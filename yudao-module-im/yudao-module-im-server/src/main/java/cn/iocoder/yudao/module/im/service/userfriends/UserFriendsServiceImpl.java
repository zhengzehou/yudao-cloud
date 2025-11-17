package cn.iocoder.yudao.module.im.service.userfriends;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.im.controller.admin.userinfo.vo.UserInfoPageReqVO;
import cn.iocoder.yudao.module.im.controller.admin.userinfo.vo.UserInfoRespVO;
import cn.iocoder.yudao.module.im.controller.admin.userinfo.vo.UserSearchPageReqVO;
import cn.iocoder.yudao.module.im.controller.app.chat.vo.UserVO;
import cn.iocoder.yudao.module.im.dal.dataobject.userfriends.UserFriendsApplyDO;
import cn.iocoder.yudao.module.im.dal.mysql.userfriends.UserFriendsApplyMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.im.controller.admin.userfriends.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.userfriends.UserFriendsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.im.dal.mysql.userfriends.UserFriendsMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 我的好友 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class UserFriendsServiceImpl implements UserFriendsService {

    @Resource
    private UserFriendsMapper userFriendsMapper;
    @Resource
    private UserFriendsApplyMapper userFriendsApplyMapper;

    @Override
    public Long createUserFriends(UserFriendsSaveReqVO createReqVO) {
        // 插入
        UserFriendsDO userFriends = BeanUtils.toBean(createReqVO, UserFriendsDO.class);
        userFriendsMapper.insert(userFriends);

        // 返回
        return userFriends.getId();
    }

    @Override
    public Long createUserFriendsApply(Long userId,Long targetUserId,String msg) {
        LambdaQueryWrapper<UserFriendsApplyDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserFriendsApplyDO::getUserId, userId);
        queryWrapper.eq(UserFriendsApplyDO::getFriendId, targetUserId);
        List<UserFriendsApplyDO> list = userFriendsApplyMapper.selectList(queryWrapper);
        if(list.isEmpty()) {
            // 插入
            UserFriendsApplyDO userFriends = new UserFriendsApplyDO();
            userFriends.setUserId(userId);
            userFriends.setFriendId(targetUserId);
            userFriends.setMsg(msg);
            userFriends.setStatus(0);
            userFriends.setCreateTime(LocalDateTime.now());
            userFriends.setDeleted(false);
            userFriendsApplyMapper.insert(userFriends);
            // 返回
            return userFriends.getId();
        }else {
            return list.get(0).getId();
        }
    }

    @Override
    public Integer handleUserFriendsApply(UserFriendsApplyDO applyDO,Integer status) {
        int n;
        // 如果拒绝
        if(status == 2){
            applyDO.setStatus(2);
            n = userFriendsApplyMapper.updateById(applyDO);
        }else{
            applyDO.setStatus(1);
            // 添加用户和朋友的关系
            UserFriendsDO userFriends = new UserFriendsDO().setUserId(applyDO.getUserId()).setFriendId(applyDO.getFriendId()).setStatus(0);
            userFriends.setCreateTime(LocalDateTime.now());
            n = userFriendsMapper.insert(userFriends);
        }
        return n;
    }

    @Override
    public void updateUserFriends(UserFriendsSaveReqVO updateReqVO) {
        // 校验存在
        validateUserFriendsExists(updateReqVO.getId());
        // 更新
        UserFriendsDO updateObj = BeanUtils.toBean(updateReqVO, UserFriendsDO.class);
        userFriendsMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserFriends(Long id) {
        // 校验存在
        validateUserFriendsExists(id);
        // 删除
        userFriendsMapper.deleteById(id);
    }

    @Override
        public void deleteUserFriendsListByIds(List<Long> ids) {
        // 删除
        userFriendsMapper.deleteByIds(ids);
        }


    private void validateUserFriendsExists(Long id) {
        if (userFriendsMapper.selectById(id) == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND);
        }
    }

    @Override
    public UserFriendsDO getUserFriends(Long id) {
        return userFriendsMapper.selectById(id);
    }

    @Override
    public PageResult<UserInfoRespVO> searchUserFriendsPage(UserSearchPageReqVO pageReqVO) {
        IPage<UserInfoRespVO> page = Page.of(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        page = userFriendsMapper.selectFriendsPageByKeyword(page,pageReqVO.getUserId(),pageReqVO.getKeyword());
        return new PageResult<>(page.getRecords(), page.getTotal());
    }
    @Override
    public PageResult<UserFriendsRespVO> getUserFriendsPage(UserInfoPageReqVO pageReqVO) {
        IPage<UserFriendsRespVO> page = Page.of(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        String nickName = pageReqVO.getNickName();
        if(nickName == null || nickName.length() < 6){
            nickName = "";
        }
        page = userFriendsMapper.selectFriendsPage(page,pageReqVO.getId(),nickName);
        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    @Override
    public PageResult<UserFriendsApplyRespVO> getUserFriendsApplyPage(UserFriendsPageReqVO pageReqVO) {
        IPage<UserFriendsApplyRespVO> page = Page.of(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        page = userFriendsMapper.selectFriendsApplyPage(page,pageReqVO.getUserId());
        return new PageResult<>(page.getRecords(), page.getTotal());
    }
    @Override
    public UserFriendsApplyDO getApplyInfo(Long id) {
        return userFriendsApplyMapper.selectById(id);
    }

}
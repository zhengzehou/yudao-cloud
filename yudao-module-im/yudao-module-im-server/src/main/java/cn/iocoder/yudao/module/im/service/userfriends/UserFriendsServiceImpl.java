package cn.iocoder.yudao.module.im.service.userfriends;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Long createUserFriends(UserFriendsSaveReqVO createReqVO) {
        // 插入
        UserFriendsDO userFriends = BeanUtils.toBean(createReqVO, UserFriendsDO.class);
        userFriendsMapper.insert(userFriends);

        // 返回
        return userFriends.getId();
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
    public PageResult<UserFriendsDO> getUserFriendsPage(UserFriendsPageReqVO pageReqVO) {
        return userFriendsMapper.selectPage(pageReqVO);
    }

}
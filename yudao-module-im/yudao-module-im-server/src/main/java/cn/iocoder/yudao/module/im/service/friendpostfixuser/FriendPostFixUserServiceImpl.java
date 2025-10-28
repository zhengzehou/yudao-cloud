package cn.iocoder.yudao.module.im.service.friendpostfixuser;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.im.controller.admin.friendpostfixuser.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.friendpostfixuser.FriendPostFixUserDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.im.dal.mysql.friendpostfixuser.FriendPostFixUserMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
/**
 * 朋友圈特定人员 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class FriendPostFixUserServiceImpl implements FriendPostFixUserService {

    @Resource
    private FriendPostFixUserMapper friendPostFixUserMapper;

    @Override
    public Long createFriendPostFixUser(FriendPostFixUserSaveReqVO createReqVO) {
        // 插入
        FriendPostFixUserDO friendPostFixUser = BeanUtils.toBean(createReqVO, FriendPostFixUserDO.class);
        friendPostFixUserMapper.insert(friendPostFixUser);

        // 返回
        return friendPostFixUser.getId();
    }

    @Override
    public void updateFriendPostFixUser(FriendPostFixUserSaveReqVO updateReqVO) {
        // 校验存在
        validateFriendPostFixUserExists(updateReqVO.getId());
        // 更新
        FriendPostFixUserDO updateObj = BeanUtils.toBean(updateReqVO, FriendPostFixUserDO.class);
        friendPostFixUserMapper.updateById(updateObj);
    }

    @Override
    public void deleteFriendPostFixUser(Long id) {
        // 校验存在
        validateFriendPostFixUserExists(id);
        // 删除
        friendPostFixUserMapper.deleteById(id);
    }

    @Override
        public void deleteFriendPostFixUserListByIds(List<Long> ids) {
        // 删除
        friendPostFixUserMapper.deleteByIds(ids);
        }


    private void validateFriendPostFixUserExists(Long id) {
        if (friendPostFixUserMapper.selectById(id) == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND);
        }
    }

    @Override
    public FriendPostFixUserDO getFriendPostFixUser(Long id) {
        return friendPostFixUserMapper.selectById(id);
    }

    @Override
    public PageResult<FriendPostFixUserDO> getFriendPostFixUserPage(FriendPostFixUserPageReqVO pageReqVO) {
        return friendPostFixUserMapper.selectPage(pageReqVO);
    }

}
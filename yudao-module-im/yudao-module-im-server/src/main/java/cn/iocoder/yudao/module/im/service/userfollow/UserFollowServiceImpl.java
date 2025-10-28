package cn.iocoder.yudao.module.im.service.userfollow;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.im.controller.admin.userfollow.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.userfollow.UserFollowDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.im.dal.mysql.userfollow.UserFollowMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 我的关注 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class UserFollowServiceImpl implements UserFollowService {

    @Resource
    private UserFollowMapper userFollowMapper;

    @Override
    public Long createUserFollow(UserFollowSaveReqVO createReqVO) {
        // 插入
        UserFollowDO userFollow = BeanUtils.toBean(createReqVO, UserFollowDO.class);
        userFollowMapper.insert(userFollow);

        // 返回
        return userFollow.getId();
    }

    @Override
    public void updateUserFollow(UserFollowSaveReqVO updateReqVO) {
        // 校验存在
        validateUserFollowExists(updateReqVO.getId());
        // 更新
        UserFollowDO updateObj = BeanUtils.toBean(updateReqVO, UserFollowDO.class);
        userFollowMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserFollow(Long id) {
        // 校验存在
        validateUserFollowExists(id);
        // 删除
        userFollowMapper.deleteById(id);
    }

    @Override
        public void deleteUserFollowListByIds(List<Long> ids) {
        // 删除
        userFollowMapper.deleteByIds(ids);
        }


    private void validateUserFollowExists(Long id) {
        if (userFollowMapper.selectById(id) == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND);
        }
    }

    @Override
    public UserFollowDO getUserFollow(Long id) {
        return userFollowMapper.selectById(id);
    }

    @Override
    public PageResult<UserFollowDO> getUserFollowPage(UserFollowPageReqVO pageReqVO) {
        return userFollowMapper.selectPage(pageReqVO);
    }

}
package cn.iocoder.yudao.module.im.dal.mysql.userfriends;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.im.dal.dataobject.userfriends.UserFriendsDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.im.controller.admin.userfriends.vo.*;

/**
 * 我的好友 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface UserFriendsMapper extends BaseMapperX<UserFriendsDO> {

    default PageResult<UserFriendsDO> selectPage(UserFriendsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserFriendsDO>()
                .eqIfPresent(UserFriendsDO::getUserId, reqVO.getUserId())
                .eqIfPresent(UserFriendsDO::getFriendId, reqVO.getFriendId())
                .eqIfPresent(UserFriendsDO::getFriendSource, reqVO.getFriendSource())
                .eqIfPresent(UserFriendsDO::getRecUserId, reqVO.getRecUserId())
                .eqIfPresent(UserFriendsDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(UserFriendsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserFriendsDO::getId));
    }

}
package cn.iocoder.yudao.module.im.dal.mysql.userfollow;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.im.dal.dataobject.userfollow.UserFollowDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.im.controller.admin.userfollow.vo.*;

/**
 * 我的关注 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface UserFollowMapper extends BaseMapperX<UserFollowDO> {

    default PageResult<UserFollowDO> selectPage(UserFollowPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserFollowDO>()
                .eqIfPresent(UserFollowDO::getUserId, reqVO.getUserId())
                .eqIfPresent(UserFollowDO::getFollowUserId, reqVO.getFollowUserId())
                .eqIfPresent(UserFollowDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(UserFollowDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserFollowDO::getId));
    }

}
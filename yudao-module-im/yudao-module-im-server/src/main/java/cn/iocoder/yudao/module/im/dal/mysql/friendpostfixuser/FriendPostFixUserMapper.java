package cn.iocoder.yudao.module.im.dal.mysql.friendpostfixuser;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.im.dal.dataobject.friendpostfixuser.FriendPostFixUserDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.im.controller.admin.friendpostfixuser.vo.*;

/**
 * 朋友圈特定人员 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface FriendPostFixUserMapper extends BaseMapperX<FriendPostFixUserDO> {

    default PageResult<FriendPostFixUserDO> selectPage(FriendPostFixUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FriendPostFixUserDO>()
                .eqIfPresent(FriendPostFixUserDO::getUserId, reqVO.getUserId())
                .eqIfPresent(FriendPostFixUserDO::getPostId, reqVO.getPostId())
                .eqIfPresent(FriendPostFixUserDO::getType, reqVO.getType())
                .betweenIfPresent(FriendPostFixUserDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FriendPostFixUserDO::getId));
    }

}
package cn.iocoder.yudao.module.im.dal.mysql.userfavorite;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.im.dal.dataobject.userfavorite.UserFavoriteDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.im.controller.admin.userfavorite.vo.*;

/**
 * 我的收藏 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface UserFavoriteMapper extends BaseMapperX<UserFavoriteDO> {

    default PageResult<UserFavoriteDO> selectPage(UserFavoritePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserFavoriteDO>()
                .eqIfPresent(UserFavoriteDO::getUserId, reqVO.getUserId())
                .eqIfPresent(UserFavoriteDO::getType, reqVO.getType())
                .eqIfPresent(UserFavoriteDO::getSendUserId, reqVO.getSendUserId())
                .eqIfPresent(UserFavoriteDO::getMsgId, reqVO.getMsgId())
                .betweenIfPresent(UserFavoriteDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserFavoriteDO::getId));
    }

}
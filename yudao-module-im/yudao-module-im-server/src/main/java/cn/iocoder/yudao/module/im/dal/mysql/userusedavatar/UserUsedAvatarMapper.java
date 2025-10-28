package cn.iocoder.yudao.module.im.dal.mysql.userusedavatar;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.im.dal.dataobject.userusedavatar.UserUsedAvatarDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.im.controller.admin.userusedavatar.vo.*;

/**
 * 历史头像 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface UserUsedAvatarMapper extends BaseMapperX<UserUsedAvatarDO> {

    default PageResult<UserUsedAvatarDO> selectPage(UserUsedAvatarPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserUsedAvatarDO>()
                .eqIfPresent(UserUsedAvatarDO::getUserId, reqVO.getUserId())
                .eqIfPresent(UserUsedAvatarDO::getAvatarUrl, reqVO.getAvatarUrl())
                .betweenIfPresent(UserUsedAvatarDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserUsedAvatarDO::getId));
    }

}
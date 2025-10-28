package cn.iocoder.yudao.module.im.dal.mysql.usedavatar;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.im.dal.dataobject.usedavatar.UsedAvatarDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.im.controller.admin.usedavatar.vo.*;

/**
 * 历史头像 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface UsedAvatarMapper extends BaseMapperX<UsedAvatarDO> {

    default PageResult<UsedAvatarDO> selectPage(UsedAvatarPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UsedAvatarDO>()
                .eqIfPresent(UsedAvatarDO::getUserId, reqVO.getUserId())
                .eqIfPresent(UsedAvatarDO::getAvatarUrl, reqVO.getAvatarUrl())
                .betweenIfPresent(UsedAvatarDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UsedAvatarDO::getId));
    }

}
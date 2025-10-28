package cn.iocoder.yudao.module.im.dal.mysql.userlabel;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.im.dal.dataobject.userlabel.UserLabelDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.im.controller.admin.userlabel.vo.*;

/**
 * 人员分组标签 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface UserLabelMapper extends BaseMapperX<UserLabelDO> {

    default PageResult<UserLabelDO> selectPage(UserLabelPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserLabelDO>()
                .eqIfPresent(UserLabelDO::getUserId, reqVO.getUserId())
                .eqIfPresent(UserLabelDO::getTagId, reqVO.getTagId())
                .betweenIfPresent(UserLabelDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserLabelDO::getId));
    }

}
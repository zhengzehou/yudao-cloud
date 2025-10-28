package cn.iocoder.yudao.module.im.dal.mysql.useroplogs;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.im.dal.dataobject.useroplogs.UserOpLogsDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.im.controller.admin.useroplogs.vo.*;

/**
 * 操作记录明细 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface UserOpLogsMapper extends BaseMapperX<UserOpLogsDO> {

    default PageResult<UserOpLogsDO> selectPage(UserOpLogsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserOpLogsDO>()
                .eqIfPresent(UserOpLogsDO::getUserId, reqVO.getUserId())
                .eqIfPresent(UserOpLogsDO::getOpType, reqVO.getOpType())
                .eqIfPresent(UserOpLogsDO::getBizId, reqVO.getBizId())
                .eqIfPresent(UserOpLogsDO::getEvent, reqVO.getEvent())
                .eqIfPresent(UserOpLogsDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(UserOpLogsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserOpLogsDO::getId));
    }

}
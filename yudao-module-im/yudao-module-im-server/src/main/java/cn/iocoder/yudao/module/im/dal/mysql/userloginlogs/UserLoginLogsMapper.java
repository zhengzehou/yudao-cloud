package cn.iocoder.yudao.module.im.dal.mysql.userloginlogs;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.im.dal.dataobject.userloginlogs.UserLoginLogsDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.im.controller.admin.userloginlogs.vo.*;

/**
 * 登录历史 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface UserLoginLogsMapper extends BaseMapperX<UserLoginLogsDO> {

    default PageResult<UserLoginLogsDO> selectPage(UserLoginLogsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserLoginLogsDO>()
                .eqIfPresent(UserLoginLogsDO::getUserId, reqVO.getUserId())
                .eqIfPresent(UserLoginLogsDO::getLoginType, reqVO.getLoginType())
                .eqIfPresent(UserLoginLogsDO::getLoginTerm, reqVO.getLoginTerm())
                .eqIfPresent(UserLoginLogsDO::getCountry, reqVO.getCountry())
                .eqIfPresent(UserLoginLogsDO::getProvince, reqVO.getProvince())
                .eqIfPresent(UserLoginLogsDO::getCity, reqVO.getCity())
                .eqIfPresent(UserLoginLogsDO::getCounty, reqVO.getCounty())
                .eqIfPresent(UserLoginLogsDO::getAddress, reqVO.getAddress())
                .eqIfPresent(UserLoginLogsDO::getSysInfo, reqVO.getSysInfo())
                .eqIfPresent(UserLoginLogsDO::getBrowser, reqVO.getBrowser())
                .eqIfPresent(UserLoginLogsDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(UserLoginLogsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserLoginLogsDO::getId));
    }

}
package cn.iocoder.yudao.module.im.dal.mysql.friendpost;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.im.dal.dataobject.friendpost.FriendPostDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.im.controller.admin.friendpost.vo.*;

/**
 * 朋友圈动态 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface FriendPostMapper extends BaseMapperX<FriendPostDO> {

    default PageResult<FriendPostDO> selectPage(FriendPostPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FriendPostDO>()
                .eqIfPresent(FriendPostDO::getUserId, reqVO.getUserId())
                .eqIfPresent(FriendPostDO::getTitle, reqVO.getTitle())
                .eqIfPresent(FriendPostDO::getContent, reqVO.getContent())
                .eqIfPresent(FriendPostDO::getStatus, reqVO.getStatus())
                .eqIfPresent(FriendPostDO::getCountry, reqVO.getCountry())
                .eqIfPresent(FriendPostDO::getProvince, reqVO.getProvince())
                .eqIfPresent(FriendPostDO::getCity, reqVO.getCity())
                .eqIfPresent(FriendPostDO::getCounty, reqVO.getCounty())
                .eqIfPresent(FriendPostDO::getAddress, reqVO.getAddress())
                .eqIfPresent(FriendPostDO::getYear, reqVO.getYear())
                .eqIfPresent(FriendPostDO::getMonth, reqVO.getMonth())
                .eqIfPresent(FriendPostDO::getDay, reqVO.getDay())
                .betweenIfPresent(FriendPostDO::getPubTime, reqVO.getPubTime())
                .eqIfPresent(FriendPostDO::getShowTo, reqVO.getShowTo())
                .betweenIfPresent(FriendPostDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FriendPostDO::getId));
    }

}
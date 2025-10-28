package cn.iocoder.yudao.module.im.dal.mysql.roomuser;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.im.dal.dataobject.roomuser.RoomUserDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.im.controller.admin.roomuser.vo.*;

/**
 * 会话人员和会话设置 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RoomUserMapper extends BaseMapperX<RoomUserDO> {

    default PageResult<RoomUserDO> selectPage(RoomUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoomUserDO>()
                .eqIfPresent(RoomUserDO::getRoomId, reqVO.getRoomId())
                .likeIfPresent(RoomUserDO::getRoomGroup, reqVO.getRoomName())
                .eqIfPresent(RoomUserDO::getRoomRemark, reqVO.getRoomRemark())
                .eqIfPresent(RoomUserDO::getUserId, reqVO.getUserId())
                .eqIfPresent(RoomUserDO::getEntryType, reqVO.getEntryType())
                .eqIfPresent(RoomUserDO::getInviter, reqVO.getInviter())
                .eqIfPresent(RoomUserDO::getReadMsgId, reqVO.getReadMsgId())
                .likeIfPresent(RoomUserDO::getNickName, reqVO.getNickName())
                .eqIfPresent(RoomUserDO::getRemark, reqVO.getRemark())
                .eqIfPresent(RoomUserDO::getUserStatus, reqVO.getUserStatus())
                .eqIfPresent(RoomUserDO::getNoDisturbing, reqVO.getNoDisturbing())
                .eqIfPresent(RoomUserDO::getHide, reqVO.getHide())
                .eqIfPresent(RoomUserDO::getDelete, reqVO.getDelete())
                .eqIfPresent(RoomUserDO::getStick, reqVO.getStick())
                .betweenIfPresent(RoomUserDO::getStickTime, reqVO.getStickTime())
                .eqIfPresent(RoomUserDO::getBgPic, reqVO.getBgPic())
                .betweenIfPresent(RoomUserDO::getForbidStartTime, reqVO.getForbidStartTime())
                .betweenIfPresent(RoomUserDO::getForbidEndTime, reqVO.getForbidEndTime())
                .eqIfPresent(RoomUserDO::getAlert, reqVO.getAlert())
                .eqIfPresent(RoomUserDO::getAlertBell, reqVO.getAlertBell())
                .eqIfPresent(RoomUserDO::getCallBell, reqVO.getCallBell())
                .eqIfPresent(RoomUserDO::getComplaint, reqVO.getComplaint())
                .betweenIfPresent(RoomUserDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoomUserDO::getId));
    }

}
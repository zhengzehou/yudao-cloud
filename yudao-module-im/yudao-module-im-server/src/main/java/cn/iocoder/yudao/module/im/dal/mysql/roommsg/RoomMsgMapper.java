package cn.iocoder.yudao.module.im.dal.mysql.roommsg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.im.dal.dataobject.roommsg.RoomMsgDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.im.controller.admin.roommsg.vo.*;

/**
 * 会话消息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RoomMsgMapper extends BaseMapperX<RoomMsgDO> {

    default PageResult<RoomMsgDO> selectPage(RoomMsgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoomMsgDO>()
                .eqIfPresent(RoomMsgDO::getRoomId, reqVO.getRoomId())
                .eqIfPresent(RoomMsgDO::getSendUserId, reqVO.getSendUserId())
                .eqIfPresent(RoomMsgDO::getMsgType, reqVO.getMsgType())
                .eqIfPresent(RoomMsgDO::getMsgContent, reqVO.getMsgContent())
                .eqIfPresent(RoomMsgDO::getTerm, reqVO.getTerm())
                .eqIfPresent(RoomMsgDO::getStatus, reqVO.getStatus())
                .eqIfPresent(RoomMsgDO::getRefMsgId, reqVO.getRefMsgId())
                .eqIfPresent(RoomMsgDO::getSysMsgType, reqVO.getSysMsgType())
                .eqIfPresent(RoomMsgDO::getKnowledgeId, reqVO.getKnowledgeId())
                .eqIfPresent(RoomMsgDO::getResults, reqVO.getResults())
                .eqIfPresent(RoomMsgDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(RoomMsgDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoomMsgDO::getId));
    }

}
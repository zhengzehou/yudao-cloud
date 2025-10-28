package cn.iocoder.yudao.module.im.dal.mysql.roomgroup;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.im.controller.app.chat.vo.ContactVO;
import cn.iocoder.yudao.module.im.controller.app.chat.vo.RoomGroupVO;
import cn.iocoder.yudao.module.im.controller.app.chat.vo.RoomReadInfo;
import cn.iocoder.yudao.module.im.controller.app.chat.vo.RoomUnreadCount;
import cn.iocoder.yudao.module.im.dal.dataobject.roomgroup.RoomGroupDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.im.controller.admin.roomgroup.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 会话（群组） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RoomGroupMapper extends BaseMapperX<RoomGroupDO> {

    default PageResult<RoomGroupDO> selectPage(RoomGroupPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoomGroupDO>()
                .eqIfPresent(RoomGroupDO::getCreateUserId, reqVO.getCreateUserId())
                .likeIfPresent(RoomGroupDO::getRoomGroup, reqVO.getRoomName())
                .eqIfPresent(RoomGroupDO::getChannel, reqVO.getChannel())
                .eqIfPresent(RoomGroupDO::getSource, reqVO.getSource())
                .eqIfPresent(RoomGroupDO::getTerm, reqVO.getTerm())
                .eqIfPresent(RoomGroupDO::getOsType, reqVO.getOsType())
                .eqIfPresent(RoomGroupDO::getItemId, reqVO.getItemId())
                .eqIfPresent(RoomGroupDO::getShopId, reqVO.getShopId())
                .eqIfPresent(RoomGroupDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(RoomGroupDO::getMerId, reqVO.getMerId())
                .eqIfPresent(RoomGroupDO::getRate, reqVO.getRate())
                .eqIfPresent(RoomGroupDO::getType, reqVO.getType())
                .eqIfPresent(RoomGroupDO::getOwner, reqVO.getOwner())
                .eqIfPresent(RoomGroupDO::getStatus, reqVO.getStatus())
                .eqIfPresent(RoomGroupDO::getLastedMsgId, reqVO.getLastedMsgId())
                .eqIfPresent(RoomGroupDO::getLastedMsg, reqVO.getLastedMsg())
                .betweenIfPresent(RoomGroupDO::getLastedMsgTime, reqVO.getLastedMsgTime())
                .eqIfPresent(RoomGroupDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(RoomGroupDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoomGroupDO::getId));
    }

    /**
     * 获取最近联系人
     * @param reqVO
     * @return
     */

//    @Select("SELECT g.* FROM im_session_group g inner join im_session_msg m on g.id = m.session_id WHERE m.send_user_id = #{createUserId} ORDER BY g.lasted_msg_time DESC LIMIT #{offset} , #{pageSize}")
    @Select("SELECT ru.ROOM_ID,ru.ROOM_GROUP ,g.LASTED_MSG text,g.LASTED_MSG_ID,u.AVATAR,u.STATUS type,u.NICK_NAME name,ru.READ_MSG_ID lastMsgId, g.LASTED_MSG_TIME activeTime ,ru.STICK_TIME as pinTime, 0 noticeStatus, 0 shieldStatus,0 hotFlag,0 unreadCount\n" +
            "FROM  (SELECT * FROM IM_ROOM_USER WHERE USER_ID =  #{createUserId}) ru INNER JOIN (SELECT * FROM IM_USER_INFO WHERE ID =  #{createUserId}) u on ru.USER_ID = u.ID INNER JOIN IM_ROOM_GROUP g  on g.id = ru.ROOM_ID\n" +
            "WHERE u.ID = #{createUserId} ORDER BY g.lasted_msg_time DESC LIMIT #{offset} , #{pageSize}")
    PageResult<ContactVO> selectContactPage(RoomGroupPageReqVO reqVO) ;

    @Select("ru.ROOM_ID,ru.ROOM_GROUP name,u.AVATAR,u.deleted deleteStatus,3 role, g.CREATE_TIME ,g.UPDATE_TIME ,ru.CREATE_TIME joinTime,ru.UPDATE_TIME memberUpdateTime " +
            "FROM  (SELECT * FROM IM_ROOM_USER WHERE USER_ID =  #{createUserId}) ru INNER JOIN (SELECT * FROM IM_USER_INFO WHERE ID =  #{createUserId}) u on ru.USER_ID = u.ID INNER JOIN IM_ROOM_GROUP g  on g.id = ru.ROOM_ID\n" +
            "WHERE u.ID = #{createUserId} ORDER BY g.lasted_msg_time DESC LIMIT #{offset} , #{pageSize}")
    PageResult<RoomGroupVO> selectContactGroupPage(RoomGroupPageReqVO reqVO) ;

    @Select("SELECT count(1) FROM IM_ROOM_MSG m WHERE m.ROOM_ID = #{roomId} and ID > #{readMsgId}")
    int selectUnreadMsgCount(Long roomId, Long readMsgId) ;

    @Select("<script>" +
            "SELECT m.ROOM_ID as roomId, COUNT(1) as unreadCount " +
            "FROM IM_ROOM_MSG m " +
            "WHERE " +
            "<foreach collection='roomReadInfos' item='info' separator=' OR '>" +
            "   (m.ROOM_ID = #{info.roomId} AND m.ID > #{info.readMsgId})" +
            "</foreach> " +
            "GROUP BY m.ROOM_ID" +
            "</script>")
    List<RoomUnreadCount> selectUnreadMsgCountBatch(@Param("roomReadInfos") List<RoomReadInfo> roomReadInfos);

}
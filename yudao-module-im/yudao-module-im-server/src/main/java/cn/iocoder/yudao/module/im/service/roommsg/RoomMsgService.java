package cn.iocoder.yudao.module.im.service.roommsg;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.im.controller.admin.roommsg.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.roommsg.RoomMsgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 会话消息 Service 接口
 *
 * @author 芋道源码
 */
public interface RoomMsgService {

    /**
     * 创建会话消息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRoomMsg(@Valid RoomMsgSaveReqVO createReqVO);

    /**
     * 更新会话消息
     *
     * @param updateReqVO 更新信息
     */
    void updateRoomMsg(@Valid RoomMsgSaveReqVO updateReqVO);

    /**
     * 删除会话消息
     *
     * @param id 编号
     */
    void deleteRoomMsg(Long id);

    /**
    * 批量删除会话消息
    *
    * @param ids 编号
    */
    void deleteRoomMsgListByIds(List<Long> ids);

    /**
     * 获得会话消息
     *
     * @param id 编号
     * @return 会话消息
     */
    RoomMsgDO getRoomMsg(Long id);

    /**
     * 获得会话消息分页
     *
     * @param pageReqVO 分页查询
     * @return 会话消息分页
     */
    PageResult<RoomMsgDO> getRoomMsgPage(RoomMsgPageReqVO pageReqVO);

}
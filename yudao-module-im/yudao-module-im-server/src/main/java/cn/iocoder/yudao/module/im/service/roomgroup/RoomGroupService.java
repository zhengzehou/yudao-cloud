package cn.iocoder.yudao.module.im.service.roomgroup;

import java.util.*;

import cn.iocoder.yudao.module.im.controller.app.chat.vo.ContactVO;
import cn.iocoder.yudao.module.im.controller.app.chat.vo.RoomGroupVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.im.controller.admin.roomgroup.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.roomgroup.RoomGroupDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 会话（群组） Service 接口
 *
 * @author 芋道源码
 */
public interface RoomGroupService {

    /**
     * 创建会话（群组）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRoomGroup(@Valid RoomGroupSaveReqVO createReqVO);

    /**
     * 更新会话（群组）
     *
     * @param updateReqVO 更新信息
     */
    void updateRoomGroup(@Valid RoomGroupSaveReqVO updateReqVO);

    /**
     * 删除会话（群组）
     *
     * @param id 编号
     */
    void deleteRoomGroup(Long id);

    /**
    * 批量删除会话（群组）
    *
    * @param ids 编号
    */
    void deleteRoomGroupListByIds(List<Long> ids);

    /**
     * 获得会话（群组）
     *
     * @param id 编号
     * @return 会话（群组）
     */
    RoomGroupDO getRoomGroup(Long id);

    /**
     * 获得会话（群组）分页
     *
     * @param pageReqVO 分页查询
     * @return 会话（群组）分页
     */
    PageResult<RoomGroupDO> getRoomGroupPage(RoomGroupPageReqVO pageReqVO);

    PageResult<RoomGroupVO> getContactGroupPage(RoomGroupPageReqVO pageReqVO);

    PageResult<ContactVO> getContactPage(RoomGroupPageReqVO pageReqVO);

}
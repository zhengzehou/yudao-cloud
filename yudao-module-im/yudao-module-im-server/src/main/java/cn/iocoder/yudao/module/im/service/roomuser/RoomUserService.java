package cn.iocoder.yudao.module.im.service.roomuser;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.im.controller.admin.roomuser.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.roomuser.RoomUserDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 会话人员和会话设置 Service 接口
 *
 * @author 芋道源码
 */
public interface RoomUserService {

    /**
     * 创建会话人员和会话设置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRoomUser(@Valid RoomUserSaveReqVO createReqVO);

    /**
     * 更新会话人员和会话设置
     *
     * @param updateReqVO 更新信息
     */
    void updateRoomUser(@Valid RoomUserSaveReqVO updateReqVO);

    /**
     * 删除会话人员和会话设置
     *
     * @param id 编号
     */
    void deleteRoomUser(Long id);

    /**
    * 批量删除会话人员和会话设置
    *
    * @param ids 编号
    */
    void deleteRoomUserListByIds(List<Long> ids);

    /**
     * 获得会话人员和会话设置
     *
     * @param id 编号
     * @return 会话人员和会话设置
     */
    RoomUserDO getRoomUser(Long id);

    /**
     * 获得会话人员和会话设置分页
     *
     * @param pageReqVO 分页查询
     * @return 会话人员和会话设置分页
     */
    PageResult<RoomUserDO> getRoomUserPage(RoomUserPageReqVO pageReqVO);

}
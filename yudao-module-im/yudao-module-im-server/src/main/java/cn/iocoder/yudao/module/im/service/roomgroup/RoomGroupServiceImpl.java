package cn.iocoder.yudao.module.im.service.roomgroup;

import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import cn.iocoder.yudao.module.im.controller.app.chat.vo.ContactVO;
import cn.iocoder.yudao.module.im.controller.app.chat.vo.RoomGroupVO;
import cn.iocoder.yudao.module.im.controller.app.chat.vo.RoomReadInfo;
import cn.iocoder.yudao.module.im.controller.app.chat.vo.RoomUnreadCount;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.im.controller.admin.roomgroup.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.roomgroup.RoomGroupDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.im.dal.mysql.roomgroup.RoomGroupMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 会话（群组） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RoomGroupServiceImpl implements RoomGroupService {

    @Resource
    private RoomGroupMapper roomGroupMapper;

    @Override
    public Long createRoomGroup(RoomGroupSaveReqVO createReqVO) {
        // 插入
        RoomGroupDO sessionGroup = BeanUtils.toBean(createReqVO, RoomGroupDO.class);
        roomGroupMapper.insert(sessionGroup);

        // 返回
        return sessionGroup.getId();
    }

    @Override
    public void updateRoomGroup(RoomGroupSaveReqVO updateReqVO) {
        // 校验存在
        validateRoomGroupExists(updateReqVO.getId());
        // 更新
        RoomGroupDO updateObj = BeanUtils.toBean(updateReqVO, RoomGroupDO.class);
        roomGroupMapper.updateById(updateObj);
    }

    @Override
    public void deleteRoomGroup(Long id) {
        // 校验存在
        validateRoomGroupExists(id);
        // 删除
        roomGroupMapper.deleteById(id);
    }

    @Override
        public void deleteRoomGroupListByIds(List<Long> ids) {
        // 删除
        roomGroupMapper.deleteByIds(ids);
        }


    private void validateRoomGroupExists(Long id) {
        if (roomGroupMapper.selectById(id) == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND);
        }
    }

    @Override
    public RoomGroupDO getRoomGroup(Long id) {
        return roomGroupMapper.selectById(id);
    }

    @Override
    public PageResult<RoomGroupDO> getRoomGroupPage(RoomGroupPageReqVO pageReqVO) {
        return roomGroupMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<ContactVO> getContactPage(RoomGroupPageReqVO pageReqVO) {
        PageResult<ContactVO> page = roomGroupMapper.selectContactPage(pageReqVO);
        // 加载会话的未读消息数
        List<RoomReadInfo> roomReadInfos = new ArrayList<>();
        page.getList().forEach(contactVO -> roomReadInfos.add(new RoomReadInfo(contactVO.getRoomId(), contactVO.getLastMsgId())));
        Map<Long,Integer> roomUnreadCounts = getUnreadMsgCount(roomReadInfos);
        page.getList().forEach(contactVO -> roomUnreadCounts.get(contactVO.getRoomId()));
        return page;
    }
    @Override
    public PageResult<RoomGroupVO> getContactGroupPage(RoomGroupPageReqVO pageReqVO) {
        PageResult<RoomGroupVO> page = roomGroupMapper.selectContactGroupPage(pageReqVO);
        return page;
    }

    public Map<Long,Integer> getUnreadMsgCount(List<RoomReadInfo> roomReadInfos) {
//        List<RoomReadInfo> roomReadInfos
        List<RoomUnreadCount> roomUnreadCounts = roomGroupMapper.selectUnreadMsgCountBatch(roomReadInfos);
        // 加载会话的未读消息数
        return roomUnreadCounts.stream().collect(Collectors.toMap(RoomUnreadCount::getRoomId, RoomUnreadCount::getUnreadCount));
    }

}
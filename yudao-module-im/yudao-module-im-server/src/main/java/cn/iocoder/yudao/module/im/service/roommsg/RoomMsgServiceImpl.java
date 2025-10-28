package cn.iocoder.yudao.module.im.service.roommsg;

import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.im.controller.admin.roommsg.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.roommsg.RoomMsgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.im.dal.mysql.roommsg.RoomMsgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 会话消息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RoomMsgServiceImpl implements RoomMsgService {

    @Resource
    private RoomMsgMapper roomMsgMapper;

    @Override
    public Long createRoomMsg(RoomMsgSaveReqVO createReqVO) {
        // 插入
        RoomMsgDO sessionMsg = BeanUtils.toBean(createReqVO, RoomMsgDO.class);
        roomMsgMapper.insert(sessionMsg);

        // 返回
        return sessionMsg.getId();
    }

    @Override
    public void updateRoomMsg(RoomMsgSaveReqVO updateReqVO) {
        // 校验存在
        validateRoomMsgExists(updateReqVO.getId());
        // 更新
        RoomMsgDO updateObj = BeanUtils.toBean(updateReqVO, RoomMsgDO.class);
        roomMsgMapper.updateById(updateObj);
    }

    @Override
    public void deleteRoomMsg(Long id) {
        // 校验存在
        validateRoomMsgExists(id);
        // 删除
        roomMsgMapper.deleteById(id);
    }

    @Override
        public void deleteRoomMsgListByIds(List<Long> ids) {
        // 删除
        roomMsgMapper.deleteByIds(ids);
        }


    private void validateRoomMsgExists(Long id) {
        if (roomMsgMapper.selectById(id) == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND);
        }
    }

    @Override
    public RoomMsgDO getRoomMsg(Long id) {
        return roomMsgMapper.selectById(id);
    }

    @Override
    public PageResult<RoomMsgDO> getRoomMsgPage(RoomMsgPageReqVO pageReqVO) {
        return roomMsgMapper.selectPage(pageReqVO);
    }

}
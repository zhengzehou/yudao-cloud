package cn.iocoder.yudao.module.im.service.roomuser;

import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.im.controller.admin.roomuser.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.roomuser.RoomUserDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.im.dal.mysql.roomuser.RoomUserMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 会话人员和会话设置 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RoomUserServiceImpl implements RoomUserService {

    @Resource
    private RoomUserMapper roomUserMapper;

    @Override
    public Long createRoomUser(RoomUserSaveReqVO createReqVO) {
        // 插入
        RoomUserDO sessionUser = BeanUtils.toBean(createReqVO, RoomUserDO.class);
        roomUserMapper.insert(sessionUser);

        // 返回
        return sessionUser.getId();
    }

    @Override
    public void updateRoomUser(RoomUserSaveReqVO updateReqVO) {
        // 校验存在
        validateRoomUserExists(updateReqVO.getId());
        // 更新
        RoomUserDO updateObj = BeanUtils.toBean(updateReqVO, RoomUserDO.class);
        roomUserMapper.updateById(updateObj);
    }

    @Override
    public void deleteRoomUser(Long id) {
        // 校验存在
        validateRoomUserExists(id);
        // 删除
        roomUserMapper.deleteById(id);
    }

    @Override
        public void deleteRoomUserListByIds(List<Long> ids) {
        // 删除
        roomUserMapper.deleteByIds(ids);
        }


    private void validateRoomUserExists(Long id) {
        if (roomUserMapper.selectById(id) == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND);
        }
    }

    @Override
    public RoomUserDO getRoomUser(Long id) {
        return roomUserMapper.selectById(id);
    }

    @Override
    public PageResult<RoomUserDO> getRoomUserPage(RoomUserPageReqVO pageReqVO) {
        return roomUserMapper.selectPage(pageReqVO);
    }

}
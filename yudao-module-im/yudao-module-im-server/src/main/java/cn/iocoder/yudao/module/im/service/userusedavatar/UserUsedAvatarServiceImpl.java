package cn.iocoder.yudao.module.im.service.userusedavatar;

import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.im.controller.admin.userusedavatar.vo.UserUsedAvatarPageReqVO;
import cn.iocoder.yudao.module.im.controller.admin.userusedavatar.vo.UserUsedAvatarSaveReqVO;
import cn.iocoder.yudao.module.im.dal.dataobject.userusedavatar.UserUsedAvatarDO;
import cn.iocoder.yudao.module.im.dal.mysql.userusedavatar.UserUsedAvatarMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 历史头像 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class UserUsedAvatarServiceImpl implements UserUsedAvatarService {

    @Resource
    private UserUsedAvatarMapper userUsedAvatarMapper;

    @Override
    public Long createUserUsedAvatar(UserUsedAvatarSaveReqVO createReqVO) {
        // 插入
        UserUsedAvatarDO userUsedAvatar = BeanUtils.toBean(createReqVO, UserUsedAvatarDO.class);
        userUsedAvatarMapper.insert(userUsedAvatar);

        // 返回
        return userUsedAvatar.getId();
    }

    @Override
    public void updateUserUsedAvatar(UserUsedAvatarSaveReqVO updateReqVO) {
        // 校验存在
        validateUserUsedAvatarExists(updateReqVO.getId());
        // 更新
        UserUsedAvatarDO updateObj = BeanUtils.toBean(updateReqVO, UserUsedAvatarDO.class);
        userUsedAvatarMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserUsedAvatar(Long id) {
        // 校验存在
        validateUserUsedAvatarExists(id);
        // 删除
        userUsedAvatarMapper.deleteById(id);
    }

    @Override
        public void deleteUserUsedAvatarListByIds(List<Long> ids) {
        // 删除
        userUsedAvatarMapper.deleteByIds(ids);
        }


    private void validateUserUsedAvatarExists(Long id) {
        if (userUsedAvatarMapper.selectById(id) == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND);
        }
    }

    @Override
    public UserUsedAvatarDO getUserUsedAvatar(Long id) {
        return userUsedAvatarMapper.selectById(id);
    }

    @Override
    public PageResult<UserUsedAvatarDO> getUserUsedAvatarPage(UserUsedAvatarPageReqVO pageReqVO) {
        return userUsedAvatarMapper.selectPage(pageReqVO);
    }

}
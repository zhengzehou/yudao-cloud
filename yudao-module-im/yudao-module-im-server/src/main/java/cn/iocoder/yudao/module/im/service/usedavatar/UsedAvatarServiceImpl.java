package cn.iocoder.yudao.module.im.service.usedavatar;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.im.controller.admin.usedavatar.vo.UsedAvatarPageReqVO;
import cn.iocoder.yudao.module.im.controller.admin.usedavatar.vo.UsedAvatarSaveReqVO;
import cn.iocoder.yudao.module.im.dal.dataobject.usedavatar.UsedAvatarDO;
import cn.iocoder.yudao.module.im.dal.mysql.usedavatar.UsedAvatarMapper;
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
public class UsedAvatarServiceImpl implements UsedAvatarService {

    @Resource
    private UsedAvatarMapper usedAvatarMapper;

    @Override
    public Long createUsedAvatar(UsedAvatarSaveReqVO createReqVO) {
        // 插入
        UsedAvatarDO usedAvatar = BeanUtils.toBean(createReqVO, UsedAvatarDO.class);
        usedAvatarMapper.insert(usedAvatar);

        // 返回
        return usedAvatar.getId();
    }

    @Override
    public void updateUsedAvatar(UsedAvatarSaveReqVO updateReqVO) {
        // 校验存在
        validateUsedAvatarExists(updateReqVO.getId());
        // 更新
        UsedAvatarDO updateObj = BeanUtils.toBean(updateReqVO, UsedAvatarDO.class);
        usedAvatarMapper.updateById(updateObj);
    }

    @Override
    public void deleteUsedAvatar(Long id) {
        // 校验存在
        validateUsedAvatarExists(id);
        // 删除
        usedAvatarMapper.deleteById(id);
    }

    @Override
        public void deleteUsedAvatarListByIds(List<Long> ids) {
        // 删除
        usedAvatarMapper.deleteByIds(ids);
        }


    private void validateUsedAvatarExists(Long id) {
        if (usedAvatarMapper.selectById(id) == null) {
            throw exception(new ErrorCode(1,"数据不存在"));
        }
    }

    @Override
    public UsedAvatarDO getUsedAvatar(Long id) {
        return usedAvatarMapper.selectById(id);
    }

    @Override
    public PageResult<UsedAvatarDO> getUsedAvatarPage(UsedAvatarPageReqVO pageReqVO) {
        return usedAvatarMapper.selectPage(pageReqVO);
    }

}
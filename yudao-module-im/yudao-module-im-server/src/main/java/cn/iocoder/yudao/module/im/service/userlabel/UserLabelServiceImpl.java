package cn.iocoder.yudao.module.im.service.userlabel;

import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.im.controller.admin.userlabel.vo.UserLabelPageReqVO;
import cn.iocoder.yudao.module.im.controller.admin.userlabel.vo.UserLabelSaveReqVO;
import cn.iocoder.yudao.module.im.dal.dataobject.userlabel.UserLabelDO;
import cn.iocoder.yudao.module.im.dal.mysql.userlabel.UserLabelMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 人员分组标签 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class UserLabelServiceImpl implements UserLabelService {

    @Resource
    private UserLabelMapper userLabelMapper;

    @Override
    public Long createUserLabel(UserLabelSaveReqVO createReqVO) {
        // 插入
        UserLabelDO userLabel = BeanUtils.toBean(createReqVO, UserLabelDO.class);
        userLabelMapper.insert(userLabel);

        // 返回
        return userLabel.getId();
    }

    @Override
    public void updateUserLabel(UserLabelSaveReqVO updateReqVO) {
        // 校验存在
        validateUserLabelExists(updateReqVO.getId());
        // 更新
        UserLabelDO updateObj = BeanUtils.toBean(updateReqVO, UserLabelDO.class);
        userLabelMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserLabel(Long id) {
        // 校验存在
        validateUserLabelExists(id);
        // 删除
        userLabelMapper.deleteById(id);
    }

    @Override
        public void deleteUserLabelListByIds(List<Long> ids) {
        // 删除
        userLabelMapper.deleteByIds(ids);
        }


    private void validateUserLabelExists(Long id) {
        if (userLabelMapper.selectById(id) == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND);
        }
    }

    @Override
    public UserLabelDO getUserLabel(Long id) {
        return userLabelMapper.selectById(id);
    }

    @Override
    public PageResult<UserLabelDO> getUserLabelPage(UserLabelPageReqVO pageReqVO) {
        return userLabelMapper.selectPage(pageReqVO);
    }

}
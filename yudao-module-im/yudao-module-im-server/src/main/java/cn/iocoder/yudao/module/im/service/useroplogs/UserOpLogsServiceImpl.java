package cn.iocoder.yudao.module.im.service.useroplogs;

import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.im.controller.admin.useroplogs.vo.UserOpLogsPageReqVO;
import cn.iocoder.yudao.module.im.controller.admin.useroplogs.vo.UserOpLogsSaveReqVO;
import cn.iocoder.yudao.module.im.dal.dataobject.useroplogs.UserOpLogsDO;
import cn.iocoder.yudao.module.im.dal.mysql.useroplogs.UserOpLogsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 操作记录明细 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class UserOpLogsServiceImpl implements UserOpLogsService {

    @Resource
    private UserOpLogsMapper userOpLogsMapper;

    @Override
    public Long createUserOpLogs(UserOpLogsSaveReqVO createReqVO) {
        // 插入
        UserOpLogsDO userOpLogs = BeanUtils.toBean(createReqVO, UserOpLogsDO.class);
        userOpLogsMapper.insert(userOpLogs);

        // 返回
        return userOpLogs.getId();
    }

    @Override
    public void updateUserOpLogs(UserOpLogsSaveReqVO updateReqVO) {
        // 校验存在
        validateUserOpLogsExists(updateReqVO.getId());
        // 更新
        UserOpLogsDO updateObj = BeanUtils.toBean(updateReqVO, UserOpLogsDO.class);
        userOpLogsMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserOpLogs(Long id) {
        // 校验存在
        validateUserOpLogsExists(id);
        // 删除
        userOpLogsMapper.deleteById(id);
    }

    @Override
        public void deleteUserOpLogsListByIds(List<Long> ids) {
        // 删除
        userOpLogsMapper.deleteByIds(ids);
        }


    private void validateUserOpLogsExists(Long id) {
        if (userOpLogsMapper.selectById(id) == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND);
        }
    }

    @Override
    public UserOpLogsDO getUserOpLogs(Long id) {
        return userOpLogsMapper.selectById(id);
    }

    @Override
    public PageResult<UserOpLogsDO> getUserOpLogsPage(UserOpLogsPageReqVO pageReqVO) {
        return userOpLogsMapper.selectPage(pageReqVO);
    }

}
package cn.iocoder.yudao.module.im.service.userloginlogs;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.im.controller.admin.userloginlogs.vo.UserLoginLogsPageReqVO;
import cn.iocoder.yudao.module.im.controller.admin.userloginlogs.vo.UserLoginLogsSaveReqVO;
import cn.iocoder.yudao.module.im.dal.dataobject.userloginlogs.UserLoginLogsDO;
import cn.iocoder.yudao.module.im.dal.mysql.userloginlogs.UserLoginLogsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 登录历史 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class UserLoginLogsServiceImpl implements UserLoginLogsService {

    @Resource
    private UserLoginLogsMapper userLoginLogsMapper;

    @Override
    public Long createUserLoginLogs(UserLoginLogsSaveReqVO createReqVO) {
        // 插入
        UserLoginLogsDO userLoginLogs = BeanUtils.toBean(createReqVO, UserLoginLogsDO.class);
        userLoginLogsMapper.insert(userLoginLogs);

        // 返回
        return userLoginLogs.getId();
    }

    @Override
    public void updateUserLoginLogs(UserLoginLogsSaveReqVO updateReqVO) {
        // 校验存在
        validateUserLoginLogsExists(updateReqVO.getId());
        // 更新
        UserLoginLogsDO updateObj = BeanUtils.toBean(updateReqVO, UserLoginLogsDO.class);
        userLoginLogsMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserLoginLogs(Long id) {
        // 校验存在
        validateUserLoginLogsExists(id);
        // 删除
        userLoginLogsMapper.deleteById(id);
    }

    @Override
        public void deleteUserLoginLogsListByIds(List<Long> ids) {
        // 删除
        userLoginLogsMapper.deleteByIds(ids);
        }


    private void validateUserLoginLogsExists(Long id) {
        if (userLoginLogsMapper.selectById(id) == null) {
//            throw exception(USER_LOGIN_LOGS_NOT_EXISTS);
        }
    }

    @Override
    public UserLoginLogsDO getUserLoginLogs(Long id) {
        return userLoginLogsMapper.selectById(id);
    }

    @Override
    public PageResult<UserLoginLogsDO> getUserLoginLogsPage(UserLoginLogsPageReqVO pageReqVO) {
        return userLoginLogsMapper.selectPage(pageReqVO);
    }

}
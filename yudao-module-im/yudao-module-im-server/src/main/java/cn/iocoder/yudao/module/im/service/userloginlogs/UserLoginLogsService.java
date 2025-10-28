package cn.iocoder.yudao.module.im.service.userloginlogs;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.im.controller.admin.userloginlogs.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.userloginlogs.UserLoginLogsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 登录历史 Service 接口
 *
 * @author 芋道源码
 */
public interface UserLoginLogsService {

    /**
     * 创建登录历史
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserLoginLogs(@Valid UserLoginLogsSaveReqVO createReqVO);

    /**
     * 更新登录历史
     *
     * @param updateReqVO 更新信息
     */
    void updateUserLoginLogs(@Valid UserLoginLogsSaveReqVO updateReqVO);

    /**
     * 删除登录历史
     *
     * @param id 编号
     */
    void deleteUserLoginLogs(Long id);

    /**
    * 批量删除登录历史
    *
    * @param ids 编号
    */
    void deleteUserLoginLogsListByIds(List<Long> ids);

    /**
     * 获得登录历史
     *
     * @param id 编号
     * @return 登录历史
     */
    UserLoginLogsDO getUserLoginLogs(Long id);

    /**
     * 获得登录历史分页
     *
     * @param pageReqVO 分页查询
     * @return 登录历史分页
     */
    PageResult<UserLoginLogsDO> getUserLoginLogsPage(UserLoginLogsPageReqVO pageReqVO);

}
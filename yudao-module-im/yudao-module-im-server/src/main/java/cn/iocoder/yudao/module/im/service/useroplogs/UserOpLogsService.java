package cn.iocoder.yudao.module.im.service.useroplogs;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.im.controller.admin.useroplogs.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.useroplogs.UserOpLogsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 操作记录明细 Service 接口
 *
 * @author 芋道源码
 */
public interface UserOpLogsService {

    /**
     * 创建操作记录明细
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserOpLogs(@Valid UserOpLogsSaveReqVO createReqVO);

    /**
     * 更新操作记录明细
     *
     * @param updateReqVO 更新信息
     */
    void updateUserOpLogs(@Valid UserOpLogsSaveReqVO updateReqVO);

    /**
     * 删除操作记录明细
     *
     * @param id 编号
     */
    void deleteUserOpLogs(Long id);

    /**
    * 批量删除操作记录明细
    *
    * @param ids 编号
    */
    void deleteUserOpLogsListByIds(List<Long> ids);

    /**
     * 获得操作记录明细
     *
     * @param id 编号
     * @return 操作记录明细
     */
    UserOpLogsDO getUserOpLogs(Long id);

    /**
     * 获得操作记录明细分页
     *
     * @param pageReqVO 分页查询
     * @return 操作记录明细分页
     */
    PageResult<UserOpLogsDO> getUserOpLogsPage(UserOpLogsPageReqVO pageReqVO);

}
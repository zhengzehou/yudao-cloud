package cn.iocoder.yudao.module.im.service.userlabel;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.im.controller.admin.userlabel.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.userlabel.UserLabelDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 人员分组标签 Service 接口
 *
 * @author 芋道源码
 */
public interface UserLabelService {

    /**
     * 创建人员分组标签
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserLabel(@Valid UserLabelSaveReqVO createReqVO);

    /**
     * 更新人员分组标签
     *
     * @param updateReqVO 更新信息
     */
    void updateUserLabel(@Valid UserLabelSaveReqVO updateReqVO);

    /**
     * 删除人员分组标签
     *
     * @param id 编号
     */
    void deleteUserLabel(Long id);

    /**
    * 批量删除人员分组标签
    *
    * @param ids 编号
    */
    void deleteUserLabelListByIds(List<Long> ids);

    /**
     * 获得人员分组标签
     *
     * @param id 编号
     * @return 人员分组标签
     */
    UserLabelDO getUserLabel(Long id);

    /**
     * 获得人员分组标签分页
     *
     * @param pageReqVO 分页查询
     * @return 人员分组标签分页
     */
    PageResult<UserLabelDO> getUserLabelPage(UserLabelPageReqVO pageReqVO);

}
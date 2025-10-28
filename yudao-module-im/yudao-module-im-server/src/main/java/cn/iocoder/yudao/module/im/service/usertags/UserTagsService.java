package cn.iocoder.yudao.module.im.service.usertags;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.im.controller.admin.usertags.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.usertags.UserTagsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 用户标签库 Service 接口
 *
 * @author 芋道源码
 */
public interface UserTagsService {

    /**
     * 创建用户标签库
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserTags(@Valid UserTagsSaveReqVO createReqVO);

    /**
     * 更新用户标签库
     *
     * @param updateReqVO 更新信息
     */
    void updateUserTags(@Valid UserTagsSaveReqVO updateReqVO);

    /**
     * 删除用户标签库
     *
     * @param id 编号
     */
    void deleteUserTags(Long id);

    /**
    * 批量删除用户标签库
    *
    * @param ids 编号
    */
    void deleteUserTagsListByIds(List<Long> ids);

    /**
     * 获得用户标签库
     *
     * @param id 编号
     * @return 用户标签库
     */
    UserTagsDO getUserTags(Long id);

    /**
     * 获得用户标签库分页
     *
     * @param pageReqVO 分页查询
     * @return 用户标签库分页
     */
    PageResult<UserTagsDO> getUserTagsPage(UserTagsPageReqVO pageReqVO);

}
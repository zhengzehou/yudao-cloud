package cn.iocoder.yudao.module.im.service.userusedavatar;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.im.controller.admin.userusedavatar.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.userusedavatar.UserUsedAvatarDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 历史头像 Service 接口
 *
 * @author 芋道源码
 */
public interface UserUsedAvatarService {

    /**
     * 创建历史头像
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserUsedAvatar(@Valid UserUsedAvatarSaveReqVO createReqVO);

    /**
     * 更新历史头像
     *
     * @param updateReqVO 更新信息
     */
    void updateUserUsedAvatar(@Valid UserUsedAvatarSaveReqVO updateReqVO);

    /**
     * 删除历史头像
     *
     * @param id 编号
     */
    void deleteUserUsedAvatar(Long id);

    /**
    * 批量删除历史头像
    *
    * @param ids 编号
    */
    void deleteUserUsedAvatarListByIds(List<Long> ids);

    /**
     * 获得历史头像
     *
     * @param id 编号
     * @return 历史头像
     */
    UserUsedAvatarDO getUserUsedAvatar(Long id);

    /**
     * 获得历史头像分页
     *
     * @param pageReqVO 分页查询
     * @return 历史头像分页
     */
    PageResult<UserUsedAvatarDO> getUserUsedAvatarPage(UserUsedAvatarPageReqVO pageReqVO);

}
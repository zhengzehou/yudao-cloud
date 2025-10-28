package cn.iocoder.yudao.module.im.service.usedavatar;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.im.controller.admin.usedavatar.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.usedavatar.UsedAvatarDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 历史头像 Service 接口
 *
 * @author 芋道源码
 */
public interface UsedAvatarService {

    /**
     * 创建历史头像
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUsedAvatar(@Valid UsedAvatarSaveReqVO createReqVO);

    /**
     * 更新历史头像
     *
     * @param updateReqVO 更新信息
     */
    void updateUsedAvatar(@Valid UsedAvatarSaveReqVO updateReqVO);

    /**
     * 删除历史头像
     *
     * @param id 编号
     */
    void deleteUsedAvatar(Long id);

    /**
    * 批量删除历史头像
    *
    * @param ids 编号
    */
    void deleteUsedAvatarListByIds(List<Long> ids);

    /**
     * 获得历史头像
     *
     * @param id 编号
     * @return 历史头像
     */
    UsedAvatarDO getUsedAvatar(Long id);

    /**
     * 获得历史头像分页
     *
     * @param pageReqVO 分页查询
     * @return 历史头像分页
     */
    PageResult<UsedAvatarDO> getUsedAvatarPage(UsedAvatarPageReqVO pageReqVO);

}
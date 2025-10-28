package cn.iocoder.yudao.module.im.service.userfavorite;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.im.controller.admin.userfavorite.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.userfavorite.UserFavoriteDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 我的收藏 Service 接口
 *
 * @author 芋道源码
 */
public interface UserFavoriteService {

    /**
     * 创建我的收藏
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserFavorite(@Valid UserFavoriteSaveReqVO createReqVO);

    /**
     * 更新我的收藏
     *
     * @param updateReqVO 更新信息
     */
    void updateUserFavorite(@Valid UserFavoriteSaveReqVO updateReqVO);

    /**
     * 删除我的收藏
     *
     * @param id 编号
     */
    void deleteUserFavorite(Long id);

    /**
    * 批量删除我的收藏
    *
    * @param ids 编号
    */
    void deleteUserFavoriteListByIds(List<Long> ids);

    /**
     * 获得我的收藏
     *
     * @param id 编号
     * @return 我的收藏
     */
    UserFavoriteDO getUserFavorite(Long id);

    /**
     * 获得我的收藏分页
     *
     * @param pageReqVO 分页查询
     * @return 我的收藏分页
     */
    PageResult<UserFavoriteDO> getUserFavoritePage(UserFavoritePageReqVO pageReqVO);

}
package cn.iocoder.yudao.module.im.service.userfollow;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.im.controller.admin.userfollow.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.userfollow.UserFollowDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 我的关注 Service 接口
 *
 * @author 芋道源码
 */
public interface UserFollowService {

    /**
     * 创建我的关注
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserFollow(@Valid UserFollowSaveReqVO createReqVO);

    /**
     * 更新我的关注
     *
     * @param updateReqVO 更新信息
     */
    void updateUserFollow(@Valid UserFollowSaveReqVO updateReqVO);

    /**
     * 删除我的关注
     *
     * @param id 编号
     */
    void deleteUserFollow(Long id);

    /**
    * 批量删除我的关注
    *
    * @param ids 编号
    */
    void deleteUserFollowListByIds(List<Long> ids);

    /**
     * 获得我的关注
     *
     * @param id 编号
     * @return 我的关注
     */
    UserFollowDO getUserFollow(Long id);

    /**
     * 获得我的关注分页
     *
     * @param pageReqVO 分页查询
     * @return 我的关注分页
     */
    PageResult<UserFollowDO> getUserFollowPage(UserFollowPageReqVO pageReqVO);

}
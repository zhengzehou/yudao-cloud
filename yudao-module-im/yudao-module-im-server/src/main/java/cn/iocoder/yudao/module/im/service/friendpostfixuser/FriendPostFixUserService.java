package cn.iocoder.yudao.module.im.service.friendpostfixuser;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.im.controller.admin.friendpostfixuser.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.friendpostfixuser.FriendPostFixUserDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 朋友圈特定人员 Service 接口
 *
 * @author 芋道源码
 */
public interface FriendPostFixUserService {

    /**
     * 创建朋友圈特定人员
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFriendPostFixUser(@Valid FriendPostFixUserSaveReqVO createReqVO);

    /**
     * 更新朋友圈特定人员
     *
     * @param updateReqVO 更新信息
     */
    void updateFriendPostFixUser(@Valid FriendPostFixUserSaveReqVO updateReqVO);

    /**
     * 删除朋友圈特定人员
     *
     * @param id 编号
     */
    void deleteFriendPostFixUser(Long id);

    /**
    * 批量删除朋友圈特定人员
    *
    * @param ids 编号
    */
    void deleteFriendPostFixUserListByIds(List<Long> ids);

    /**
     * 获得朋友圈特定人员
     *
     * @param id 编号
     * @return 朋友圈特定人员
     */
    FriendPostFixUserDO getFriendPostFixUser(Long id);

    /**
     * 获得朋友圈特定人员分页
     *
     * @param pageReqVO 分页查询
     * @return 朋友圈特定人员分页
     */
    PageResult<FriendPostFixUserDO> getFriendPostFixUserPage(FriendPostFixUserPageReqVO pageReqVO);

}
package cn.iocoder.yudao.module.im.service.friendpost;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.im.controller.admin.friendpost.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.friendpost.FriendPostDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 朋友圈动态 Service 接口
 *
 * @author 芋道源码
 */
public interface FriendPostService {

    /**
     * 创建朋友圈动态
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFriendPost(@Valid FriendPostSaveReqVO createReqVO);

    /**
     * 更新朋友圈动态
     *
     * @param updateReqVO 更新信息
     */
    void updateFriendPost(@Valid FriendPostSaveReqVO updateReqVO);

    /**
     * 删除朋友圈动态
     *
     * @param id 编号
     */
    void deleteFriendPost(Long id);

    /**
    * 批量删除朋友圈动态
    *
    * @param ids 编号
    */
    void deleteFriendPostListByIds(List<Long> ids);

    /**
     * 获得朋友圈动态
     *
     * @param id 编号
     * @return 朋友圈动态
     */
    FriendPostDO getFriendPost(Long id);

    /**
     * 获得朋友圈动态分页
     *
     * @param pageReqVO 分页查询
     * @return 朋友圈动态分页
     */
    PageResult<FriendPostDO> getFriendPostPage(FriendPostPageReqVO pageReqVO);

}
package cn.iocoder.yudao.module.im.service.userfriends;

import java.util.*;

import cn.iocoder.yudao.module.im.controller.admin.userinfo.vo.UserInfoPageReqVO;
import cn.iocoder.yudao.module.im.controller.admin.userinfo.vo.UserInfoRespVO;
import cn.iocoder.yudao.module.im.controller.admin.userinfo.vo.UserSearchPageReqVO;
import cn.iocoder.yudao.module.im.dal.dataobject.userfriends.UserFriendsApplyDO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.im.controller.admin.userfriends.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.userfriends.UserFriendsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 我的好友 Service 接口
 *
 * @author 芋道源码
 */
public interface UserFriendsService {

    /**
     * 创建我的好友
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserFriends(@Valid UserFriendsSaveReqVO createReqVO);
    Long createUserFriendsApply(Long userId, Long targetUserId,String msg);
    Integer handleUserFriendsApply(UserFriendsApplyDO applyDO,Integer status);
    /**
     * 更新我的好友
     *
     * @param updateReqVO 更新信息
     */
    void updateUserFriends(@Valid UserFriendsSaveReqVO updateReqVO);

    /**
     * 删除我的好友
     *
     * @param id 编号
     */
    void deleteUserFriends(Long id);

    /**
    * 批量删除我的好友
    *
    * @param ids 编号
    */
    void deleteUserFriendsListByIds(List<Long> ids);

    /**
     * 获得我的好友
     *
     * @param id 编号
     * @return 我的好友
     */
    UserFriendsDO getUserFriends(Long id);

    /**
     * 获得我的好友分页
     *
     * @param pageReqVO 分页查询
     * @return 我的好友分页
     */
    PageResult<UserInfoRespVO> searchUserFriendsPage(UserSearchPageReqVO pageReqVO);
    PageResult<UserFriendsRespVO> getUserFriendsPage(UserInfoPageReqVO pageReqVO);
    PageResult<UserFriendsApplyRespVO> getUserFriendsApplyPage(UserFriendsPageReqVO pageReqVO);
    UserFriendsApplyDO getApplyInfo(Long id);

}
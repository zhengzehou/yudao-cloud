package cn.iocoder.yudao.module.im.service.userinfo;

import java.util.*;

import cn.iocoder.yudao.module.im.controller.app.chat.vo.UserVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.im.controller.admin.userinfo.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.userinfo.UserInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 用户信息 Service 接口
 *
 * @author 芋道源码
 */
public interface UserInfoService {

    /**
     * 创建用户信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserInfo(@Valid UserInfoSaveReqVO createReqVO);
    UserInfoDO createUser(String nickname, String avtar, String registerIp, Integer terminal);

    UserInfoDO createUserIfAbsent(String mobile, String registerIp, Integer terminal);

    /**
     * 更新用户信息
     *
     * @param updateReqVO 更新信息
     */
    void updateUserInfo(@Valid UserInfoSaveReqVO updateReqVO);

    /**
     * 删除用户信息
     *
     * @param id 编号
     */
    void deleteUserInfo(Long id);

    /**
    * 批量删除用户信息
    *
    * @param ids 编号
    */
    void deleteUserInfoListByIds(List<Long> ids);

    /**
     * 获得用户信息
     *
     * @param id 编号
     * @return 用户信息
     */
    UserInfoDO getUserInfo(Long id);

    List<UserInfoDO> getUserInfo(List<Long> ids);

    UserInfoDO getUserByMobile(String mobile);
    UserInfoDO getUserByEmail(String email);
    UserInfoDO getUserByUsername(String name);
    boolean isPasswordMatch(String rawPassword, String encodedPassword);

    /**
     * 获得用户信息分页
     *
     * @param pageReqVO 分页查询
     * @return 用户信息分页
     */
    PageResult<UserInfoDO> getUserInfoPage(UserInfoPageReqVO pageReqVO);

    PageResult<UserVO> selectFriendsPage(UserInfoPageReqVO pageReqVO);

}
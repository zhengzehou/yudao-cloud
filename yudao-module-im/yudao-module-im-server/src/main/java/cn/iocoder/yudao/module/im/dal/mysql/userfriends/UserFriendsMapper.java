package cn.iocoder.yudao.module.im.dal.mysql.userfriends;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.im.controller.admin.userinfo.vo.UserInfoRespVO;
import cn.iocoder.yudao.module.im.controller.app.chat.vo.UserVO;
import cn.iocoder.yudao.module.im.dal.dataobject.userfriends.UserFriendsDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.im.controller.admin.userfriends.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 我的好友 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface UserFriendsMapper extends BaseMapperX<UserFriendsDO> {

    default PageResult<UserFriendsDO> selectPage(UserFriendsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserFriendsDO>()
                .eqIfPresent(UserFriendsDO::getUserId, reqVO.getUserId())
                .eqIfPresent(UserFriendsDO::getFriendId, reqVO.getFriendId())
                .eqIfPresent(UserFriendsDO::getFriendSource, reqVO.getFriendSource())
                .eqIfPresent(UserFriendsDO::getRecUserId, reqVO.getRecUserId())
                .eqIfPresent(UserFriendsDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(UserFriendsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserFriendsDO::getId));
    }

    @Select("SELECT u.* FROM IM_USER_INFO u " +
            "where u.DELETED  = 0  AND (u.ID = #{userId} OR u.NICK_NAME = #{keyword} OR u.EMAIL = #{keyword} OR u.PHONE = #{keyword} OR u.USERNAME = #{keyword})"  )
    IPage<UserInfoRespVO> selectFriendsPageByKeyword(IPage<UserInfoRespVO> page, @Param("userId") Long userId, @Param("keyword") String keyword);

    @Select("<script> " +
            "SELECT u.USERNAME,u.NAME,u.NICK_NAME,u.FIRST_CHAR,u.AVATAR,u.AGE,u.BIRTHDAY,u.AREA,f.* FROM IM_USER_INFO u inner join IM_USER_FRIENDS f on u.ID = f.USER_ID  " +
            "where f.DELETED  = 0 and f.USER_ID = #{userId} " +
            "<if test='name != null and name != \"\"'>" +
            " AND u.NICK_NAME LIKE CONCAT(#{name}, '%')" +
            "</if></script>" )
    IPage<UserFriendsRespVO> selectFriendsPage(IPage<UserFriendsRespVO> page, @Param("userId") Long userId, @Param("name") String name);

    @Select("SELECT u.USERNAME,u.NAME,u.NICK_NAME,u.FIRST_CHAR,u.AVATAR,u.AGE,u.BIRTHDAY,u.AREA,f.* FROM IM_USER_INFO u inner join IM_USER_FRIENDS_APPLY f on u.ID = f.FRIEND_ID  where f.DELETED  = 0 and f.USER_ID = #{userId}")
    IPage<UserFriendsApplyRespVO> selectFriendsApplyPage(IPage<UserFriendsApplyRespVO> page, @Param("userId") Long userId);

}
package cn.iocoder.yudao.module.im.dal.mysql.userinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.im.controller.app.chat.vo.UserVO;
import cn.iocoder.yudao.module.im.dal.dataobject.userinfo.UserInfoDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.im.controller.admin.userinfo.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface UserInfoMapper extends BaseMapperX<UserInfoDO> {

    default PageResult<UserInfoDO> selectPage(UserInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserInfoDO>()
                .eqIfPresent(UserInfoDO::getUsername, reqVO.getUsername())
                .eqIfPresent(UserInfoDO::getPwd, reqVO.getPwd())
                .likeIfPresent(UserInfoDO::getName, reqVO.getName())
                .likeIfPresent(UserInfoDO::getNickName, reqVO.getNickName())
                .eqIfPresent(UserInfoDO::getFirstChar, reqVO.getFirstChar())
                .eqIfPresent(UserInfoDO::getAvatar, reqVO.getAvatar())
                .eqIfPresent(UserInfoDO::getGender, reqVO.getSex())
                .eqIfPresent(UserInfoDO::getAge, reqVO.getAge())
                .eqIfPresent(UserInfoDO::getBirthday, reqVO.getBirthday())
                .eqIfPresent(UserInfoDO::getSlogan, reqVO.getSign())
                .eqIfPresent(UserInfoDO::getPhone, reqVO.getPhone())
                .eqIfPresent(UserInfoDO::getEmail, reqVO.getEmail())
                .eqIfPresent(UserInfoDO::getArea, reqVO.getArea())
                .eqIfPresent(UserInfoDO::getDefBell, reqVO.getDefBell())
                .eqIfPresent(UserInfoDO::getAccSource, reqVO.getAccSource())
                .eqIfPresent(UserInfoDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(UserInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserInfoDO::getId));
    }

    @Select("SELECT u.ID userId,u.AVATAR,u.NICK_NAME,u.STATUS type, u.DELETED activeStatus FROM IM_USER_INFO u inner join IM_USER_FRIENDS f  on u.ID = f.USER_ID where f.DELETED  = 0 and f.USER_ID = #{userId}")
    IPage<UserVO> selectFriendsPage(IPage<UserVO> page, @Param("userId") Long userId);

    default UserInfoDO selectByMobile(String mobile) {
        return selectOne(UserInfoDO::getPhone, mobile);
    }
    default UserInfoDO selectByEmail(String email) {
        return selectOne(UserInfoDO::getEmail, email);
    }


//    default UserInfoDO selectByName(String name) {
//        return selectOne(UserInfoDO::getName, name);
//    }

    default UserInfoDO selectByUsername(String username) {
        return selectOne(UserInfoDO::getUsername, username);
    }

    default List<UserInfoDO> selectListByNicknameLike(String nickname) {
        return selectList(new LambdaQueryWrapperX<UserInfoDO>()
                .likeIfPresent(UserInfoDO::getNickName, nickname));
    }

//    default PageResult<UserInfoDO> selectPage(MemberUserPageReqVO reqVO) {
//        // 处理 tagIds 过滤条件
//        String tagIdSql = "";
//        if (CollUtil.isNotEmpty(reqVO.getTagIds())) {
//            tagIdSql = reqVO.getTagIds().stream()
//                    .map(tagId -> "FIND_IN_SET(" + tagId + ", tag_ids)")
//                    .collect(Collectors.joining(" OR "));
//        }
//        // 分页查询
//        return selectPage(reqVO, new LambdaQueryWrapperX<UserInfoDO>()
//                .likeIfPresent(UserInfoDO::getPhone, reqVO.getMobile())
//                .betweenIfPresent(UserInfoDO::getLoginDate, reqVO.getLoginDate())
//                .likeIfPresent(UserInfoDO::getNickName, reqVO.getNickname())
//                .betweenIfPresent(UserInfoDO::getCreateTime, reqVO.getCreateTime())
//                .eqIfPresent(UserInfoDO::getLevelId, reqVO.getLevelId())
//                .eqIfPresent(UserInfoDO::getGroupId, reqVO.getGroupId())
//                .apply(StrUtil.isNotEmpty(tagIdSql), tagIdSql)
//                .orderByDesc(UserInfoDO::getId));
//    }

//    default Long selectCountByGroupId(Long groupId) {
//        return selectCount(MemberUserDO::getGroupId, groupId);
//    }
//
//    default Long selectCountByLevelId(Long levelId) {
//        return selectCount(MemberUserDO::getLevelId, levelId);
//    }
//
//    default Long selectCountByTagId(Long tagId) {
//        return selectCount(new LambdaQueryWrapperX<UserInfoDO>()
//                .apply("FIND_IN_SET({0}, tag_ids)", tagId));
//    }

}
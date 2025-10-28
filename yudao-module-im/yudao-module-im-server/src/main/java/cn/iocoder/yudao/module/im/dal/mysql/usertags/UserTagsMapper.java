package cn.iocoder.yudao.module.im.dal.mysql.usertags;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.im.dal.dataobject.usertags.UserTagsDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.im.controller.admin.usertags.vo.*;

/**
 * 用户标签库 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface UserTagsMapper extends BaseMapperX<UserTagsDO> {

    default PageResult<UserTagsDO> selectPage(UserTagsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserTagsDO>()
                .eqIfPresent(UserTagsDO::getUserId, reqVO.getUserId())
                .likeIfPresent(UserTagsDO::getTagName, reqVO.getTagName())
                .eqIfPresent(UserTagsDO::getTagDesc, reqVO.getTagDesc())
                .eqIfPresent(UserTagsDO::getSort, reqVO.getSort())
                .betweenIfPresent(UserTagsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserTagsDO::getId));
    }

}
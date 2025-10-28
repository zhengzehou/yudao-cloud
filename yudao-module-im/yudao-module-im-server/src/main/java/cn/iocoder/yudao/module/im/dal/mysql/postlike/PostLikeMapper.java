package cn.iocoder.yudao.module.im.dal.mysql.postlike;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.im.dal.dataobject.postlike.PostLikeDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.im.controller.admin.postlike.vo.*;

/**
 * 朋友圈点赞和踩 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PostLikeMapper extends BaseMapperX<PostLikeDO> {

    default PageResult<PostLikeDO> selectPage(PostLikePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PostLikeDO>()
                .eqIfPresent(PostLikeDO::getUserId, reqVO.getUserId())
                .eqIfPresent(PostLikeDO::getPostId, reqVO.getPostId())
                .eqIfPresent(PostLikeDO::getType, reqVO.getType())
                .eqIfPresent(PostLikeDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(PostLikeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PostLikeDO::getId));
    }

}
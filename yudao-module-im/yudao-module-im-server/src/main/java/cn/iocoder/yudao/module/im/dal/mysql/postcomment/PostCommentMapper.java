package cn.iocoder.yudao.module.im.dal.mysql.postcomment;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.im.dal.dataobject.postcomment.PostCommentDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.im.controller.admin.postcomment.vo.*;

/**
 * 朋友圈评论列 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PostCommentMapper extends BaseMapperX<PostCommentDO> {

    default PageResult<PostCommentDO> selectPage(PostCommentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PostCommentDO>()
                .eqIfPresent(PostCommentDO::getUserId, reqVO.getUserId())
                .eqIfPresent(PostCommentDO::getPostId, reqVO.getPostId())
                .eqIfPresent(PostCommentDO::getContent, reqVO.getContent())
                .eqIfPresent(PostCommentDO::getUpperId, reqVO.getUpperId())
                .eqIfPresent(PostCommentDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(PostCommentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PostCommentDO::getId));
    }

}
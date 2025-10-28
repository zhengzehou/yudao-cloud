package cn.iocoder.yudao.module.im.service.postcomment;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.im.controller.admin.postcomment.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.postcomment.PostCommentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.im.dal.mysql.postcomment.PostCommentMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 朋友圈评论列 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PostCommentServiceImpl implements PostCommentService {

    @Resource
    private PostCommentMapper postCommentMapper;

    @Override
    public Long createPostComment(PostCommentSaveReqVO createReqVO) {
        // 插入
        PostCommentDO postComment = BeanUtils.toBean(createReqVO, PostCommentDO.class);
        postCommentMapper.insert(postComment);

        // 返回
        return postComment.getId();
    }

    @Override
    public void updatePostComment(PostCommentSaveReqVO updateReqVO) {
        // 校验存在
        validatePostCommentExists(updateReqVO.getId());
        // 更新
        PostCommentDO updateObj = BeanUtils.toBean(updateReqVO, PostCommentDO.class);
        postCommentMapper.updateById(updateObj);
    }

    @Override
    public void deletePostComment(Long id) {
        // 校验存在
        validatePostCommentExists(id);
        // 删除
        postCommentMapper.deleteById(id);
    }

    @Override
        public void deletePostCommentListByIds(List<Long> ids) {
        // 删除
        postCommentMapper.deleteByIds(ids);
        }


    private void validatePostCommentExists(Long id) {
        if (postCommentMapper.selectById(id) == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND);
        }
    }

    @Override
    public PostCommentDO getPostComment(Long id) {
        return postCommentMapper.selectById(id);
    }

    @Override
    public PageResult<PostCommentDO> getPostCommentPage(PostCommentPageReqVO pageReqVO) {
        return postCommentMapper.selectPage(pageReqVO);
    }

}
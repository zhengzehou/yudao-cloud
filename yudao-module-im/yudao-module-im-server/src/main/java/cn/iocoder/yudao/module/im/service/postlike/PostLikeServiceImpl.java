package cn.iocoder.yudao.module.im.service.postlike;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.im.controller.admin.postlike.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.postlike.PostLikeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.im.dal.mysql.postlike.PostLikeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 朋友圈点赞和踩 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PostLikeServiceImpl implements PostLikeService {

    @Resource
    private PostLikeMapper postLikeMapper;

    @Override
    public Long createPostLike(PostLikeSaveReqVO createReqVO) {
        // 插入
        PostLikeDO postLike = BeanUtils.toBean(createReqVO, PostLikeDO.class);
        postLikeMapper.insert(postLike);

        // 返回
        return postLike.getId();
    }

    @Override
    public void updatePostLike(PostLikeSaveReqVO updateReqVO) {
        // 校验存在
        validatePostLikeExists(updateReqVO.getId());
        // 更新
        PostLikeDO updateObj = BeanUtils.toBean(updateReqVO, PostLikeDO.class);
        postLikeMapper.updateById(updateObj);
    }

    @Override
    public void deletePostLike(Long id) {
        // 校验存在
        validatePostLikeExists(id);
        // 删除
        postLikeMapper.deleteById(id);
    }

    @Override
        public void deletePostLikeListByIds(List<Long> ids) {
        // 删除
        postLikeMapper.deleteByIds(ids);
        }


    private void validatePostLikeExists(Long id) {
        if (postLikeMapper.selectById(id) == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND);
        }
    }

    @Override
    public PostLikeDO getPostLike(Long id) {
        return postLikeMapper.selectById(id);
    }

    @Override
    public PageResult<PostLikeDO> getPostLikePage(PostLikePageReqVO pageReqVO) {
        return postLikeMapper.selectPage(pageReqVO);
    }

}
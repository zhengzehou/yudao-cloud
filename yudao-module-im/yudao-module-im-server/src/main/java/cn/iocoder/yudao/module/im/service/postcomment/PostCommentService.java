package cn.iocoder.yudao.module.im.service.postcomment;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.im.controller.admin.postcomment.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.postcomment.PostCommentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 朋友圈评论列 Service 接口
 *
 * @author 芋道源码
 */
public interface PostCommentService {

    /**
     * 创建朋友圈评论列
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPostComment(@Valid PostCommentSaveReqVO createReqVO);

    /**
     * 更新朋友圈评论列
     *
     * @param updateReqVO 更新信息
     */
    void updatePostComment(@Valid PostCommentSaveReqVO updateReqVO);

    /**
     * 删除朋友圈评论列
     *
     * @param id 编号
     */
    void deletePostComment(Long id);

    /**
    * 批量删除朋友圈评论列
    *
    * @param ids 编号
    */
    void deletePostCommentListByIds(List<Long> ids);

    /**
     * 获得朋友圈评论列
     *
     * @param id 编号
     * @return 朋友圈评论列
     */
    PostCommentDO getPostComment(Long id);

    /**
     * 获得朋友圈评论列分页
     *
     * @param pageReqVO 分页查询
     * @return 朋友圈评论列分页
     */
    PageResult<PostCommentDO> getPostCommentPage(PostCommentPageReqVO pageReqVO);

}
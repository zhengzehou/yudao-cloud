package cn.iocoder.yudao.module.im.service.postlike;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.im.controller.admin.postlike.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.postlike.PostLikeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 朋友圈点赞和踩 Service 接口
 *
 * @author 芋道源码
 */
public interface PostLikeService {

    /**
     * 创建朋友圈点赞和踩
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPostLike(@Valid PostLikeSaveReqVO createReqVO);

    /**
     * 更新朋友圈点赞和踩
     *
     * @param updateReqVO 更新信息
     */
    void updatePostLike(@Valid PostLikeSaveReqVO updateReqVO);

    /**
     * 删除朋友圈点赞和踩
     *
     * @param id 编号
     */
    void deletePostLike(Long id);

    /**
    * 批量删除朋友圈点赞和踩
    *
    * @param ids 编号
    */
    void deletePostLikeListByIds(List<Long> ids);

    /**
     * 获得朋友圈点赞和踩
     *
     * @param id 编号
     * @return 朋友圈点赞和踩
     */
    PostLikeDO getPostLike(Long id);

    /**
     * 获得朋友圈点赞和踩分页
     *
     * @param pageReqVO 分页查询
     * @return 朋友圈点赞和踩分页
     */
    PageResult<PostLikeDO> getPostLikePage(PostLikePageReqVO pageReqVO);

}
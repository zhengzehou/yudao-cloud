package cn.iocoder.yudao.module.im.controller.admin.postcomment;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.im.controller.admin.postcomment.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.postcomment.PostCommentDO;
import cn.iocoder.yudao.module.im.service.postcomment.PostCommentService;

@Tag(name = "管理后台 - 朋友圈评论列")
@RestController
@RequestMapping("/im/post-comment")
@Validated
public class PostCommentController {

    @Resource
    private PostCommentService postCommentService;

    @PostMapping("/create")
    @Operation(summary = "创建朋友圈评论列")
    @PreAuthorize("@ss.hasPermission('im:post-comment:create')")
    public CommonResult<Long> createPostComment(@Valid @RequestBody PostCommentSaveReqVO createReqVO) {
        return success(postCommentService.createPostComment(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新朋友圈评论列")
    @PreAuthorize("@ss.hasPermission('im:post-comment:update')")
    public CommonResult<Boolean> updatePostComment(@Valid @RequestBody PostCommentSaveReqVO updateReqVO) {
        postCommentService.updatePostComment(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除朋友圈评论列")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('im:post-comment:delete')")
    public CommonResult<Boolean> deletePostComment(@RequestParam("id") Long id) {
        postCommentService.deletePostComment(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除朋友圈评论列")
                @PreAuthorize("@ss.hasPermission('im:post-comment:delete')")
    public CommonResult<Boolean> deletePostCommentList(@RequestParam("ids") List<Long> ids) {
        postCommentService.deletePostCommentListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得朋友圈评论列")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('im:post-comment:query')")
    public CommonResult<PostCommentRespVO> getPostComment(@RequestParam("id") Long id) {
        PostCommentDO postComment = postCommentService.getPostComment(id);
        return success(BeanUtils.toBean(postComment, PostCommentRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得朋友圈评论列分页")
    @PreAuthorize("@ss.hasPermission('im:post-comment:query')")
    public CommonResult<PageResult<PostCommentRespVO>> getPostCommentPage(@Valid PostCommentPageReqVO pageReqVO) {
        PageResult<PostCommentDO> pageResult = postCommentService.getPostCommentPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PostCommentRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出朋友圈评论列 Excel")
    @PreAuthorize("@ss.hasPermission('im:post-comment:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPostCommentExcel(@Valid PostCommentPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PostCommentDO> list = postCommentService.getPostCommentPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "朋友圈评论列.xls", "数据", PostCommentRespVO.class,
                        BeanUtils.toBean(list, PostCommentRespVO.class));
    }

}
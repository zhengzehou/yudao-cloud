package cn.iocoder.yudao.module.im.controller.admin.postlike;

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

import cn.iocoder.yudao.module.im.controller.admin.postlike.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.postlike.PostLikeDO;
import cn.iocoder.yudao.module.im.service.postlike.PostLikeService;

@Tag(name = "管理后台 - 朋友圈点赞和踩")
@RestController
@RequestMapping("/im/post-like")
@Validated
public class PostLikeController {

    @Resource
    private PostLikeService postLikeService;

    @PostMapping("/create")
    @Operation(summary = "创建朋友圈点赞和踩")
    @PreAuthorize("@ss.hasPermission('im:post-like:create')")
    public CommonResult<Long> createPostLike(@Valid @RequestBody PostLikeSaveReqVO createReqVO) {
        return success(postLikeService.createPostLike(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新朋友圈点赞和踩")
    @PreAuthorize("@ss.hasPermission('im:post-like:update')")
    public CommonResult<Boolean> updatePostLike(@Valid @RequestBody PostLikeSaveReqVO updateReqVO) {
        postLikeService.updatePostLike(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除朋友圈点赞和踩")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('im:post-like:delete')")
    public CommonResult<Boolean> deletePostLike(@RequestParam("id") Long id) {
        postLikeService.deletePostLike(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除朋友圈点赞和踩")
                @PreAuthorize("@ss.hasPermission('im:post-like:delete')")
    public CommonResult<Boolean> deletePostLikeList(@RequestParam("ids") List<Long> ids) {
        postLikeService.deletePostLikeListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得朋友圈点赞和踩")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('im:post-like:query')")
    public CommonResult<PostLikeRespVO> getPostLike(@RequestParam("id") Long id) {
        PostLikeDO postLike = postLikeService.getPostLike(id);
        return success(BeanUtils.toBean(postLike, PostLikeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得朋友圈点赞和踩分页")
    @PreAuthorize("@ss.hasPermission('im:post-like:query')")
    public CommonResult<PageResult<PostLikeRespVO>> getPostLikePage(@Valid PostLikePageReqVO pageReqVO) {
        PageResult<PostLikeDO> pageResult = postLikeService.getPostLikePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PostLikeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出朋友圈点赞和踩 Excel")
    @PreAuthorize("@ss.hasPermission('im:post-like:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPostLikeExcel(@Valid PostLikePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PostLikeDO> list = postLikeService.getPostLikePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "朋友圈点赞和踩.xls", "数据", PostLikeRespVO.class,
                        BeanUtils.toBean(list, PostLikeRespVO.class));
    }

}
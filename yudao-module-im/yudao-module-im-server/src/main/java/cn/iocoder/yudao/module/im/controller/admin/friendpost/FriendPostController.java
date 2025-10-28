package cn.iocoder.yudao.module.im.controller.admin.friendpost;

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

import cn.iocoder.yudao.module.im.controller.admin.friendpost.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.friendpost.FriendPostDO;
import cn.iocoder.yudao.module.im.service.friendpost.FriendPostService;

@Tag(name = "管理后台 - 朋友圈动态")
@RestController
@RequestMapping("/im/friend-post")
@Validated
public class FriendPostController {

    @Resource
    private FriendPostService friendPostService;

    @PostMapping("/create")
    @Operation(summary = "创建朋友圈动态")
    @PreAuthorize("@ss.hasPermission('im:friend-post:create')")
    public CommonResult<Long> createFriendPost(@Valid @RequestBody FriendPostSaveReqVO createReqVO) {
        return success(friendPostService.createFriendPost(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新朋友圈动态")
    @PreAuthorize("@ss.hasPermission('im:friend-post:update')")
    public CommonResult<Boolean> updateFriendPost(@Valid @RequestBody FriendPostSaveReqVO updateReqVO) {
        friendPostService.updateFriendPost(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除朋友圈动态")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('im:friend-post:delete')")
    public CommonResult<Boolean> deleteFriendPost(@RequestParam("id") Long id) {
        friendPostService.deleteFriendPost(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除朋友圈动态")
                @PreAuthorize("@ss.hasPermission('im:friend-post:delete')")
    public CommonResult<Boolean> deleteFriendPostList(@RequestParam("ids") List<Long> ids) {
        friendPostService.deleteFriendPostListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得朋友圈动态")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('im:friend-post:query')")
    public CommonResult<FriendPostRespVO> getFriendPost(@RequestParam("id") Long id) {
        FriendPostDO friendPost = friendPostService.getFriendPost(id);
        return success(BeanUtils.toBean(friendPost, FriendPostRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得朋友圈动态分页")
    @PreAuthorize("@ss.hasPermission('im:friend-post:query')")
    public CommonResult<PageResult<FriendPostRespVO>> getFriendPostPage(@Valid FriendPostPageReqVO pageReqVO) {
        PageResult<FriendPostDO> pageResult = friendPostService.getFriendPostPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, FriendPostRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出朋友圈动态 Excel")
    @PreAuthorize("@ss.hasPermission('im:friend-post:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFriendPostExcel(@Valid FriendPostPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<FriendPostDO> list = friendPostService.getFriendPostPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "朋友圈动态.xls", "数据", FriendPostRespVO.class,
                        BeanUtils.toBean(list, FriendPostRespVO.class));
    }

}
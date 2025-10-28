package cn.iocoder.yudao.module.im.controller.admin.usertags;

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

import cn.iocoder.yudao.module.im.controller.admin.usertags.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.usertags.UserTagsDO;
import cn.iocoder.yudao.module.im.service.usertags.UserTagsService;

@Tag(name = "管理后台 - 用户标签库")
@RestController
@RequestMapping("/im/user-tags")
@Validated
public class UserTagsController {

    @Resource
    private UserTagsService userTagsService;

    @PostMapping("/create")
    @Operation(summary = "创建用户标签库")
    @PreAuthorize("@ss.hasPermission('im:user-tags:create')")
    public CommonResult<Long> createUserTags(@Valid @RequestBody UserTagsSaveReqVO createReqVO) {
        return success(userTagsService.createUserTags(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户标签库")
    @PreAuthorize("@ss.hasPermission('im:user-tags:update')")
    public CommonResult<Boolean> updateUserTags(@Valid @RequestBody UserTagsSaveReqVO updateReqVO) {
        userTagsService.updateUserTags(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户标签库")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('im:user-tags:delete')")
    public CommonResult<Boolean> deleteUserTags(@RequestParam("id") Long id) {
        userTagsService.deleteUserTags(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除用户标签库")
                @PreAuthorize("@ss.hasPermission('im:user-tags:delete')")
    public CommonResult<Boolean> deleteUserTagsList(@RequestParam("ids") List<Long> ids) {
        userTagsService.deleteUserTagsListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户标签库")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('im:user-tags:query')")
    public CommonResult<UserTagsRespVO> getUserTags(@RequestParam("id") Long id) {
        UserTagsDO userTags = userTagsService.getUserTags(id);
        return success(BeanUtils.toBean(userTags, UserTagsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用户标签库分页")
    @PreAuthorize("@ss.hasPermission('im:user-tags:query')")
    public CommonResult<PageResult<UserTagsRespVO>> getUserTagsPage(@Valid UserTagsPageReqVO pageReqVO) {
        PageResult<UserTagsDO> pageResult = userTagsService.getUserTagsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UserTagsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出用户标签库 Excel")
    @PreAuthorize("@ss.hasPermission('im:user-tags:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUserTagsExcel(@Valid UserTagsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UserTagsDO> list = userTagsService.getUserTagsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "用户标签库.xls", "数据", UserTagsRespVO.class,
                        BeanUtils.toBean(list, UserTagsRespVO.class));
    }

}
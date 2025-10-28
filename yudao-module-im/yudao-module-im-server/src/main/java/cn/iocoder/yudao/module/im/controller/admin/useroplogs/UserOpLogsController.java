package cn.iocoder.yudao.module.im.controller.admin.useroplogs;

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

import cn.iocoder.yudao.module.im.controller.admin.useroplogs.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.useroplogs.UserOpLogsDO;
import cn.iocoder.yudao.module.im.service.useroplogs.UserOpLogsService;

@Tag(name = "管理后台 - 操作记录明细")
@RestController
@RequestMapping("/im/user-op-logs")
@Validated
public class UserOpLogsController {

    @Resource
    private UserOpLogsService userOpLogsService;

    @PostMapping("/create")
    @Operation(summary = "创建操作记录明细")
    @PreAuthorize("@ss.hasPermission('im:user-op-logs:create')")
    public CommonResult<Long> createUserOpLogs(@Valid @RequestBody UserOpLogsSaveReqVO createReqVO) {
        return success(userOpLogsService.createUserOpLogs(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新操作记录明细")
    @PreAuthorize("@ss.hasPermission('im:user-op-logs:update')")
    public CommonResult<Boolean> updateUserOpLogs(@Valid @RequestBody UserOpLogsSaveReqVO updateReqVO) {
        userOpLogsService.updateUserOpLogs(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除操作记录明细")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('im:user-op-logs:delete')")
    public CommonResult<Boolean> deleteUserOpLogs(@RequestParam("id") Long id) {
        userOpLogsService.deleteUserOpLogs(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除操作记录明细")
                @PreAuthorize("@ss.hasPermission('im:user-op-logs:delete')")
    public CommonResult<Boolean> deleteUserOpLogsList(@RequestParam("ids") List<Long> ids) {
        userOpLogsService.deleteUserOpLogsListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得操作记录明细")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('im:user-op-logs:query')")
    public CommonResult<UserOpLogsRespVO> getUserOpLogs(@RequestParam("id") Long id) {
        UserOpLogsDO userOpLogs = userOpLogsService.getUserOpLogs(id);
        return success(BeanUtils.toBean(userOpLogs, UserOpLogsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得操作记录明细分页")
    @PreAuthorize("@ss.hasPermission('im:user-op-logs:query')")
    public CommonResult<PageResult<UserOpLogsRespVO>> getUserOpLogsPage(@Valid UserOpLogsPageReqVO pageReqVO) {
        PageResult<UserOpLogsDO> pageResult = userOpLogsService.getUserOpLogsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UserOpLogsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出操作记录明细 Excel")
    @PreAuthorize("@ss.hasPermission('im:user-op-logs:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUserOpLogsExcel(@Valid UserOpLogsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UserOpLogsDO> list = userOpLogsService.getUserOpLogsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "操作记录明细.xls", "数据", UserOpLogsRespVO.class,
                        BeanUtils.toBean(list, UserOpLogsRespVO.class));
    }

}
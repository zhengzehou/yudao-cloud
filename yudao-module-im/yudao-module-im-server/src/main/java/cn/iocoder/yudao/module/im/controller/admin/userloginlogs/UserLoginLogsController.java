package cn.iocoder.yudao.module.im.controller.admin.userloginlogs;

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

import cn.iocoder.yudao.module.im.controller.admin.userloginlogs.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.userloginlogs.UserLoginLogsDO;
import cn.iocoder.yudao.module.im.service.userloginlogs.UserLoginLogsService;

@Tag(name = "管理后台 - 登录历史")
@RestController
@RequestMapping("/im/user-login-logs")
@Validated
public class UserLoginLogsController {

    @Resource
    private UserLoginLogsService userLoginLogsService;

    @PostMapping("/create")
    @Operation(summary = "创建登录历史")
    @PreAuthorize("@ss.hasPermission('im:user-login-logs:create')")
    public CommonResult<Long> createUserLoginLogs(@Valid @RequestBody UserLoginLogsSaveReqVO createReqVO) {
        return success(userLoginLogsService.createUserLoginLogs(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新登录历史")
    @PreAuthorize("@ss.hasPermission('im:user-login-logs:update')")
    public CommonResult<Boolean> updateUserLoginLogs(@Valid @RequestBody UserLoginLogsSaveReqVO updateReqVO) {
        userLoginLogsService.updateUserLoginLogs(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除登录历史")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('im:user-login-logs:delete')")
    public CommonResult<Boolean> deleteUserLoginLogs(@RequestParam("id") Long id) {
        userLoginLogsService.deleteUserLoginLogs(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除登录历史")
                @PreAuthorize("@ss.hasPermission('im:user-login-logs:delete')")
    public CommonResult<Boolean> deleteUserLoginLogsList(@RequestParam("ids") List<Long> ids) {
        userLoginLogsService.deleteUserLoginLogsListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得登录历史")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('im:user-login-logs:query')")
    public CommonResult<UserLoginLogsRespVO> getUserLoginLogs(@RequestParam("id") Long id) {
        UserLoginLogsDO userLoginLogs = userLoginLogsService.getUserLoginLogs(id);
        return success(BeanUtils.toBean(userLoginLogs, UserLoginLogsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得登录历史分页")
    @PreAuthorize("@ss.hasPermission('im:user-login-logs:query')")
    public CommonResult<PageResult<UserLoginLogsRespVO>> getUserLoginLogsPage(@Valid UserLoginLogsPageReqVO pageReqVO) {
        PageResult<UserLoginLogsDO> pageResult = userLoginLogsService.getUserLoginLogsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UserLoginLogsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出登录历史 Excel")
    @PreAuthorize("@ss.hasPermission('im:user-login-logs:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUserLoginLogsExcel(@Valid UserLoginLogsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UserLoginLogsDO> list = userLoginLogsService.getUserLoginLogsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "登录历史.xls", "数据", UserLoginLogsRespVO.class,
                        BeanUtils.toBean(list, UserLoginLogsRespVO.class));
    }

}
package cn.iocoder.yudao.module.im.controller.admin.userlabel;

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

import cn.iocoder.yudao.module.im.controller.admin.userlabel.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.userlabel.UserLabelDO;
import cn.iocoder.yudao.module.im.service.userlabel.UserLabelService;

@Tag(name = "管理后台 - 人员分组标签")
@RestController
@RequestMapping("/im/user-label")
@Validated
public class UserLabelController {

    @Resource
    private UserLabelService userLabelService;

    @PostMapping("/create")
    @Operation(summary = "创建人员分组标签")
    @PreAuthorize("@ss.hasPermission('im:user-label:create')")
    public CommonResult<Long> createUserLabel(@Valid @RequestBody UserLabelSaveReqVO createReqVO) {
        return success(userLabelService.createUserLabel(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新人员分组标签")
    @PreAuthorize("@ss.hasPermission('im:user-label:update')")
    public CommonResult<Boolean> updateUserLabel(@Valid @RequestBody UserLabelSaveReqVO updateReqVO) {
        userLabelService.updateUserLabel(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除人员分组标签")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('im:user-label:delete')")
    public CommonResult<Boolean> deleteUserLabel(@RequestParam("id") Long id) {
        userLabelService.deleteUserLabel(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除人员分组标签")
                @PreAuthorize("@ss.hasPermission('im:user-label:delete')")
    public CommonResult<Boolean> deleteUserLabelList(@RequestParam("ids") List<Long> ids) {
        userLabelService.deleteUserLabelListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得人员分组标签")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('im:user-label:query')")
    public CommonResult<UserLabelRespVO> getUserLabel(@RequestParam("id") Long id) {
        UserLabelDO userLabel = userLabelService.getUserLabel(id);
        return success(BeanUtils.toBean(userLabel, UserLabelRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得人员分组标签分页")
    @PreAuthorize("@ss.hasPermission('im:user-label:query')")
    public CommonResult<PageResult<UserLabelRespVO>> getUserLabelPage(@Valid UserLabelPageReqVO pageReqVO) {
        PageResult<UserLabelDO> pageResult = userLabelService.getUserLabelPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UserLabelRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出人员分组标签 Excel")
    @PreAuthorize("@ss.hasPermission('im:user-label:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUserLabelExcel(@Valid UserLabelPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UserLabelDO> list = userLabelService.getUserLabelPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "人员分组标签.xls", "数据", UserLabelRespVO.class,
                        BeanUtils.toBean(list, UserLabelRespVO.class));
    }

}
package cn.iocoder.yudao.module.im.controller.admin.usedavatar;

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

import cn.iocoder.yudao.module.im.controller.admin.usedavatar.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.usedavatar.UsedAvatarDO;
import cn.iocoder.yudao.module.im.service.usedavatar.UsedAvatarService;

@Tag(name = "管理后台 - 历史头像")
@RestController
@RequestMapping("/user/used-avatar")
@Validated
public class UsedAvatarController {

    @Resource
    private UsedAvatarService usedAvatarService;

    @PostMapping("/create")
    @Operation(summary = "创建历史头像")
    @PreAuthorize("@ss.hasPermission('user:used-avatar:create')")
    public CommonResult<Long> createUsedAvatar(@Valid @RequestBody UsedAvatarSaveReqVO createReqVO) {
        return success(usedAvatarService.createUsedAvatar(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新历史头像")
    @PreAuthorize("@ss.hasPermission('user:used-avatar:update')")
    public CommonResult<Boolean> updateUsedAvatar(@Valid @RequestBody UsedAvatarSaveReqVO updateReqVO) {
        usedAvatarService.updateUsedAvatar(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除历史头像")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('user:used-avatar:delete')")
    public CommonResult<Boolean> deleteUsedAvatar(@RequestParam("id") Long id) {
        usedAvatarService.deleteUsedAvatar(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除历史头像")
                @PreAuthorize("@ss.hasPermission('user:used-avatar:delete')")
    public CommonResult<Boolean> deleteUsedAvatarList(@RequestParam("ids") List<Long> ids) {
        usedAvatarService.deleteUsedAvatarListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得历史头像")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('user:used-avatar:query')")
    public CommonResult<UsedAvatarRespVO> getUsedAvatar(@RequestParam("id") Long id) {
        UsedAvatarDO usedAvatar = usedAvatarService.getUsedAvatar(id);
        return success(BeanUtils.toBean(usedAvatar, UsedAvatarRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得历史头像分页")
    @PreAuthorize("@ss.hasPermission('user:used-avatar:query')")
    public CommonResult<PageResult<UsedAvatarRespVO>> getUsedAvatarPage(@Valid UsedAvatarPageReqVO pageReqVO) {
        PageResult<UsedAvatarDO> pageResult = usedAvatarService.getUsedAvatarPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UsedAvatarRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出历史头像 Excel")
    @PreAuthorize("@ss.hasPermission('user:used-avatar:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUsedAvatarExcel(@Valid UsedAvatarPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UsedAvatarDO> list = usedAvatarService.getUsedAvatarPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "历史头像.xls", "数据", UsedAvatarRespVO.class,
                        BeanUtils.toBean(list, UsedAvatarRespVO.class));
    }

}
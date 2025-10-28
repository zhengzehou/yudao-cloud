package cn.iocoder.yudao.module.im.controller.admin.userusedavatar;

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

import cn.iocoder.yudao.module.im.controller.admin.userusedavatar.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.userusedavatar.UserUsedAvatarDO;
import cn.iocoder.yudao.module.im.service.userusedavatar.UserUsedAvatarService;

@Tag(name = "管理后台 - 历史头像")
@RestController
@RequestMapping("/im/user-used-avatar")
@Validated
public class UserUsedAvatarController {

    @Resource
    private UserUsedAvatarService userUsedAvatarService;

    @PostMapping("/create")
    @Operation(summary = "创建历史头像")
    @PreAuthorize("@ss.hasPermission('im:user-used-avatar:create')")
    public CommonResult<Long> createUserUsedAvatar(@Valid @RequestBody UserUsedAvatarSaveReqVO createReqVO) {
        return success(userUsedAvatarService.createUserUsedAvatar(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新历史头像")
    @PreAuthorize("@ss.hasPermission('im:user-used-avatar:update')")
    public CommonResult<Boolean> updateUserUsedAvatar(@Valid @RequestBody UserUsedAvatarSaveReqVO updateReqVO) {
        userUsedAvatarService.updateUserUsedAvatar(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除历史头像")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('im:user-used-avatar:delete')")
    public CommonResult<Boolean> deleteUserUsedAvatar(@RequestParam("id") Long id) {
        userUsedAvatarService.deleteUserUsedAvatar(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除历史头像")
                @PreAuthorize("@ss.hasPermission('im:user-used-avatar:delete')")
    public CommonResult<Boolean> deleteUserUsedAvatarList(@RequestParam("ids") List<Long> ids) {
        userUsedAvatarService.deleteUserUsedAvatarListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得历史头像")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('im:user-used-avatar:query')")
    public CommonResult<UserUsedAvatarRespVO> getUserUsedAvatar(@RequestParam("id") Long id) {
        UserUsedAvatarDO userUsedAvatar = userUsedAvatarService.getUserUsedAvatar(id);
        return success(BeanUtils.toBean(userUsedAvatar, UserUsedAvatarRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得历史头像分页")
    @PreAuthorize("@ss.hasPermission('im:user-used-avatar:query')")
    public CommonResult<PageResult<UserUsedAvatarRespVO>> getUserUsedAvatarPage(@Valid UserUsedAvatarPageReqVO pageReqVO) {
        PageResult<UserUsedAvatarDO> pageResult = userUsedAvatarService.getUserUsedAvatarPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UserUsedAvatarRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出历史头像 Excel")
    @PreAuthorize("@ss.hasPermission('im:user-used-avatar:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUserUsedAvatarExcel(@Valid UserUsedAvatarPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UserUsedAvatarDO> list = userUsedAvatarService.getUserUsedAvatarPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "历史头像.xls", "数据", UserUsedAvatarRespVO.class,
                        BeanUtils.toBean(list, UserUsedAvatarRespVO.class));
    }

}
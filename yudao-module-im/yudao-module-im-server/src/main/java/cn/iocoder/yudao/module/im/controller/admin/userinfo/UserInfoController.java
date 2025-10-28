package cn.iocoder.yudao.module.im.controller.admin.userinfo;

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

import cn.iocoder.yudao.module.im.controller.admin.userinfo.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.userinfo.UserInfoDO;
import cn.iocoder.yudao.module.im.service.userinfo.UserInfoService;

@Tag(name = "管理后台 - 用户信息")
@RestController
@RequestMapping("/im/user-info")
@Validated
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建用户信息")
    @PreAuthorize("@ss.hasPermission('im:user-info:create')")
    public CommonResult<Long> createUserInfo(@Valid @RequestBody UserInfoSaveReqVO createReqVO) {
        return success(userInfoService.createUserInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户信息")
    @PreAuthorize("@ss.hasPermission('im:user-info:update')")
    public CommonResult<Boolean> updateUserInfo(@Valid @RequestBody UserInfoSaveReqVO updateReqVO) {
        userInfoService.updateUserInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('im:user-info:delete')")
    public CommonResult<Boolean> deleteUserInfo(@RequestParam("id") Long id) {
        userInfoService.deleteUserInfo(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除用户信息")
                @PreAuthorize("@ss.hasPermission('im:user-info:delete')")
    public CommonResult<Boolean> deleteUserInfoList(@RequestParam("ids") List<Long> ids) {
        userInfoService.deleteUserInfoListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('im:user-info:query')")
    public CommonResult<UserInfoRespVO> getUserInfo(@RequestParam("id") Long id) {
        UserInfoDO userInfo = userInfoService.getUserInfo(id);
        return success(BeanUtils.toBean(userInfo, UserInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用户信息分页")
    @PreAuthorize("@ss.hasPermission('im:user-info:query')")
    public CommonResult<PageResult<UserInfoRespVO>> getUserInfoPage(@Valid UserInfoPageReqVO pageReqVO) {
        PageResult<UserInfoDO> pageResult = userInfoService.getUserInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UserInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出用户信息 Excel")
    @PreAuthorize("@ss.hasPermission('im:user-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUserInfoExcel(@Valid UserInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UserInfoDO> list = userInfoService.getUserInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "用户信息.xls", "数据", UserInfoRespVO.class,
                        BeanUtils.toBean(list, UserInfoRespVO.class));
    }

}
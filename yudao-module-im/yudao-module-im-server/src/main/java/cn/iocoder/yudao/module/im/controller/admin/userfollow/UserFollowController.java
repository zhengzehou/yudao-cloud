package cn.iocoder.yudao.module.im.controller.admin.userfollow;

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

import cn.iocoder.yudao.module.im.controller.admin.userfollow.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.userfollow.UserFollowDO;
import cn.iocoder.yudao.module.im.service.userfollow.UserFollowService;

@Tag(name = "管理后台 - 我的关注")
@RestController
@RequestMapping("/im/user-follow")
@Validated
public class UserFollowController {

    @Resource
    private UserFollowService userFollowService;

    @PostMapping("/create")
    @Operation(summary = "创建我的关注")
    @PreAuthorize("@ss.hasPermission('im:user-follow:create')")
    public CommonResult<Long> createUserFollow(@Valid @RequestBody UserFollowSaveReqVO createReqVO) {
        return success(userFollowService.createUserFollow(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新我的关注")
    @PreAuthorize("@ss.hasPermission('im:user-follow:update')")
    public CommonResult<Boolean> updateUserFollow(@Valid @RequestBody UserFollowSaveReqVO updateReqVO) {
        userFollowService.updateUserFollow(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除我的关注")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('im:user-follow:delete')")
    public CommonResult<Boolean> deleteUserFollow(@RequestParam("id") Long id) {
        userFollowService.deleteUserFollow(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除我的关注")
                @PreAuthorize("@ss.hasPermission('im:user-follow:delete')")
    public CommonResult<Boolean> deleteUserFollowList(@RequestParam("ids") List<Long> ids) {
        userFollowService.deleteUserFollowListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得我的关注")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('im:user-follow:query')")
    public CommonResult<UserFollowRespVO> getUserFollow(@RequestParam("id") Long id) {
        UserFollowDO userFollow = userFollowService.getUserFollow(id);
        return success(BeanUtils.toBean(userFollow, UserFollowRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得我的关注分页")
    @PreAuthorize("@ss.hasPermission('im:user-follow:query')")
    public CommonResult<PageResult<UserFollowRespVO>> getUserFollowPage(@Valid UserFollowPageReqVO pageReqVO) {
        PageResult<UserFollowDO> pageResult = userFollowService.getUserFollowPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UserFollowRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出我的关注 Excel")
    @PreAuthorize("@ss.hasPermission('im:user-follow:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUserFollowExcel(@Valid UserFollowPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UserFollowDO> list = userFollowService.getUserFollowPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "我的关注.xls", "数据", UserFollowRespVO.class,
                        BeanUtils.toBean(list, UserFollowRespVO.class));
    }

}
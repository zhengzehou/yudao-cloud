package cn.iocoder.yudao.module.im.controller.admin.friendpostfixuser;

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

import cn.iocoder.yudao.module.im.controller.admin.friendpostfixuser.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.friendpostfixuser.FriendPostFixUserDO;
import cn.iocoder.yudao.module.im.service.friendpostfixuser.FriendPostFixUserService;

@Tag(name = "管理后台 - 朋友圈特定人员")
@RestController
@RequestMapping("/im/friend-post-fix-user")
@Validated
public class FriendPostFixUserController {

    @Resource
    private FriendPostFixUserService friendPostFixUserService;

    @PostMapping("/create")
    @Operation(summary = "创建朋友圈特定人员")
    @PreAuthorize("@ss.hasPermission('im:friend-post-fix-user:create')")
    public CommonResult<Long> createFriendPostFixUser(@Valid @RequestBody FriendPostFixUserSaveReqVO createReqVO) {
        return success(friendPostFixUserService.createFriendPostFixUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新朋友圈特定人员")
    @PreAuthorize("@ss.hasPermission('im:friend-post-fix-user:update')")
    public CommonResult<Boolean> updateFriendPostFixUser(@Valid @RequestBody FriendPostFixUserSaveReqVO updateReqVO) {
        friendPostFixUserService.updateFriendPostFixUser(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除朋友圈特定人员")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('im:friend-post-fix-user:delete')")
    public CommonResult<Boolean> deleteFriendPostFixUser(@RequestParam("id") Long id) {
        friendPostFixUserService.deleteFriendPostFixUser(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除朋友圈特定人员")
                @PreAuthorize("@ss.hasPermission('im:friend-post-fix-user:delete')")
    public CommonResult<Boolean> deleteFriendPostFixUserList(@RequestParam("ids") List<Long> ids) {
        friendPostFixUserService.deleteFriendPostFixUserListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得朋友圈特定人员")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('im:friend-post-fix-user:query')")
    public CommonResult<FriendPostFixUserRespVO> getFriendPostFixUser(@RequestParam("id") Long id) {
        FriendPostFixUserDO friendPostFixUser = friendPostFixUserService.getFriendPostFixUser(id);
        return success(BeanUtils.toBean(friendPostFixUser, FriendPostFixUserRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得朋友圈特定人员分页")
    @PreAuthorize("@ss.hasPermission('im:friend-post-fix-user:query')")
    public CommonResult<PageResult<FriendPostFixUserRespVO>> getFriendPostFixUserPage(@Valid FriendPostFixUserPageReqVO pageReqVO) {
        PageResult<FriendPostFixUserDO> pageResult = friendPostFixUserService.getFriendPostFixUserPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, FriendPostFixUserRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出朋友圈特定人员 Excel")
    @PreAuthorize("@ss.hasPermission('im:friend-post-fix-user:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFriendPostFixUserExcel(@Valid FriendPostFixUserPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<FriendPostFixUserDO> list = friendPostFixUserService.getFriendPostFixUserPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "朋友圈特定人员.xls", "数据", FriendPostFixUserRespVO.class,
                        BeanUtils.toBean(list, FriendPostFixUserRespVO.class));
    }

}
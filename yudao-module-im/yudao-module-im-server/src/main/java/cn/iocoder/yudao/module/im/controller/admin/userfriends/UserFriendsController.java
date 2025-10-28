package cn.iocoder.yudao.module.im.controller.admin.userfriends;

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

import cn.iocoder.yudao.module.im.controller.admin.userfriends.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.userfriends.UserFriendsDO;
import cn.iocoder.yudao.module.im.service.userfriends.UserFriendsService;

@Tag(name = "管理后台 - 我的好友")
@RestController
@RequestMapping("/im/user-friends")
@Validated
public class UserFriendsController {

    @Resource
    private UserFriendsService userFriendsService;

    @PostMapping("/create")
    @Operation(summary = "创建我的好友")
    @PreAuthorize("@ss.hasPermission('im:user-friends:create')")
    public CommonResult<Long> createUserFriends(@Valid @RequestBody UserFriendsSaveReqVO createReqVO) {
        return success(userFriendsService.createUserFriends(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新我的好友")
    @PreAuthorize("@ss.hasPermission('im:user-friends:update')")
    public CommonResult<Boolean> updateUserFriends(@Valid @RequestBody UserFriendsSaveReqVO updateReqVO) {
        userFriendsService.updateUserFriends(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除我的好友")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('im:user-friends:delete')")
    public CommonResult<Boolean> deleteUserFriends(@RequestParam("id") Long id) {
        userFriendsService.deleteUserFriends(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除我的好友")
                @PreAuthorize("@ss.hasPermission('im:user-friends:delete')")
    public CommonResult<Boolean> deleteUserFriendsList(@RequestParam("ids") List<Long> ids) {
        userFriendsService.deleteUserFriendsListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得我的好友")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('im:user-friends:query')")
    public CommonResult<UserFriendsRespVO> getUserFriends(@RequestParam("id") Long id) {
        UserFriendsDO userFriends = userFriendsService.getUserFriends(id);
        return success(BeanUtils.toBean(userFriends, UserFriendsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得我的好友分页")
    @PreAuthorize("@ss.hasPermission('im:user-friends:query')")
    public CommonResult<PageResult<UserFriendsRespVO>> getUserFriendsPage(@Valid UserFriendsPageReqVO pageReqVO) {
        PageResult<UserFriendsDO> pageResult = userFriendsService.getUserFriendsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UserFriendsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出我的好友 Excel")
    @PreAuthorize("@ss.hasPermission('im:user-friends:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUserFriendsExcel(@Valid UserFriendsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UserFriendsDO> list = userFriendsService.getUserFriendsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "我的好友.xls", "数据", UserFriendsRespVO.class,
                        BeanUtils.toBean(list, UserFriendsRespVO.class));
    }

}
package cn.iocoder.yudao.module.im.controller.admin.roomuser;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.im.controller.admin.roomuser.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.roomuser.RoomUserDO;
import cn.iocoder.yudao.module.im.service.roomuser.RoomUserService;

@Tag(name = "管理后台 - 会话人员和会话设置")
@RestController
@RequestMapping("/im/session-user")
@Validated
public class RoomUserController {

    @Resource
    private RoomUserService sessionUserService;

    @PostMapping("/create")
    @Operation(summary = "创建会话人员和会话设置")
    @PreAuthorize("@ss.hasPermission('im:session-user:create')")
    public CommonResult<Long> createSessionUser(@Valid @RequestBody RoomUserSaveReqVO createReqVO) {
        return success(sessionUserService.createRoomUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会话人员和会话设置")
    @PreAuthorize("@ss.hasPermission('im:session-user:update')")
    public CommonResult<Boolean> updateSessionUser(@Valid @RequestBody RoomUserSaveReqVO updateReqVO) {
        sessionUserService.updateRoomUser(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除会话人员和会话设置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('im:session-user:delete')")
    public CommonResult<Boolean> deleteSessionUser(@RequestParam("id") Long id) {
        sessionUserService.deleteRoomUser(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除会话人员和会话设置")
                @PreAuthorize("@ss.hasPermission('im:session-user:delete')")
    public CommonResult<Boolean> deleteSessionUserList(@RequestParam("ids") List<Long> ids) {
        sessionUserService.deleteRoomUserListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得会话人员和会话设置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('im:session-user:query')")
    public CommonResult<RoomUserRespVO> getSessionUser(@RequestParam("id") Long id) {
        RoomUserDO sessionUser = sessionUserService.getRoomUser(id);
        return success(BeanUtils.toBean(sessionUser, RoomUserRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得会话人员和会话设置分页")
    @PreAuthorize("@ss.hasPermission('im:session-user:query')")
    public CommonResult<PageResult<RoomUserRespVO>> getSessionUserPage(@Valid RoomUserPageReqVO pageReqVO) {
        PageResult<RoomUserDO> pageResult = sessionUserService.getRoomUserPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RoomUserRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出会话人员和会话设置 Excel")
    @PreAuthorize("@ss.hasPermission('im:session-user:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSessionUserExcel(@Valid RoomUserPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RoomUserDO> list = sessionUserService.getRoomUserPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "会话人员和会话设置.xls", "数据", RoomUserRespVO.class,
                        BeanUtils.toBean(list, RoomUserRespVO.class));
    }

}
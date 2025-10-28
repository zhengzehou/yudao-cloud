package cn.iocoder.yudao.module.im.controller.admin.roomgroup;

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

import cn.iocoder.yudao.module.im.controller.admin.roomgroup.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.roomgroup.RoomGroupDO;
import cn.iocoder.yudao.module.im.service.roomgroup.RoomGroupService;

@Tag(name = "管理后台 - 会话（群组）")
@RestController
@RequestMapping("/im/session-group")
@Validated
public class RoomGroupController {

    @Resource
    private RoomGroupService sessionGroupService;

    @PostMapping("/create")
    @Operation(summary = "创建会话（群组）")
    @PreAuthorize("@ss.hasPermission('im:session-group:create')")
    public CommonResult<Long> createSessionGroup(@Valid @RequestBody RoomGroupSaveReqVO createReqVO) {
        return success(sessionGroupService.createRoomGroup(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会话（群组）")
    @PreAuthorize("@ss.hasPermission('im:session-group:update')")
    public CommonResult<Boolean> updateSessionGroup(@Valid @RequestBody RoomGroupSaveReqVO updateReqVO) {
        sessionGroupService.updateRoomGroup(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除会话（群组）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('im:session-group:delete')")
    public CommonResult<Boolean> deleteSessionGroup(@RequestParam("id") Long id) {
        sessionGroupService.deleteRoomGroup(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除会话（群组）")
                @PreAuthorize("@ss.hasPermission('im:session-group:delete')")
    public CommonResult<Boolean> deleteSessionGroupList(@RequestParam("ids") List<Long> ids) {
        sessionGroupService.deleteRoomGroupListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得会话（群组）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('im:session-group:query')")
    public CommonResult<RoomGroupRespVO> getSessionGroup(@RequestParam("id") Long id) {
        RoomGroupDO sessionGroup = sessionGroupService.getRoomGroup(id);
        return success(BeanUtils.toBean(sessionGroup, RoomGroupRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得会话（群组）分页")
    @PreAuthorize("@ss.hasPermission('im:session-group:query')")
    public CommonResult<PageResult<RoomGroupRespVO>> getSessionGroupPage(@Valid RoomGroupPageReqVO pageReqVO) {
        PageResult<RoomGroupDO> pageResult = sessionGroupService.getRoomGroupPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RoomGroupRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出会话（群组） Excel")
    @PreAuthorize("@ss.hasPermission('im:session-group:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSessionGroupExcel(@Valid RoomGroupPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RoomGroupDO> list = sessionGroupService.getRoomGroupPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "会话（群组）.xls", "数据", RoomGroupRespVO.class,
                        BeanUtils.toBean(list, RoomGroupRespVO.class));
    }

}
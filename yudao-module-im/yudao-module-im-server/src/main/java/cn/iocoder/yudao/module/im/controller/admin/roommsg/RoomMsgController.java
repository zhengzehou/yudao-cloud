package cn.iocoder.yudao.module.im.controller.admin.roommsg;

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

import cn.iocoder.yudao.module.im.controller.admin.roommsg.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.roommsg.RoomMsgDO;
import cn.iocoder.yudao.module.im.service.roommsg.RoomMsgService;

@Tag(name = "管理后台 - 会话消息")
@RestController
@RequestMapping("/im/session-msg")
@Validated
public class RoomMsgController {

    @Resource
    private RoomMsgService sessionMsgService;

    @PostMapping("/create")
    @Operation(summary = "创建会话消息")
    @PreAuthorize("@ss.hasPermission('im:session-msg:create')")
    public CommonResult<Long> createSessionMsg(@Valid @RequestBody RoomMsgSaveReqVO createReqVO) {
        return success(sessionMsgService.createRoomMsg(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会话消息")
    @PreAuthorize("@ss.hasPermission('im:session-msg:update')")
    public CommonResult<Boolean> updateSessionMsg(@Valid @RequestBody RoomMsgSaveReqVO updateReqVO) {
        sessionMsgService.updateRoomMsg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除会话消息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('im:session-msg:delete')")
    public CommonResult<Boolean> deleteSessionMsg(@RequestParam("id") Long id) {
        sessionMsgService.deleteRoomMsg(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除会话消息")
                @PreAuthorize("@ss.hasPermission('im:session-msg:delete')")
    public CommonResult<Boolean> deleteSessionMsgList(@RequestParam("ids") List<Long> ids) {
        sessionMsgService.deleteRoomMsgListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得会话消息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('im:session-msg:query')")
    public CommonResult<RoomMsgRespVO> getSessionMsg(@RequestParam("id") Long id) {
        RoomMsgDO sessionMsg = sessionMsgService.getRoomMsg(id);
        return success(BeanUtils.toBean(sessionMsg, RoomMsgRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得会话消息分页")
    @PreAuthorize("@ss.hasPermission('im:session-msg:query')")
    public CommonResult<PageResult<RoomMsgRespVO>> getSessionMsgPage(@Valid RoomMsgPageReqVO pageReqVO) {
        PageResult<RoomMsgDO> pageResult = sessionMsgService.getRoomMsgPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RoomMsgRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出会话消息 Excel")
    @PreAuthorize("@ss.hasPermission('im:session-msg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSessionMsgExcel(@Valid RoomMsgPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RoomMsgDO> list = sessionMsgService.getRoomMsgPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "会话消息.xls", "数据", RoomMsgRespVO.class,
                        BeanUtils.toBean(list, RoomMsgRespVO.class));
    }

}
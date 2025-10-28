package cn.iocoder.yudao.module.im.controller.app.chat;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.im.controller.admin.roomgroup.vo.RoomGroupPageReqVO;
import cn.iocoder.yudao.module.im.controller.admin.roomgroup.vo.RoomGroupRespVO;
import cn.iocoder.yudao.module.im.controller.admin.roomgroup.vo.RoomGroupSaveReqVO;
import cn.iocoder.yudao.module.im.controller.admin.userinfo.vo.UserInfoPageReqVO;
import cn.iocoder.yudao.module.im.controller.app.chat.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.roomgroup.RoomGroupDO;
import cn.iocoder.yudao.module.im.dal.dataobject.userinfo.UserInfoDO;
import cn.iocoder.yudao.module.im.service.roomgroup.RoomGroupService;
import cn.iocoder.yudao.module.im.service.userinfo.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 会话（群组）")
@RestController
@RequestMapping("/im/chat")
@Validated
public class ChatController {

    @Resource
    private RoomGroupService sessionGroupService;

    @Resource
    private UserInfoService userInfoService;

    /**
     * 最近联系人，需要根据最新活跃时间进行倒序排序
     * @param reqVO
     * @return
     */
    @RequestMapping("/contact/page")
    @Operation(summary = "获得会话最近联系人")
    public CommonResult<ChatRespVO> getContactPage(@Valid ChatPageReqVO reqVO) {
        RoomGroupPageReqVO pageReqVO = new RoomGroupPageReqVO();
        pageReqVO.setPageSize(reqVO.getPageSize());
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        pageReqVO.setCreateUserId(userId);
        PageResult<ContactVO> pageResult = sessionGroupService.getContactPage(pageReqVO);
        ChatRespVO chatRespVO = new ChatRespVO();
        if(pageResult.getList().size() == reqVO.getPageSize()){
            chatRespVO.setCursor(reqVO.getCursor()+1);
            chatRespVO.setIsLast(false);
        }else{
            chatRespVO.setCursor(reqVO.getCursor());
            chatRespVO.setIsLast(true);
        }
        chatRespVO.setList(pageResult.getList());
        return success(chatRespVO);
    }
    @RequestMapping("/user/friend/page")
    @Operation(summary = "获取好友列表")
    public CommonResult<ChatRespVO> getUserFriendsPage(@Valid ChatPageReqVO reqVO) {

        UserInfoPageReqVO pageReqVO = new UserInfoPageReqVO();
        pageReqVO.setPageSize(reqVO.getPageSize());
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        pageReqVO.setId(userId);
        PageResult<UserVO> pageResult = userInfoService.selectFriendsPage(pageReqVO);
        ChatRespVO chatRespVO = new ChatRespVO();
        if(pageResult.getList().size() == reqVO.getPageSize()){
            chatRespVO.setCursor(reqVO.getCursor()+1);
            chatRespVO.setIsLast(false);
        }else{
            chatRespVO.setCursor(reqVO.getCursor());
            chatRespVO.setIsLast(true);
        }
        chatRespVO.setList(pageResult.getList());
        return success(chatRespVO);
    }
    @RequestMapping("/room/group/page")
    @Operation(summary = "获取群组列表")
    public CommonResult<ChatRespVO> getRoomGroupPage(@Valid ChatPageReqVO reqVO) {
        RoomGroupPageReqVO pageReqVO = new RoomGroupPageReqVO();
        pageReqVO.setPageSize(reqVO.getPageSize());
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        pageReqVO.setCreateUserId(userId);
        PageResult<RoomGroupVO> pageResult = sessionGroupService.getContactGroupPage(pageReqVO);
        ChatRespVO chatRespVO = new ChatRespVO();
        if(pageResult.getList().size() == reqVO.getPageSize()){
            chatRespVO.setCursor(reqVO.getCursor()+1);
            chatRespVO.setIsLast(false);
        }else{
            chatRespVO.setCursor(reqVO.getCursor());
            chatRespVO.setIsLast(true);
        }
        chatRespVO.setList(pageResult.getList());
        return success(chatRespVO);
    }

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
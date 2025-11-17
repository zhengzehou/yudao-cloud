package cn.iocoder.yudao.module.im.controller.app.user;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.common.util.servlet.ServletUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.im.controller.admin.userinfo.vo.UserInfoPageReqVO;
import cn.iocoder.yudao.module.im.controller.admin.userinfo.vo.UserInfoRespVO;
import cn.iocoder.yudao.module.im.controller.admin.userinfo.vo.UserInfoSaveReqVO;
import cn.iocoder.yudao.module.im.dal.dataobject.userinfo.UserInfoDO;
import cn.iocoder.yudao.module.im.service.userinfo.UserInfoService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.error;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 用户信息")
@RestController
@RequestMapping("/im/user")
@Validated
public class UserController {

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

//    @DeleteMapping("/delete")
//    @Operation(summary = "删除用户信息")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('im:user-info:delete')")
//    public CommonResult<Boolean> deleteUserInfo(@RequestParam("id") Long id) {
//        userInfoService.deleteUserInfo(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    public CommonResult<Boolean> deleteUserInfoList(@RequestParam("ids") List<Long> ids) {
//        userInfoService.deleteUserInfoListByIds(ids);
//        return success(true);
//    }

    @GetMapping("/get")
    @Operation(summary = "获得用户信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<UserInfoRespVO> getUserInfo(@RequestParam("id") Long id) {
        UserInfoDO userInfo = userInfoService.getUserInfo(id);
        return success(BeanUtils.toBean(userInfo, UserInfoRespVO.class));
    }


    @GetMapping("/info")
    @Operation(summary = "获得当前登录用户信息")
    @PermitAll
    public CommonResult<UserInfoRespVO> userInfo() {
        Long id = SecurityFrameworkUtils.getLoginUserId();
        if (id == null) {
            return error(1,"请先登录");
        }
        UserInfoDO userInfo = userInfoService.getUserInfo(id);
        return success(BeanUtils.toBean(userInfo, UserInfoRespVO.class));
    }
    @GetMapping("/exist")
    @Operation(summary = "判断用户是否存在")
    @PermitAll
    public CommonResult<String> userExist(String username) {
        UserInfoDO userInfo = userInfoService.getUserByUsername(username);
        return userInfo == null ? success("1") : error(1,"账号已使用");
    }
    @GetMapping("/page")
    @Operation(summary = "获得用户信息分页")
    @PreAuthorize("@ss.hasPermission('im:user-info:query')")
    public CommonResult<PageResult<UserInfoRespVO>> getUserInfoPage(@Valid UserInfoPageReqVO pageReqVO) {
        PageResult<UserInfoDO> pageResult = userInfoService.getUserInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UserInfoRespVO.class));
    }

    @GetMapping("/new-friend/page")
    @Operation(summary = "申请加好友的列表-新朋友")
    @PreAuthorize("@ss.hasPermission('im:user-info:query')")
    public CommonResult<PageResult<UserInfoRespVO>> getUserNewFriendsPage(@Valid UserInfoPageReqVO pageReqVO) {
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
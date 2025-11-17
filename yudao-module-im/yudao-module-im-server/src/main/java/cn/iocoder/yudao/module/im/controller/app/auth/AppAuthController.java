package cn.iocoder.yudao.module.im.controller.app.auth;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.servlet.ServletUtils;
import cn.iocoder.yudao.framework.security.config.SecurityProperties;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.im.controller.admin.userinfo.vo.UserInfoSaveReqVO;
import cn.iocoder.yudao.module.im.controller.app.auth.vo.*;
import cn.iocoder.yudao.module.im.convert.auth.AuthConvert;
import cn.iocoder.yudao.module.im.service.auth.UserAuthService;
import cn.iocoder.yudao.module.im.service.userinfo.UserInfoService;
import cn.iocoder.yudao.module.system.api.social.SocialClientApi;
import cn.iocoder.yudao.module.system.api.social.dto.SocialWxJsapiSignatureRespDTO;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.time.ZoneId;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.error;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "用户 APP - 认证")
@RestController
@RequestMapping("/im/user")
@Validated
@Slf4j
public class AppAuthController {

    @Resource
    private UserAuthService authService;

    @Resource
    private UserInfoService userInfoService;

//    @Resource
//    private SocialClientApi socialClientApi;

    @Resource
    private SecurityProperties securityProperties;

    @PostMapping("/login")
    @Operation(summary = "使用手机 + 密码登录")
    @PermitAll
    public CommonResult<AppAuthLoginRespVO> login(@RequestBody @Valid AppAuthLoginReqVO reqVO) {
        return success(authService.login(reqVO));
    }
    @PostMapping("/login/pwd/all")
    @Operation(summary = "密码登录")
    @PermitAll
    public CommonResult<AppAuthLoginRespVO> pwdLogin(@RequestBody @Valid AppAuthLoginReqVO reqVO) {
        return success(authService.login(reqVO));
    }

    @PostMapping("/logout")
    @Operation(summary = "登出系统")
    @PermitAll
    public CommonResult<Boolean> logout(HttpServletRequest request) {
        String token = SecurityFrameworkUtils.obtainAuthorization(request,
                securityProperties.getTokenHeader(), securityProperties.getTokenParameter());
        if (StrUtil.isNotBlank(token)) {
            authService.logout(token);
        }
        return success(true);
    }
    @PostMapping("/logout/all")
    @Operation(summary = "登出所有设备的系统")
    @PermitAll
    public CommonResult<Boolean> logoutAll(HttpServletRequest request) {
        String token = SecurityFrameworkUtils.obtainAuthorization(request,
                securityProperties.getTokenHeader(), securityProperties.getTokenParameter());
        if (StrUtil.isNotBlank(token)) {
            authService.logoutAll(token);
        }
        return success(true);
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "刷新令牌")
    @Parameter(name = "refreshToken", description = "刷新令牌", required = true)
    @PermitAll
    public CommonResult<AppAuthLoginRespVO> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return success(authService.refreshToken(refreshToken));
    }

    // ========== 短信登录相关 ==========

    @PostMapping("/sms-login")
    @Operation(summary = "使用手机 + 验证码登录")
    @PermitAll
    public CommonResult<AppAuthLoginRespVO> smsLogin(@RequestBody @Valid AppAuthSmsLoginReqVO reqVO) {
        return success(authService.smsLogin(reqVO));
    }

    @PostMapping("/send-sms-code")
    @Operation(summary = "发送手机验证码")
    @PermitAll
    public CommonResult<Boolean> sendSmsCode(@RequestBody @Valid AppAuthSmsSendReqVO reqVO) {
        authService.sendSmsCode(getLoginUserId(), reqVO);
        return success(true);
    }

    @PostMapping("/validate-sms-code")
    @Operation(summary = "校验手机验证码")
    @PermitAll
    public CommonResult<Boolean> validateSmsCode(@RequestBody @Valid AppAuthSmsValidateReqVO reqVO) {
        authService.validateSmsCode(getLoginUserId(), reqVO);
        return success(true);
    }

    @PostMapping("/register/v2")
    @Operation(summary = "注册账号")
    @PermitAll
    public CommonResult<Long> userRegister(@RequestBody JSONObject json) {
        String username = json.getString("username");
        String password = json.getString("password");
        String secondPassword = json.getString("secondPassword");
        if(password != null && !password.equals(secondPassword)){
            return error(1,"密码不一致");
        }
        String phone = json.getString("phone");
        String email = json.getString("email");
        String code = json.getString("code");
        Integer type = json.getInteger("type");
        UserInfoSaveReqVO reqVO = new UserInfoSaveReqVO();
        reqVO.setName(username);
        reqVO.setRegisterIp(ServletUtils.getClientIP());
        reqVO.setUsername(username);
        reqVO.setPhone(phone == null ? "" : phone.trim());
        reqVO.setEmail(email == null ? "" : email.trim());
        reqVO.setAccSource(0);
        reqVO.setPwd(password);
        reqVO.setRegisterType(type);
        Long userId = userInfoService.createUserInfo(reqVO);
        return success(userId);
    }

    // ========== 社交登录相关 ==========

//    @GetMapping("/social-auth-redirect")
//    @Operation(summary = "社交授权的跳转")
//    @Parameters({
//            @Parameter(name = "type", description = "社交类型", required = true),
//            @Parameter(name = "redirectUri", description = "回调路径")
//    })
//    @PermitAll
//    public CommonResult<String> socialAuthRedirect(@RequestParam("type") Integer type,
//                                                   @RequestParam("redirectUri") String redirectUri) {
//        return CommonResult.success(authService.getSocialAuthorizeUrl(type, redirectUri));
//    }

//    @PostMapping("/social-login")
//    @Operation(summary = "社交快捷登录，使用 code 授权码", description = "适合未登录的用户，但是社交账号已绑定用户")
//    @PermitAll
//    public CommonResult<AppAuthLoginRespVO> socialLogin(@RequestBody @Valid AppAuthSocialLoginReqVO reqVO) {
//        return success(authService.socialLogin(reqVO));
//    }

//    @PostMapping("/weixin-mini-app-login")
//    @Operation(summary = "微信小程序的一键登录")
//    @PermitAll
//    public CommonResult<AppAuthLoginRespVO> weixinMiniAppLogin(@RequestBody @Valid AppAuthWeixinMiniAppLoginReqVO reqVO) {
//        return success(authService.weixinMiniAppLogin(reqVO));
//    }

//    @PostMapping("/create-weixin-jsapi-signature")
//    @Operation(summary = "创建微信 JS SDK 初始化所需的签名",
//            description = "参考 https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/JS-SDK.html 文档")
//    @PermitAll
//    public CommonResult<SocialWxJsapiSignatureRespDTO> createWeixinMpJsapiSignature(@RequestParam("url") String url) {
//        SocialWxJsapiSignatureRespDTO signature = socialClientApi.createWxMpJsapiSignature(
//                UserTypeEnum.MEMBER.getValue(), url).getCheckedData();
//        return success(AuthConvert.INSTANCE.convert(signature));
//    }

}

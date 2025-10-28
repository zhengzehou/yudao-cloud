package cn.iocoder.yudao.module.im.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 APP - 认证")
@RestController
@RequestMapping("/im/res")
@Validated
@Slf4j
public class AppController {
    @GetMapping("/app/latest")
    @PermitAll
    public CommonResult<AppVersionRespVO> appVersion() {
        AppVersionRespVO appVersionRespVO = new AppVersionRespVO();
        appVersionRespVO.setId(1L);
        appVersionRespVO.setVersion("1.0.0");
        appVersionRespVO.setVersionLabel("1.0.0");
        appVersionRespVO.setIsLatest(true);
        appVersionRespVO.setFetchVersionStatus(true);
        appVersionRespVO.setFetchVersionStatusLabel("1.0.0");
        appVersionRespVO.setNotice("1.0.0");
        appVersionRespVO.setNoticeSummary("1.0.0");
        AppVersionRespVO.Updater updater = new AppVersionRespVO.Updater();
        updater.setVersion("1.0.0");
        updater.setNotes("1.0.0");
        updater.setPlatforms("{}");
        updater.setPubDate("1.0.0");
        appVersionRespVO.setUpdaterJson(updater);
        appVersionRespVO.setCreateTime("1.0.0");
        return success(appVersionRespVO);
    }
    @GetMapping("/system/constant")
    @PermitAll
    public CommonResult<JSONObject> sysConstant() {
        String sysConstant = """
                {
                        "ossInfo": {
                            "image": {
                                "type": "image",
                                "path": "image/",
                                "code": 0,
                                "timeOut": 12000,
                                "fileSize": 3145728,
                                "fileType": "image/*"
                            },
                            "file": {
                                "type": "file",
                                "path": "file/",
                                "code": 2,
                                "timeOut": 12000,
                                "fileSize": 52428800,
                                "fileType": "!image/*;!video/*"
                            },
                            "video": {
                                "type": "video",
                                "path": "video/",
                                "code": 1,
                                "timeOut": 12000,
                                "fileSize": 20971520,
                                "fileType": "video/*;"
                            },
                            "audio": {
                                "type": "audio",
                                "path": "audio/",
                                "code": 4,
                                "timeOut": 12000,
                                "fileSize": 10485760,
                                "fileType": "audio/mp3;audio/x-mpeg;audio/mpeg;audio/webm;audio/wav;video/webm;"
                            },
                            "font": {
                                "type": "font",
                                "path": "font/",
                                "code": 3,
                                "timeOut": 12000,
                                "fileSize": 12582912,
                                "fileType": "font/*"
                            }
                        },
                        "msgInfo": {
                            "1": {
                                "maxLength": 2048
                            },
                            "3": {
                                "maxLength": 2048
                            },
                            "4": {
                                "maxLength": 2048
                            },
                            "6": {
                                "maxLength": 2048
                            },
                            "8": {
                                "maxLength": 10240
                            },
                            "9": {
                                "maxLength": 8192
                            },
                            "13": {
                                "maxLength": 4096
                            }
                        }
                    }
                """;
        return success(JSON.parseObject(sysConstant));
    }

}

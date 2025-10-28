package cn.iocoder.yudao.module.im.controller.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "用户 APP - 登录 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppVersionRespVO {

    private Long id;
    private String version;
    private String versionLabel;
    private Boolean isLatest;
    private Boolean fetchVersionStatus;
    private String fetchVersionStatusLabel;
    private String notice;
    private String noticeSummary;
    private Updater updaterJson;
    private String createTime;
    private String updateTime;

    @Data
    public static class Updater {
        private String version;
        private String notes;
        private String pubDate;
        private String platforms;
    }


}

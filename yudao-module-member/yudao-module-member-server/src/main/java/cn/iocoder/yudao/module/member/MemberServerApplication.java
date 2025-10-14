package cn.iocoder.yudao.module.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 项目的启动类
 *
 * @author 芋道源码
 */
@SpringBootApplication
public class MemberServerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        cn.iocoder.yudao.framework.common.util.spring.SetProfileByEnv.setProfile();
        return builder.sources(MemberServerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MemberServerApplication.class, args);
    }

}

package cn.iocoder.yudao.module.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 项目的启动类
 * @author 芋道源码
 */
@SpringBootApplication
public class SystemServerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
cn.iocoder.yudao.framework.common.util.spring.SetProfileByEnv.setProfile();
        return builder.sources(SystemServerApplication.class);
    }

    public static void main(String[] args) {

        SpringApplication.run(SystemServerApplication.class, args);
    }

}

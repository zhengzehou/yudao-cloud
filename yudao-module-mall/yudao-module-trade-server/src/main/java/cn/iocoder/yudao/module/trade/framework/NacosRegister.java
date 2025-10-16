package cn.iocoder.yudao.module.trade.framework;

import com.alibaba.cloud.nacos.registry.NacosAutoServiceRegistration;
import com.alibaba.cloud.nacos.registry.NacosRegistration;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import java.lang.management.ManagementFactory;
import java.util.Set;

@Component
@Slf4j
public class NacosRegister {

    @Autowired
    private NacosRegistration registration;

    @Autowired
    private NacosAutoServiceRegistration nacosAutoServiceRegistration;

    @Value("${server.port}")
    String serverPort;

    @PostConstruct
    public void registerInstance() throws Exception {
        log.info("start register server to nacos");
        if (registration != null ) {
            String tomcatPort = serverPort;
            try {
                tomcatPort = getPort();
            } catch (Exception e) {
                log.warn("获取外部Tomcat端口异常：", e);
            }
            registration.setPort(Integer.parseInt(tomcatPort));
            nacosAutoServiceRegistration.start();
        }
    }

    /**
     * 获取外部tomcat端口
     */
    public String getPort() {
        try {
            MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
            Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"), Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
            String port = objectNames.iterator().next().getKeyProperty("port");
            return port;
        } catch (Exception ex) {
            log.error("NacosRegister.getPort()动态获取端口异常:", ex.toString());
            return serverPort;
        }
    }
}
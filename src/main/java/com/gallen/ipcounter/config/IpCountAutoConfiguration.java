package com.gallen.ipcounter.config;

import com.gallen.ipcounter.service.IpCountService;
import com.gallen.ipcounter.service.impl.IpCountServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling//开启支持定时任务
//@EnableConfigurationProperties(IpProperties.class)
@Import(IpProperties.class)
public class IpCountAutoConfiguration {
    @Bean
    public IpCountService ipCountService(){
        return new IpCountServiceImpl();
    }
}

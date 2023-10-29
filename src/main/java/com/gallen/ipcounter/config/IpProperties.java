package com.gallen.ipcounter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "gallen.tools.ip")
@Component("ipProperties")
@Data
public class IpProperties {
    /**
     * 循环间隔(秒)
     */
    private Long cycle = 5L;

    /**
     * 是否周期内重置
     */
    private Boolean cycleReset = false;

    /**
     * 是否直接输出
     */
    private Boolean print = true;

    /**
     * 是否将数据存在Redis
     */
    private Boolean storeInRedis = false;
}

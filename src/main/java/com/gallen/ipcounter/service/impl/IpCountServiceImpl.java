package com.gallen.ipcounter.service.impl;

import com.gallen.ipcounter.config.IpProperties;
import com.gallen.ipcounter.service.IpCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.gallen.ipcounter.constants.RedisConstants.IP_COUNTER_ZSET_KEY;


public class IpCountServiceImpl implements IpCountService{
    private Map<String, Integer> ipCountMap = new HashMap<>();

    /**
     * 从倒入starter的web容器中获取httpServletRequest
     */
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Override
    public void count(){
        // 获取IP
        String ip = httpServletRequest.getRemoteAddr();

        // 用Map统计IP
        Integer count = ipCountMap.getOrDefault(ip, 0);
        ipCountMap.put(ip, count + 1);

    }

    @Autowired
    private IpProperties ipProperties;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    @Scheduled(cron = "0/#{ipProperties.cycle} * * * * ?")
    public void print() {
        Set<Map.Entry<String, Integer>> entries = ipCountMap.entrySet();
        if(ipProperties.getPrint()){
            System.out.println("         IP访问统计");
            System.out.println("+-----ip-address-----+--num--+");
            for (Map.Entry<String, Integer> entry : ipCountMap.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println(String.format("|%18s  |%5d  |",key,value));
            }
            System.out.println("+--------------------+-------+");
        }


        // 如果开启使用Redis存储，则将数据保存到Redis中
        if(ipProperties.getStoreInRedis()){
            for (Map.Entry<String, Integer> entry : entries) {
                redisTemplate.opsForZSet().add(IP_COUNTER_ZSET_KEY, entry.getKey(), entry.getValue());
            }
        }


        // 如果开启了周期重置，清空Map
        if(ipProperties.getCycleReset()){
            ipCountMap.clear();
        }
    }
}

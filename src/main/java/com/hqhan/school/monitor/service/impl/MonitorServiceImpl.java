package com.hqhan.school.monitor.service.impl;

import com.alibaba.fastjson.JSON;
import com.hqhan.school.monitor.service.MonitorService;
import com.hqhan.school.monitor.vo.ServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 〈description〉<br>
 * 〈〉
 *
 * @author hqhan8080@Gmail.com
 * @version V1.0
 * @date 2019-11-01 23:52
 */
@Service
public class MonitorServiceImpl implements MonitorService {

    private Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);

    @Autowired
    private Environment env;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public ServerInfo iamAlive(ServerInfo serverInfo) {
        String nodeKeyName = buildMonitorKey(serverInfo);
        if (StringUtils.isEmpty(nodeKeyName)) {
            return null;
        }
        redisTemplate.opsForValue().set(nodeKeyName, JSON.toJSONString(serverInfo), Long.valueOf(env.getProperty("redis.key.heart.beat.timeout", "60")), TimeUnit.SECONDS);
        logger.info("Server [{}] is alive, ip {}, port {} ", serverInfo.getServerName(), serverInfo.getIp(), serverInfo.getPort());
        serverInfo.setTimeout((Long.valueOf(env.getProperty("redis.key.heart.beat.timeout", "60")) - 5) * 1000);
        return serverInfo;
    }


    @Override
    public Map<String, List<ServerInfo>> getAliveNodeStatistics(String serverName, String status) {
        Set<String> keys = redisTemplate.keys(MONITOR_HEART_BEAT_PREFIX + "*");
        List<String> serverInfoStrList = redisTemplate.opsForValue().multiGet(keys);
        Map<String, List<ServerInfo>> result = new HashMap<>();
        for (String str : serverInfoStrList) {
            ServerInfo serverInfo = JSON.parseObject(str, ServerInfo.class);
            String server = serverInfo.getServerName();
            if (!result.containsKey(server)) {
                List<ServerInfo> serverInfoList = new ArrayList();
                serverInfoList.add(serverInfo);
                result.put(server, serverInfoList);
            } else {
                result.get(server).add(serverInfo);
            }
        }
        return result;
    }

    @Override
    public Set<String> getAliveServerNameList() {
        return redisTemplate.keys(MONITOR_HEART_BEAT_PREFIX);
    }

    private String buildMonitorKey(ServerInfo serverInfo) {
        if (serverInfo == null || StringUtils.isEmpty(serverInfo.getIp()) || StringUtils.isEmpty(serverInfo.getPort()) || StringUtils.isEmpty(serverInfo.getServerName())) {
            return "";
        }
        return new StringBuffer(MONITOR_HEART_BEAT_PREFIX).append(":").append(serverInfo.getServerName()).append(":").append(serverInfo.getIp()).append(":").append(serverInfo.getPort()).toString();
    }


    /**
     * scan 实现
     *
     * @param pattern  表达式
     * @param consumer 对迭代到的key进行操作
     */
    public void scan(String pattern, Consumer<byte[]> consumer) {
        redisTemplate.execute((RedisConnection connection) -> {
            try (Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().count(Long.MAX_VALUE).match(pattern).build())) {
                cursor.forEachRemaining(consumer);
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 获取符合条件的key
     *
     * @param pattern 表达式
     * @return
     */
    public List<String> keys(String pattern) {
        List<String> keys = new ArrayList<>();
        this.scan(pattern, item -> {
            //符合条件的key
            String key = new String(item, StandardCharsets.UTF_8);
            keys.add(key);
        });
        return keys;
    }
}

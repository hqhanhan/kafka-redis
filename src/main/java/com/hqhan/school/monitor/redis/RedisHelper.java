package com.hqhan.school.monitor.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 〈description〉<br>
 * 〈〉
 *
 * @author hqhan8080@Gmail.com
 * @version V1.0
 * @date 2019-11-02 13:17
 */
@Component
public class RedisHelper {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void getConnectionFactory() {
    }


}

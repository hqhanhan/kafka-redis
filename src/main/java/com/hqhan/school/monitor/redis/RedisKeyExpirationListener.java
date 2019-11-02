package com.hqhan.school.monitor.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * 〈description〉<br>
 * 〈〉
 *
 * @author hqhan8080@Gmail.com
 * @version V1.0
 * @date 2019-11-01 23:14
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    private Logger logger = LoggerFactory.getLogger(RedisKeyExpirationListener.class);

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        super.onMessage(message, pattern);
        try {

            String ok = new String(pattern, "UTF-8");
            String key = new String(message.getBody(), "UTF-8");
            String channel = new String(message.getChannel(), "UTF-8");
            logger.info(key + " ------------------> " + channel+"============="+ok);
        } catch (UnsupportedEncodingException e) {
            logger.error("Redis key is expired error", e);
        }
    }
}





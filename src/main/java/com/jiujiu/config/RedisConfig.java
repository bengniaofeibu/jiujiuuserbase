package com.jiujiu.config;

import com.jiujiuwisdom.utils.RedisClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Bean
    public RedisClient redisClient(){
        return new RedisClient("redis.host","redis.port","redis.pwd","redis.db");
    }

}

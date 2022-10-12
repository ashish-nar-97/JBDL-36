package com.example.minor1.repository;

import com.example.minor1.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class CacheRepository {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private final String USER_KEY_PREFIX = "usr::";

    /**
     * username : ashish
     * key : usr::ashish
     *
     * username : akshay
     * key : usr::akshay
     * */

    public void set(MyUser myUser){
        String key = getKey(myUser.getUsername());
        redisTemplate.opsForValue().set(key, myUser, 24, TimeUnit.HOURS);
    }

    public MyUser get(String username){
        String key = getKey(username);
        return (MyUser) redisTemplate.opsForValue().get(key);
    }

    private String getKey(String username){
        return USER_KEY_PREFIX + username;
    }
}

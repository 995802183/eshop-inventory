package com.wyw.eshop.inventory.dao.impl;

import com.wyw.eshop.inventory.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDaoImpl implements RedisDao {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public void del(String key){
        redisTemplate.opsForValue().getOperations().delete(key);
    }
}

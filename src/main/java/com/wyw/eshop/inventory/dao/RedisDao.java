package com.wyw.eshop.inventory.dao;

public interface RedisDao {
    String get(String key);

    void set(String key, String value);

    void del(String key);
}

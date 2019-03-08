package com.wyw.eshop.inventory.service.impl;

import com.wyw.eshop.inventory.dao.RedisDao;
import com.wyw.eshop.inventory.mapper.ProductInventoryMapper;
import com.wyw.eshop.inventory.model.ProductInventory;
import com.wyw.eshop.inventory.service.ProductInventoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Resource
    private ProductInventoryMapper inventoryMapper;
    @Resource
    private RedisDao redisDao;


    @Override
    public void insertOne(ProductInventory productInventory){
        inventoryMapper.insert(productInventory);
    }

    @Override
    public ProductInventory getById(Integer productId){
        return inventoryMapper.findById(productId);
    }

    @Override
    public void updateProductInventory(ProductInventory productInventory){
        inventoryMapper.updateByPrimaryKey(productInventory);
    }

    @Override
    public void setProductInventoryCache(ProductInventory productInventory){
        String key = "product:inventory:" + productInventory.getProductId();
        redisDao.set(key,String.valueOf(productInventory.getInventoryCnt()));
    }

    @Override
    public ProductInventory getProductInventoryCache(Integer productId ){
        String key = "product:inventory:" + productId;
        String value = redisDao.get(key);
        if(!StringUtils.isEmpty(value)){
            return new ProductInventory(productId,Long.parseLong(value));
        }
        return null;
    }

    @Override
    public void removeProductInventoryCache(ProductInventory productInventory){
        String key = "product:inventory:" + productInventory.getProductId();
        redisDao.del(key);
    }
}

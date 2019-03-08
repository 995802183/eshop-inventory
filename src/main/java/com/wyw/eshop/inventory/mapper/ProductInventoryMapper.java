package com.wyw.eshop.inventory.mapper;

import com.wyw.eshop.inventory.model.ProductInventory;
import com.wyw.eshop.inventory.util.MyMapper;

public interface ProductInventoryMapper extends MyMapper<ProductInventory> {

    public ProductInventory findById(Integer productId);
}
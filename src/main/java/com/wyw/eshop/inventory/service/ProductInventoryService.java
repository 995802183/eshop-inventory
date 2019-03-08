package com.wyw.eshop.inventory.service;

import com.wyw.eshop.inventory.model.ProductInventory;

public interface ProductInventoryService {
    void insertOne(ProductInventory productInventory);

    ProductInventory getById(Integer productId);

    void updateProductInventory(ProductInventory productInventory);

    void setProductInventoryCache(ProductInventory productInventory);

    ProductInventory getProductInventoryCache(Integer productId);

    void removeProductInventoryCache(ProductInventory productInventory);
}

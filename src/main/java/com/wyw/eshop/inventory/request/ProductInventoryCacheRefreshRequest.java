package com.wyw.eshop.inventory.request;

import com.wyw.eshop.inventory.model.ProductInventory;
import com.wyw.eshop.inventory.service.ProductInventoryService;

public class ProductInventoryCacheRefreshRequest implements Request{

    private Integer productId;
    private ProductInventoryService productInventoryService;

    public ProductInventoryCacheRefreshRequest(Integer productId, ProductInventoryService productInventoryService) {
        this.productId = productId;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        ProductInventory productInventory = productInventoryService.getById(productId);
        productInventoryService.setProductInventoryCache(productInventory);
    }

    @Override
    public Integer getProductId() {
        return productId;
    }
}

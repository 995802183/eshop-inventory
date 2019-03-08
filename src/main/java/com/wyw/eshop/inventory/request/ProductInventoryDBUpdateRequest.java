package com.wyw.eshop.inventory.request;

import com.wyw.eshop.inventory.model.ProductInventory;
import com.wyw.eshop.inventory.service.ProductInventoryService;

public class ProductInventoryDBUpdateRequest implements Request {

    private ProductInventory productInventory;
    private ProductInventoryService productInventoryService;

    public ProductInventoryDBUpdateRequest(ProductInventory productInventory, ProductInventoryService productInventoryService) {
        this.productInventory = productInventory;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        productInventoryService.removeProductInventoryCache(productInventory);
        productInventoryService.updateProductInventory(productInventory);
    }

    @Override
    public Integer getProductId() {
        return productInventory.getProductId();
    }
}

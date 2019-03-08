package com.wyw.eshop.inventory.controller;

import com.wyw.eshop.inventory.model.ProductInventory;
import com.wyw.eshop.inventory.request.ProductInventoryCacheRefreshRequest;
import com.wyw.eshop.inventory.request.ProductInventoryDBUpdateRequest;
import com.wyw.eshop.inventory.service.ProductInventoryService;
import com.wyw.eshop.inventory.service.RequestAsyncProcessService;
import com.wyw.eshop.inventory.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductInventoryController {

    @Autowired
    private ProductInventoryService productInventoryService;
    @Autowired
    private RequestAsyncProcessService requestAsyncProcessService;

    @RequestMapping("/addOne")
    public void addOne(ProductInventory productInventory){
        productInventoryService.insertOne(productInventory);
    }

    @RequestMapping("/getOne")
    public ProductInventory getOne(Integer productId){
        ProductInventory productInventory = null;
        ProductInventoryCacheRefreshRequest request = new ProductInventoryCacheRefreshRequest(productId, productInventoryService);
        requestAsyncProcessService.process(request);
        long startTime = System.currentTimeMillis();
        long endTime = 0L;
        long waitTime = 0L;
        while (true){
            if(waitTime > 200){
                break;
            }
            productInventory = productInventoryService.getProductInventoryCache(productId);
            if(!ObjectUtils.isEmpty(productInventory)){
                return productInventory;
            }else{
                try {
                    Thread.sleep(20);
                    endTime = System.currentTimeMillis();
                    waitTime = endTime - startTime;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        productInventory = productInventoryService.getById(productId);
        if(!ObjectUtils.isEmpty(productInventory)){
            productInventoryService.setProductInventoryCache(productInventory);
            return productInventory;
        }
        return new ProductInventory(productId,-1L);
    }

    @RequestMapping("/updateOne")
    public Response updateOne(ProductInventory productInventory){
        try {
            ProductInventoryDBUpdateRequest request = new ProductInventoryDBUpdateRequest(productInventory, productInventoryService);
            requestAsyncProcessService.process(request);
            return new Response(Response.SUCCESS);
        } catch (Exception e) {
            return new Response(Response.FAILURE);
        }
    }

    @RequestMapping("/setCache")
    public void setCache(ProductInventory productInventory){
        productInventoryService.setProductInventoryCache(productInventory);
    }

    @RequestMapping("/getCache")
    public ProductInventory getCache(Integer productId){
        ProductInventory productInventory = productInventoryService.getProductInventoryCache(productId);
        return productInventory;
    }
}

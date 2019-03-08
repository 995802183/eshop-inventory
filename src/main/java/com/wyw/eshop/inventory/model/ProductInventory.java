package com.wyw.eshop.inventory.model;

import javax.persistence.*;

@Table(name = "product_inventory")
public class ProductInventory {
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "inventory_cnt")
    private Long inventoryCnt;

    /**
     * @return product_id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return inventory_cnt
     */
    public Long getInventoryCnt() {
        return inventoryCnt;
    }

    /**
     * @param inventoryCnt
     */
    public void setInventoryCnt(Long inventoryCnt) {
        this.inventoryCnt = inventoryCnt;
    }

    public ProductInventory(Integer productId, Long inventoryCnt) {
        this.productId = productId;
        this.inventoryCnt = inventoryCnt;
    }
}
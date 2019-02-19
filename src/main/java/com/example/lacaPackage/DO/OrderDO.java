package com.example.lacaPackage.DO;

import com.example.lacaPackage.entity.LacaProductOrder;

public class OrderDO extends LacaProductOrder {

    private String productId;

    private String producName;

    private Integer productNum;

    public String getProducName() {
        return producName;
    }

    public void setProducName(String producName) {
        this.producName = producName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }
}

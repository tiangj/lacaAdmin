package com.example.lacaPackage.DO;

import com.example.lacaPackage.entity.LacaProductOrderDetail;

public class LacaProductOrderDetailDO extends LacaProductOrderDetail {

    private String productIdIndex;

    private String productNameIndex;

    public String getProductIdIndex() {
        return productIdIndex;
    }

    public void setProductIdIndex(String productIdIndex) {
        this.productIdIndex = productIdIndex;
    }

    public String getProductNameIndex() {
        return productNameIndex;
    }

    public void setProductNameIndex(String productNameIndex) {
        this.productNameIndex = productNameIndex;
    }
}

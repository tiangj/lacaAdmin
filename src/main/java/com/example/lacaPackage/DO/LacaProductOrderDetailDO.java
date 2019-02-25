package com.example.lacaPackage.DO;

import com.example.lacaPackage.entity.LacaProductOrderDetail;

public class LacaProductOrderDetailDO extends LacaProductOrderDetail {

    private String productIdIndex;

    private String productNameIndex;

    private String productTypeIndex;

    private Integer productTypeId;

    private String  productTypeNameIndex;

    public String getProductTypeNameIndex() {
        return productTypeNameIndex;
    }

    public void setProductTypeNameIndex(String productTypeNameIndex) {
        this.productTypeNameIndex = productTypeNameIndex;
    }

    public String getProductTypeIndex() {
        return productTypeIndex;
    }

    public void setProductTypeIndex(String productTypeIndex) {
        this.productTypeIndex = productTypeIndex;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

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

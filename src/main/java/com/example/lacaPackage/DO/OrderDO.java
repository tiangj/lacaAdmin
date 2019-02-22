package com.example.lacaPackage.DO;

import com.example.lacaPackage.entity.LacaProductOrder;
import com.example.lacaPackage.entity.LacaProductOrderDetail;

import java.util.List;

public class OrderDO extends LacaProductOrder {

    private List<LacaProductOrderDetail> orderDetailList;

    private String productId;

    private String productName;

    private Integer productNum;

    private String productIds;

    private String productInfos;

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public String getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(String productInfos) {
        this.productInfos = productInfos;
    }

    public List<LacaProductOrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<LacaProductOrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

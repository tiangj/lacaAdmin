package com.example.lacaPackage.DO;

import com.example.lacaPackage.entity.LacaProductOrder;
import com.example.lacaPackage.entity.LacaProductOrderDetail;

import java.util.List;

public class OrderDO extends LacaProductOrder {

    private List<LacaProductOrderDetail> orderDetailList;

    private String productId;

    private String producName;

    private Integer productNum;

    private Integer totalNum;

    @Override
    public Integer getTotalNum() {
        return totalNum;
    }

    @Override
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public List<LacaProductOrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<LacaProductOrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

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

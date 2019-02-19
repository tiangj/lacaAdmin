package com.example.lacaPackage.DO;

import com.example.lacaPackage.entity.LacaProductOrder;

public class OrderDO extends LacaProductOrder {

    private String producName;

    public String getProducName() {
        return producName;
    }

    public void setProducName(String producName) {
        this.producName = producName;
    }
}

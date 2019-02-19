package com.example.lacaPackage.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.lacaPackage.DO.OrderDO;
import com.example.lacaPackage.DO.ProductDO;
import com.example.lacaPackage.entity.LacaProductOrder;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator-plus123
 * @since 2019-02-13
 */
public interface ILacaProductOrderService extends IService<LacaProductOrder> {

    Page<OrderDO> getAllOrderList(Page<OrderDO> page, OrderDO orderDO);

    OrderDO getOrderInfoById(String id);

}

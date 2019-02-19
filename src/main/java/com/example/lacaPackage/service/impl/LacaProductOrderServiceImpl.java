package com.example.lacaPackage.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.lacaPackage.DO.OrderDO;
import com.example.lacaPackage.entity.LacaProduct;
import com.example.lacaPackage.entity.LacaProductOrder;
import com.example.lacaPackage.mapper.LacaProductOrderMapper;
import com.example.lacaPackage.service.ILacaProductOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator-plus123
 * @since 2019-02-13
 */
@Service
public class LacaProductOrderServiceImpl extends ServiceImpl<LacaProductOrderMapper, LacaProductOrder> implements ILacaProductOrderService {

    @Autowired
    private LacaProductOrderMapper lacaProductOrderMapper;

    @Override
    public Page<OrderDO> getAllOrderList(Page<OrderDO> page, OrderDO orderDO) {
        page.setRecords(lacaProductOrderMapper.getAllOrderList(page,orderDO));
        return page;
    }

    @Override
    public OrderDO getOrderInfoById(String id) {
        return lacaProductOrderMapper.getOrderInfoById(id);
    }
}

package com.example.lacaPackage.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.lacaPackage.DO.OrderDO;
import com.example.lacaPackage.entity.LacaProductOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author generator-plus123
 * @since 2019-02-13
 */
@Component
public interface LacaProductOrderMapper extends BaseMapper<LacaProductOrder> {

    List<OrderDO> getAllOrderList(Page<OrderDO> page, OrderDO orderDO);

    OrderDO getOrderInfoById(String id);

}

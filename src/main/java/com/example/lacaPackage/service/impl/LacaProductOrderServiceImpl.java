package com.example.lacaPackage.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.lacaPackage.DO.OrderDO;
import com.example.lacaPackage.entity.LacaProductOrder;
import com.example.lacaPackage.entity.LacaProductOrderDetail;
import com.example.lacaPackage.entity.LacaProductStock;
import com.example.lacaPackage.mapper.LacaProductOrderDetailMapper;
import com.example.lacaPackage.mapper.LacaProductOrderMapper;
import com.example.lacaPackage.mapper.LacaProductStockMapper;
import com.example.lacaPackage.service.ILacaProductOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private LacaProductOrderDetailMapper lacaProductOrderDetailMapper;

    @Autowired
    private LacaProductStockMapper lacaProductStockMapper;

    @Override
    public Page<OrderDO> getAllOrderList(Page<OrderDO> page, OrderDO orderDO) {
        page.setRecords(lacaProductOrderMapper.getAllOrderList(page,orderDO));
        return page;
    }

    @Override
    public OrderDO getOrderInfoById(String id) {
        return lacaProductOrderMapper.getOrderInfoById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> saveOrder(OrderDO orderDO,String userId) throws Exception {
        Map<String,Object> result=new HashMap<>();
        try{
            LacaProductOrder lacaProductOrder=new LacaProductOrder();
            lacaProductOrder.setTotalNum(orderDO.getTotalNum());
            lacaProductOrder.setSaleType(orderDO.getSaleType());
            lacaProductOrder.setJoinShop(orderDO.getJoinShop());
            lacaProductOrder.setCustomerName(orderDO.getCustomerName());
            lacaProductOrder.setDesigner(orderDO.getDesigner());
            lacaProductOrder.setUserShopFee(orderDO.getUserShopFee());
            lacaProductOrder.setCashPayPrice(orderDO.getCashPayPrice());
            lacaProductOrder.setCardPayPrice(orderDO.getCardPayPrice());
            lacaProductOrder.setWxPayPrice(orderDO.getWxPayPrice());
            lacaProductOrder.setAlipayPayPrice(orderDO.getAlipayPayPrice());
            lacaProductOrder.setTotalPrice(orderDO.getTotalPrice());
            lacaProductOrder.setPostWay(orderDO.getPostWay());
            lacaProductOrder.setRemark(orderDO.getRemark());
            if(StringUtils.isNotBlank(orderDO.getInnerOrderId())){
                lacaProductOrder.setStatus("3");
            }else{
                lacaProductOrder.setStatus(orderDO.getStatus());
            }
            lacaProductOrder.setInnerOrderId(orderDO.getInnerOrderId());
            //保存或者订单主表
            if(orderDO.getId()!=null){
                lacaProductOrder.setUpdateDate(new Date());
                lacaProductOrder.setUpdateUser(userId);
                lacaProductOrderMapper.updateById(lacaProductOrder);
            }else{
                lacaProductOrder.setCreateDate(new Date());
                lacaProductOrder.setCreateUser(userId);
                lacaProductOrder.setUpdateDate(new Date());
                lacaProductOrder.setUpdateUser(userId);
                lacaProductOrderMapper.insert(lacaProductOrder);
            }

            Integer orderMainId=lacaProductOrder.getId();

            Integer saleType=lacaProductOrder.getSaleType();

            //修改详情表
            EntityWrapper<LacaProductOrderDetail> lacaProductOrderDetailEntityWrapper=new EntityWrapper<>();
            lacaProductOrderDetailEntityWrapper.eq("order_id",orderMainId);
            lacaProductOrderDetailMapper.delete(lacaProductOrderDetailEntityWrapper);
            for(LacaProductOrderDetail lacaProductOrderDetail:orderDO.getOrderDetailList()){
                  lacaProductOrderDetail.setOrderId(orderMainId+"");
                  lacaProductOrderDetailMapper.insert(lacaProductOrderDetail);

                  String productId=lacaProductOrderDetail.getProductId();
                  LacaProductStock  lacaProductStock=new LacaProductStock();
                  lacaProductStock.setProductId(productId);

                  lacaProductStock=lacaProductStockMapper.selectOne(lacaProductStock);

                  //进
                  if(saleType==1){
                      lacaProductStock.setIncomeNum(lacaProductOrderDetail.getProductNum()+lacaProductStock.getIncomeNum());
                      lacaProductStock.setExsitNum(lacaProductOrderDetail.getProductNum()+lacaProductStock.getExsitNum());
                  }
                  //出
                  if(saleType==2){
                      lacaProductStock.setExsitNum(lacaProductStock.getExsitNum()-lacaProductOrderDetail.getProductNum());
                      lacaProductStock.setOutNum(lacaProductOrderDetail.getProductNum()+lacaProductStock.getOutNum());
                  }

                  //修改产品的库存
                  lacaProductStock.setUpdateDate(new Date());
                  lacaProductStock.setUpdateUser(userId);

                  lacaProductStockMapper.updateById(lacaProductStock);
            }
            result.put("code",1);
            result.put("msg","操作成功");
        }catch (Exception e){
            result.put("code",0);
            result.put("msg","操作失败");
            throw new Exception();
        }
        return result;
    }
}

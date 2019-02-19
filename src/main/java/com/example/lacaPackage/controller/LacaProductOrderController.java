package com.example.lacaPackage.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.lacaPackage.DO.OrderDO;
import com.example.lacaPackage.DO.ProductDO;
import com.example.lacaPackage.entity.LacaProduct;
import com.example.lacaPackage.service.ILacaProductOrderService;
import com.example.lacaPackage.service.ILacaProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author generator-plus123
 * @since 2019-02-13
 */
@Controller
@RequestMapping("/lacaProductOrder")
public class LacaProductOrderController {

    @Autowired
    private ILacaProductOrderService productOrderService;

    private ILacaProductService lacaProductService;

    @RequestMapping("list")
    public String list(){
        return "order/list";
    }

    @ResponseBody
    @RequestMapping("listData")
    public Map<String,Object> listData(Integer page, Integer limit,String name){
        Page<OrderDO> orderDOPage=new Page<>();
        orderDOPage.setLimit(limit);
        orderDOPage.setCurrent(page);
        OrderDO orderDO=new OrderDO();
        if(StringUtils.isNotBlank(name)){
            orderDO.setProducName(name);
        }
        Page<OrderDO> pageList=productOrderService.getAllOrderList(orderDOPage,orderDO);
        Map<String,Object> result=new HashMap<>();
        result.put("code",0);
        result.put("msg","");
        result.put("count",pageList.getTotal());
        result.put("data",pageList.getRecords());
        return result;
    }

    @RequestMapping("toAdd")
    public String toAdd(Model model, String id){
        OrderDO orderDO=null;
        if(StringUtils.isBlank(id)){
            orderDO=new OrderDO();
        }else{
            orderDO=productOrderService.getOrderInfoById(id);
        }
        model.addAttribute("orderDO",orderDO);
        //获取所有的商品
        EntityWrapper<LacaProduct> entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("delete_flag",0);
        List<LacaProduct> lacaProductList=lacaProductService.selectList(entityWrapper);
        model.addAttribute("lacaProductList",lacaProductList);

        return "order/add";
    }

    @ResponseBody
    @RequestMapping("saveOrder")
    public Map<String,Object> saveOrder(){
        return null;
    }

}


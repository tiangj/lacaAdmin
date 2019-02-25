package com.example.lacaPackage.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.config.ConstantUtil;
import com.example.lacaPackage.DO.LacaProductOrderDetailDO;
import com.example.lacaPackage.DO.OrderDO;
import com.example.lacaPackage.entity.LacaProduct;
import com.example.lacaPackage.entity.LacaProductOrder;
import com.example.lacaPackage.entity.LacaProductOrderDetail;
import com.example.lacaPackage.entity.LacaProductType;
import com.example.lacaPackage.service.ILacaProductOrderDetailService;
import com.example.lacaPackage.service.ILacaProductOrderService;
import com.example.lacaPackage.service.ILacaProductService;
import com.example.lacaPackage.service.ILacaProductTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private ILacaProductService lacaProductService;

    @Autowired
    private ILacaProductOrderDetailService lacaProductOrderDetailService;

    @Autowired
    private ILacaProductTypeService lacaProductTypeService;

    @RequestMapping("list")
    public String list(Model model){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        model.addAttribute("endDate",sdf.format(date));
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-30);
        model.addAttribute("beginDate",sdf.format(calendar.getTime()));
        return "order/list";
    }

    @ResponseBody
    @RequestMapping("listData")
    public Map<String,Object> listData(Integer page, Integer limit,String productName,String saleType,
                                       String joinShop,String customerName,String designer,String beginDate,String endDate){
        Page<OrderDO> orderDOPage=new Page<>();
        orderDOPage.setLimit(limit);
        orderDOPage.setCurrent(page);
        OrderDO orderDO=new OrderDO();
        if(StringUtils.isNotBlank(productName)){
            orderDO.setProductName(productName);
        }
        if(StringUtils.isNotBlank(saleType)){
            orderDO.setSaleType(Integer.parseInt(saleType));
        }
        if(StringUtils.isNotBlank(joinShop)){
            orderDO.setJoinShop(joinShop);
        }
        if(StringUtils.isNotBlank(customerName)){
            orderDO.setCustomerName(customerName);
        }
        if(StringUtils.isNotBlank(designer)){
            orderDO.setDesigner(designer);
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        if(StringUtils.isNotBlank(beginDate)){
            try {
                orderDO.setBeginDate(sdf.parse(beginDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(StringUtils.isNotBlank(endDate)){
            try {
                Date endDateResult=sdf.parse(endDate);

                Calendar calendar=Calendar.getInstance();
                calendar.setTime(endDateResult);
                calendar.add(Calendar.DATE,+1);
                orderDO.setEndDate(calendar.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
        if(orderDO.getPostWay()==null){
           orderDO.setPostWay("");
        }
        //根据订单id获取订单详情
        EntityWrapper<LacaProductOrderDetail> orderDetailEntityWrapper=new EntityWrapper<>();
        orderDetailEntityWrapper.eq("order_id",id);
        orderDetailEntityWrapper.eq("delete_flag",0);
        List<LacaProductOrderDetail> orderDetailList=lacaProductOrderDetailService.selectList(orderDetailEntityWrapper);
        List<LacaProductOrderDetailDO> lacaProductOrderDetailDOList=new ArrayList<>();

        int i=1;
        for (LacaProductOrderDetail lacaProductOrderDetail:orderDetailList){
            LacaProductOrderDetailDO lacaProductOrderDetailDO=new LacaProductOrderDetailDO();
            lacaProductOrderDetailDO.setProductIdIndex("productId_"+i);
            lacaProductOrderDetailDO.setProductNameIndex("productName_"+i);
            lacaProductOrderDetailDO.setId(lacaProductOrderDetail.getId());
            lacaProductOrderDetailDO.setProductId(lacaProductOrderDetail.getProductId());

            EntityWrapper<LacaProduct> entityWrapper=new EntityWrapper<>();
            entityWrapper.eq("id",lacaProductOrderDetail.getProductId());
            entityWrapper.eq("delete_flag",0);
            LacaProduct lacaProduct=lacaProductService.selectOne(entityWrapper);

            lacaProductOrderDetailDO.setProductNum(lacaProductOrderDetail.getProductNum());
            lacaProductOrderDetailDO.setProductTypeId(lacaProduct.getProductTypeId());
            lacaProductOrderDetailDO.setProductTypeIndex("productType_"+i);

            lacaProductOrderDetailDOList.add(lacaProductOrderDetailDO);
            i++;

        }

        model.addAttribute("orderDetailList",lacaProductOrderDetailDOList);
        model.addAttribute("exsitOrder",lacaProductOrderDetailDOList.size());

        //获取所有的商品
        EntityWrapper<LacaProduct> entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("delete_flag",0);
        List<LacaProduct> lacaProductList=lacaProductService.selectList(entityWrapper);
        model.addAttribute("lacaProductList",lacaProductList);

        //获取所有商品类型
        EntityWrapper<LacaProductType> entityTypeWrapper=new EntityWrapper<>();
        entityTypeWrapper.eq("delete_flag",0);
        List<LacaProductType> lacaProductTypeList=lacaProductTypeService.selectList(entityTypeWrapper);
        model.addAttribute("lacaProductTypeList",lacaProductTypeList);

        return "order/add";
    }

    @ResponseBody
    @RequestMapping("getAllLacaProduct")
    public List<LacaProduct> getAllLacaProduct(Integer productTypeId){
        //获取所有的商品
        EntityWrapper<LacaProduct> entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("delete_flag",0);
        entityWrapper.eq("product_type_id",productTypeId);
        return lacaProductService.selectList(entityWrapper);
    }

    @ResponseBody
    @RequestMapping("getAllLacaProductType")
    public List<LacaProductType> getAllLacaProductType(){
        //获取所有商品类型
        EntityWrapper<LacaProductType> entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("delete_flag",0);
        return lacaProductTypeService.selectList(entityWrapper);
    }

    @ResponseBody
    @RequestMapping("saveOrder")
    public Map<String,Object> saveOrder(OrderDO orderDO, HttpServletRequest request){
        Map<String,Object> result=new HashMap<>();

        String userId=request.getSession().getAttribute(ConstantUtil.SEESION_USER_ID).toString();
        //不同产品的类型数
        Integer prductTypeSize=Integer.parseInt(request.getParameter("productTypeSize"));

        List<LacaProductOrderDetail> productOrderDetailList=new ArrayList<>();
        Integer totalNum=0;
        for (int i=1;i<=prductTypeSize;i++){
            String productId=request.getParameter("productId_"+i);
            Integer productNum=Integer.parseInt(request.getParameter("productNum_"+i));

            LacaProductOrderDetail lacaProductOrderDetail=new LacaProductOrderDetail();
            lacaProductOrderDetail.setProductId(productId);
            lacaProductOrderDetail.setProductNum(productNum);
            lacaProductOrderDetail.setCreateDate(new Date());
            lacaProductOrderDetail.setCreateUser(userId);
            lacaProductOrderDetail.setUpdateDate(new Date());
            lacaProductOrderDetail.setUpdateUser(userId);
            //lacaProductOrderDetail.setProductPrice();
            productOrderDetailList.add(lacaProductOrderDetail);
            totalNum+=productNum;
        }
        orderDO.setTotalNum(totalNum);
        orderDO.setOrderDetailList(productOrderDetailList);
        try {
            result=productOrderService.saveOrder(orderDO,userId);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code",0);
            result.put("msg","操作失败");
        }
        return result;
    }

    /****
     * 跳转至选择用户订单列表
     * @param model
     * @param customerName
     * @return
     */
    @RequestMapping("toSelectCustomerInfo")
    public String toSelectCustomerInfo(Model model,String customerName){
        model.addAttribute("customerName",customerName);
        return "order/selectCustomerInfo";
    }

    /****
     *根据客户姓名获取订单信息
     * @param customerName
     * @return
     */
    @ResponseBody
    @RequestMapping("getProductOrderInfo")
    public Map<String,Object>  getProductOrderInfo(String customerName,String orderId){
        EntityWrapper<LacaProductOrder> entityWrapper=new EntityWrapper<>();
        if(StringUtils.isNotBlank(customerName)){
            entityWrapper.like("customer_name",customerName);
        }
        entityWrapper.eq("deleteFlag",0);
        entityWrapper.eq("sale_type",2);
        entityWrapper.ne("id",orderId);
        List<LacaProductOrder> list=productOrderService.selectList(entityWrapper);
        Map<String,Object> result=new HashMap<>();
        result.put("code",0);
        result.put("msg","");
        result.put("count",list.size());
        result.put("data",list);
        return result;
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();

        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-30);
        calendar.getTime();
        System.out.println(sdf.format(calendar.getTime()));
    }
}


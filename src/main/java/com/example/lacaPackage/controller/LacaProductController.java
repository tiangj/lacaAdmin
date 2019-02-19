package com.example.lacaPackage.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.config.ConstantUtil;
import com.example.lacaPackage.DO.ProductDO;
import com.example.lacaPackage.entity.LacaProductType;
import com.example.lacaPackage.service.ILacaProductService;
import com.example.lacaPackage.service.ILacaProductTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/lacaProduct")
public class LacaProductController {

    @Autowired
    private ILacaProductService lacaProductService;

    @Autowired
    private ILacaProductTypeService lacaProductTypeService;

    @RequestMapping("list")
    public String list(){
        return "product/list";
    }

    @ResponseBody
    @RequestMapping("listData")
    public Map<String,Object> listData(Integer page, Integer limit, String name){
        Page<ProductDO> productDOPage=new Page<>();
        productDOPage.setLimit(limit);
        productDOPage.setCurrent(page);
        ProductDO productDO=new ProductDO();
        if(StringUtils.isNotBlank(name)){
            productDO.setProductName(name);
        }
        Page<ProductDO> pageList=lacaProductService.getAllProductList(productDOPage,productDO);
        Map<String,Object> result=new HashMap<>();
        result.put("code",0);
        result.put("msg","");
        result.put("count",pageList.getTotal());
        result.put("data",pageList.getRecords());
        return result;
    }

    @RequestMapping("toAdd")
    public String toAdd(Model model, String id){
        ProductDO productDO=null;
        if(StringUtils.isBlank(id)){
            productDO=new ProductDO();
        }else{
            productDO=lacaProductService.getProductInfoById(id);
        }
        //获取所有的商品类型
        EntityWrapper<LacaProductType> entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("delete_flag",0);
        List<LacaProductType> productTypeList=lacaProductTypeService.selectList(entityWrapper);
        model.addAttribute("productTypeList",productTypeList);

        model.addAttribute("productDO",productDO);
        return "product/add";
    }

    @ResponseBody
    @RequestMapping("saveProduct")
    public Map<String,Object> saveProduct(ProductDO productDO, HttpServletRequest request){
        String userId=request.getSession().getAttribute(ConstantUtil.SEESION_USER_ID).toString();
        Map<String,Object> result=new HashMap<>();
        try {
            result=lacaProductService.saveProduct(productDO,userId);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code",0);
            result.put("msg","操作失败");
        }
        return result;
    }


}


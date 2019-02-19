package com.example.lacaPackage.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.config.ConstantUtil;
import com.example.lacaPackage.entity.LacaProductType;
import com.example.lacaPackage.service.ILacaProductTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 产品类型 前端控制器
 * </p>
 *
 * @author generator-plus123
 * @since 2019-02-13
 */
@Controller
@RequestMapping("/lacaProductType")
public class LacaProductTypeController {

    @Autowired
    private ILacaProductTypeService lacaProductTypeService;

    @RequestMapping("list")
    public String list(){
        return "productType/list";
    }

    @ResponseBody
    @RequestMapping("listData")
    public Map<String,Object> listData(Integer page, Integer limit, String name){
        Page<LacaProductType> lacaProductTypePage=new Page<>();
        lacaProductTypePage.setLimit(limit);
        lacaProductTypePage.setCurrent(page);
        EntityWrapper<LacaProductType> lacaProductTypeEntityWrapper=new EntityWrapper<>();
        if(StringUtils.isNotBlank(name)){
            lacaProductTypeEntityWrapper.like("name",name);
        }
        lacaProductTypeEntityWrapper.eq("delete_flag","0");
        lacaProductTypeEntityWrapper.orderBy("create_date",false);

        Page<LacaProductType> productTypePage=lacaProductTypeService.selectPage(lacaProductTypePage,lacaProductTypeEntityWrapper);
        Map<String,Object> result=new HashMap<>();
        result.put("code",0);
        result.put("msg","");
        result.put("count",productTypePage.getTotal());
        result.put("data",productTypePage.getRecords());
        return result;
    }


    @RequestMapping("toAdd")
    public String toAdd(Model model, String id){
        LacaProductType lacaProductType=null;
        if(StringUtils.isBlank(id)){
            lacaProductType=new LacaProductType();
        }else{
            lacaProductType=lacaProductTypeService.selectById(id);
        }
        model.addAttribute("lacaProductType",lacaProductType);
        return "productType/add";
    }

    @ResponseBody
    @RequestMapping("saveProductType")
    public Map<String,Object> saveProductType(LacaProductType lacaProductType, HttpServletRequest request){
        String userId=request.getSession().getAttribute(ConstantUtil.SEESION_USER_ID).toString();

        Boolean flag=false;
        Map<String,Object> result=new HashMap<>();
        if(lacaProductType.getId()!=null){
            lacaProductType.setCreateDate(new Date());
            lacaProductType.setUpdateDate(new Date());
            lacaProductType.setCreateUser(userId);
            lacaProductType.setUpdateUser(userId);
            flag=lacaProductTypeService.updateById(lacaProductType);
        }else{
            flag=lacaProductTypeService.insert(lacaProductType);
        }
        if(flag){
            result.put("code",1);
            result.put("msg","操作成功");
        }else{
            result.put("code",0);
            result.put("msg","操作失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("delProductType")
    public Map<String,Object> delProductType(Integer id){
        LacaProductType lacaProductType=new LacaProductType();
        lacaProductType.setId(id);
        lacaProductType.setDeleteFlag(1);
        Boolean flag=lacaProductTypeService.updateById(lacaProductType);
        Map<String,Object> result=new HashMap<>();
        if(flag){
            result.put("code",1);
            result.put("msg","删除成功");
        }else{
            result.put("code",0);
            result.put("msg","删除失败");
        }
        return result;
    }
}


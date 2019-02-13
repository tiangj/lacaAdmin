package com.example.sys.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.config.ConstantUtil;
import com.example.sys.DO.SysMenusListDO;
import com.example.sys.entity.SysMenu;
import com.example.sys.service.ISysMenuService;
import com.example.wwq.DO.ProductDO;
import com.example.wwq.entity.WwqSort;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author generator-plus123
 * @since 2018-12-27
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;

    /****
     * 跳转至菜单list
     * @return
     */
    @RequestMapping("list")
    public String list(){
        return "sys/menuList";
    }

    @ResponseBody
    @RequestMapping("listData")
    public Map<String,Object> listData(Integer page, Integer limit, String name){
        Page<SysMenusListDO> sysMenusListDOPage=new Page<>();
        sysMenusListDOPage.setLimit(limit);
        sysMenusListDOPage.setCurrent(page);
        SysMenusListDO sysMenusListDO=new SysMenusListDO();
        if(StringUtils.isNotBlank(name)){
            sysMenusListDO.setMenuName(name);
        }
        Page<SysMenusListDO> pageList=sysMenuService.getAllMenus(sysMenusListDOPage,sysMenusListDO);
        Map<String,Object> result=new HashMap<>();
        result.put("code",0);
        result.put("msg","");
        result.put("count",pageList.getTotal());
        result.put("data",pageList.getRecords());
        return result;
    }

    /****
     * 跳转至新增菜单页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("toAdd")
    public String  toAdd(Model model, String id){
        SysMenu sysMenu=null;
        if(StringUtils.isNotBlank(id)){
            sysMenu=sysMenuService.selectById(id);
        }else{
            sysMenu=new SysMenu();
        }

        //获取所有的一级菜单
        EntityWrapper<SysMenu> entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("parent_id","");
        entityWrapper.eq("del_flag",0);
        List<SysMenu> firstMenusList=sysMenuService.selectList(entityWrapper);
        firstMenusList.add(0,new SysMenu());

        model.addAttribute("firstMenusList",firstMenusList);

        model.addAttribute("sysMenu",sysMenu);
        return "sys/addMenu";
    }

    /****
     * 保存菜单
     * @param sysMenu
     * @return
     */
    @RequestMapping("saveMenu")
    @ResponseBody
    public Map<String,Object> saveMenu(SysMenu sysMenu){

        Boolean flag=false;
        Map<String,Object> result=new HashMap<>();
        if(sysMenu.getId()!=null){
            sysMenu.setCreateDate(new Date());
            sysMenu.setUpdateDate(new Date());
            flag=sysMenuService.updateById(sysMenu);
        }else{
            flag=sysMenuService.insert(sysMenu);
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

    /****
     * 删除菜单
     * @return
     */
    @ResponseBody
    @RequestMapping("delMenu")
    public Map<String,Object> delMenu(Integer id){
        SysMenu sysMenu=new SysMenu();
        sysMenu.setId(id);
        sysMenu.setDelFlag("1");
        Boolean flag=sysMenuService.updateById(sysMenu);
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



    /****
     * 根据用户id获取菜单信息
     * @return
     */
    @ResponseBody
    @RequestMapping("getMenusByUserId")
    public Map<String,Object> getMenusByUserId(String userId){
        return sysMenuService.getMenusByUserId(userId);
    }

}


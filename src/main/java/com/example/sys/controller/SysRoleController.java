package com.example.sys.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.sys.entity.SysMenu;
import com.example.sys.entity.SysRole;
import com.example.sys.entity.SysRoleMenu;
import com.example.sys.service.ISysMenuService;
import com.example.sys.service.ISysRoleMenuService;
import com.example.sys.service.ISysRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author generator-plus123
 * @since 2018-12-27
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    /****
     * 跳转至权限list
     * @return
     */
    @RequestMapping("list")
    public String list(){
       return "sys/roleList";
    }

    /******
     * 权限列表
     * @return
     */
    @ResponseBody
    @RequestMapping("listData")
    public Map<String,Object> listData(Integer page, Integer limit, String name){
        Page<SysRole> sysRolePage=new Page<>();
        sysRolePage.setLimit(limit);
        sysRolePage.setCurrent(page);
        EntityWrapper<SysRole> sysRoleEntityWrapper=new EntityWrapper<>();
        if(StringUtils.isNotBlank(name)){
            sysRoleEntityWrapper.like("name",name);
        }
        sysRoleEntityWrapper.like("del_flag","0");
        sysRoleEntityWrapper.orderBy("create_date",false);

        Page<SysRole> rolePage=sysRoleService.selectPage(sysRolePage,sysRoleEntityWrapper);
        Map<String,Object> result=new HashMap<>();
        result.put("code",0);
        result.put("msg","");
        result.put("count",rolePage.getTotal());
        result.put("data",rolePage.getRecords());
        return result;
    }

    /****
     * 跳转至新增权限页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("toAdd")
    public String  toAdd(Model model, String id){
        SysRole sysRole=null;
        if(StringUtils.isNotBlank(id)){
            sysRole=sysRoleService.selectById(id);
        }else{
            sysRole=new SysRole();
        }
        model.addAttribute("sysRole",sysRole);
        return "sys/addRole";
    }

    /****
     * 保存权限信息
     * @param sysRole
     * @return
     */
    @RequestMapping("saveRole")
    @ResponseBody
    public Map<String,Object> saveRole(SysRole sysRole){

        Boolean flag=false;
        Map<String,Object> result=new HashMap<>();
        if(sysRole.getId()!=null){
            sysRole.setCreateDate(new Date());
            sysRole.setUpdateDate(new Date());
            flag=sysRoleService.updateById(sysRole);
        }else{
            flag=sysRoleService.insert(sysRole);
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
     * 删除权限
     * @return
     */
    @ResponseBody
    @RequestMapping("delRole")
    public Map<String,Object> delRole(Integer id){
        SysRole sysRole=new SysRole();
        sysRole.setId(id);
        sysRole.setDelFlag("1");
        Boolean flag=sysRoleService.updateById(sysRole);
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

    @RequestMapping("editRoleMenu")
    public String editRoleMenu(Model model,Integer id){
        //获取所有的一级菜单
        EntityWrapper<SysMenu> entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("del_flag",0);
        entityWrapper.eq("parent_id","");
        entityWrapper.orderBy("sort");
        List<SysMenu> sysMenuFirstList=sysMenuService.selectList(entityWrapper);


        //获取所有的二级菜单
        EntityWrapper<SysMenu> entitySecondWrapper=new EntityWrapper<>();
        entitySecondWrapper.addFilter("parent_id !=''");
        entitySecondWrapper.eq("del_flag",0);
        entitySecondWrapper.orderBy("sort");
        List<SysMenu> sysMenuSecondList=sysMenuService.selectList(entitySecondWrapper);


        //获取该角色下所有的菜单
        EntityWrapper<SysRoleMenu> roleMenuEntityWrapper=new EntityWrapper<>();
        roleMenuEntityWrapper.eq("role_id",id);
        List<SysRoleMenu> sysRoleMenuList=sysRoleMenuService.selectList(roleMenuEntityWrapper);

        List<Map<String,Object>> firstList=new ArrayList<>();
        for(SysMenu sysMenu:sysMenuFirstList){
            Map<String,Object> map=new HashMap<>();
            map.put("id",sysMenu.getId());
            map.put("name",sysMenu.getName());
            map.put("open",false);
            map.put("checked",false);
            for(SysRoleMenu sysRoleMenu:sysRoleMenuList){
                if(sysRoleMenu.getMenuId().equals(sysMenu.getId().toString())){
                    map.put("checked",true);
                }
            }
            firstList.add(map);
        }

        List<Map<String,Object>> secondList=new ArrayList<>();
        for(SysMenu sysMenu:sysMenuSecondList){
            Map<String,Object> map=new HashMap<>();
            map.put("id",sysMenu.getId());
            map.put("parentId",sysMenu.getParentId());
            map.put("name",sysMenu.getName());
            map.put("checked",false);
            for(SysRoleMenu sysRoleMenu:sysRoleMenuList){
                if(sysRoleMenu.getMenuId().equals(sysMenu.getId().toString())){
                    map.put("checked",true);
                }
            }
            secondList.add(map);
        }

        List<Map<String,Object>> list=new ArrayList<>();
        for(Map<String,Object> mapFirst:firstList){
            Map<String,Object> map=new HashMap<>();
            List<Map<String,Object>> secondMenuList=new ArrayList<>();
            for(Map<String,Object> mapSecond:secondList){
                if(mapSecond.get("parentId").toString().equals(mapFirst.get("id").toString())){
                    //mapFirst.put("checked",true);
                    mapFirst.put("open",true);
                    secondMenuList.add(mapSecond);
                    mapFirst.put("children",secondMenuList);
                }
            }
            map.putAll(mapFirst);
            list.add(map);
        }

        SysRole sysRole=sysRoleService.selectById(id);

        model.addAttribute("menusList", JSON.toJSON(list));
        model.addAttribute("roleId",id);
        model.addAttribute("name",sysRole.getName());

        return "sys/editRoleMenu";
    }

}


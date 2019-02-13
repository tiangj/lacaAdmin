package com.example.sys.controller;


import com.example.sys.service.ISysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author generator-plus123
 * @since 2018-12-27
 */
@Controller
@RequestMapping("/sysRoleMenu")
public class SysRoleMenuController {

    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    /***
     * 保存权限菜单信息
     * @param roleId
     * @param menuIds
     * @return
     */
    @ResponseBody
    @RequestMapping("saveRoleMenu")
    public Map<String,Object> saveRoleMenu(Integer roleId,String menuIds){
        Map<String,Object> resultMap=new HashMap<>();
        try {
            Integer result=sysRoleMenuService.saveRoleMenu(roleId,menuIds);
            if(result>0){
                resultMap.put("code",1);
                resultMap.put("msg","操作成功");
            }else {
                resultMap.put("code",0);
                resultMap.put("msg","操作失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("code",0);
            resultMap.put("msg","操作失败");
        }
        return resultMap;
    }
}


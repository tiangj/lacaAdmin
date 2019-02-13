package com.example.sys.controller;


import com.example.sys.service.ISysUserRoleService;
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
@RequestMapping("/sysUserRole")
public class SysUserRoleController {

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @ResponseBody
    @RequestMapping("saveUserRole")
    public Map<String,Object> saveUserRole(Integer userId,String roleId){
        Map<String,Object> result=new HashMap<>();

        try {
            Integer flag=sysUserRoleService.saveUserRole(userId,roleId);
            if(flag>0){
                result.put("code",1);
                result.put("msg","操作成功");
            }else{
                result.put("code",0);
                result.put("msg","操作失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code",0);
            result.put("msg","操作失败");
        }
        return result;
    }
}


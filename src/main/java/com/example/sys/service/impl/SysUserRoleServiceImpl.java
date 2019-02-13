package com.example.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.sys.entity.SysUserRole;
import com.example.sys.mapper.SysUserRoleMapper;
import com.example.sys.service.ISysUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator-plus123
 * @since 2018-12-27
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveUserRole(Integer userId, String roleId) throws Exception{

        Integer flag=0;
        EntityWrapper<SysUserRole> sysUserRoleEntityWrapper=new EntityWrapper<>();
        sysUserRoleEntityWrapper.eq("user_id",userId);
        sysUserRoleMapper.delete(sysUserRoleEntityWrapper);

        if(roleId.contains(",")){
           String[] strs=roleId.split(",");
           for(String s:strs){
               SysUserRole sysUserRole=new SysUserRole();
               sysUserRole.setUserId(userId+"");
               sysUserRole.setRoleId(s);
               flag=sysUserRoleMapper.insert(sysUserRole);
           }
        }else{
            SysUserRole sysUserRole=new SysUserRole();
            sysUserRole.setRoleId(roleId);
            sysUserRole.setUserId(userId+"");
            flag=sysUserRoleMapper.insert(sysUserRole);
        }
        return flag;
    }
}

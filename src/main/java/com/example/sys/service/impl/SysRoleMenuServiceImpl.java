package com.example.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.sys.entity.SysRoleMenu;
import com.example.sys.mapper.SysRoleMenuMapper;
import com.example.sys.service.ISysRoleMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
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
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Transactional
    @Override
    public Integer saveRoleMenu(Integer roleId, String menuIds) throws Exception {
        Integer result=0;
        EntityWrapper<SysRoleMenu> entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("role_id",roleId);
        result=sysRoleMenuMapper.delete(entityWrapper);

        if(menuIds.contains(",")){
           String[] menusStr= menuIds.split(",");
           for (String str:menusStr){
               SysRoleMenu sysRoleMenu=new SysRoleMenu();
               sysRoleMenu.setRoleId(roleId+"");
               sysRoleMenu.setMenuId(str);
               result=sysRoleMenuMapper.insert(sysRoleMenu);
           }
        }else if (StringUtils.isNotBlank(menuIds) && menuIds.contains(".")==false){
            SysRoleMenu sysRoleMenu=new SysRoleMenu();
            sysRoleMenu.setRoleId(roleId+"");
            sysRoleMenu.setMenuId(menuIds);
            result=sysRoleMenuMapper.insert(sysRoleMenu);
        }
        return result;
    }
}

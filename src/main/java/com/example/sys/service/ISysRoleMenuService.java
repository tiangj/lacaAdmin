package com.example.sys.service;

import com.example.sys.entity.SysRoleMenu;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator-plus123
 * @since 2018-12-27
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {

    public Integer saveRoleMenu(Integer roleId,String menuIds) throws Exception ;

}

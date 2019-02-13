package com.example.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.sys.DO.SysMenusListDO;
import com.example.sys.entity.SysMenu;
import com.baomidou.mybatisplus.service.IService;
import com.example.wwq.DO.ProductDO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author generator-plus123
 * @since 2018-12-27
 */
public interface ISysMenuService extends IService<SysMenu> {

    /******
     * 根据用户id获取菜单信息
     * @param userId
     * @return
     */
    Map<String, Object> getMenusByUserId(String userId);

    Page<SysMenusListDO> getAllMenus(Page<SysMenusListDO> page, SysMenusListDO sysMenusListDO);

}

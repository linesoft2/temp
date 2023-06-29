package com.etc.covid19proj.service;

import com.etc.covid19proj.entity.Menu;

import java.util.List;

/**
 * 菜单业务逻辑接口
 */
public interface IMenuService {
    /**
     * 根据角色ID查找角色可以访问的菜单
     * @param rid
     * @return
     */
    public List<Menu> findByRid(Integer rid);

    /**
     * 查询所有菜单
     * @return
     */
    public List<Menu> findAll();
}

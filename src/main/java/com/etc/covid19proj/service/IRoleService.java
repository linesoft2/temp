package com.etc.covid19proj.service;

import com.etc.covid19proj.entity.Role;

import java.util.List;

/**
 * 角色业务逻辑接口
 */
public interface IRoleService {
    /**
     * 查询所有角色
     * @return
     */
    public List<Role> findAll();

    /**
     * 根据指定条件查询角色
     * @param roleParam
     * @return
     */
    public List<Role> findByConditions(Role roleParam);

    /**
     * 保存角色
     * @param roleParam
     * @param menuIds
     */
    public void addRole(Role roleParam,String menuIds);

    /**
     * 修改角色
     * @param roleParam
     * @param menuIds
     */
    public void updateRole(Role roleParam,String menuIds);

    /**
     * 删除角色
     * @param rid
     */
    public void deleteRole(Integer rid);

    /**
     * 删除多个角色
     * @param rids
     */
    public void deleteRoles(String rids);
}

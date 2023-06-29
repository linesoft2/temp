package com.etc.covid19proj.service.impl;

import com.etc.covid19proj.entity.Role;
import com.etc.covid19proj.mapper.RoleMapper;
import com.etc.covid19proj.mapper.UserMapper;
import com.etc.covid19proj.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色的业务逻辑实现类
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    //自动封装Role的Mapper对象
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public List<Role> findAll() {
        return roleMapper.selectAll();
    }

    /**
     * 根据指定条件查询角色的业务逻辑实现
     * @param roleParam
     * @return
     */
    @Override
    public List<Role> findByConditions(Role roleParam) {
        return roleMapper.selectByConditions(roleParam);
    }

    /**
     * 添加角色
     * @param roleParam
     * @param menuIds
     */
    @Override
    public void addRole(Role roleParam, String menuIds) {
        roleMapper.inserRole(roleParam);
        String[] menuIdArray=menuIds.split(",");
        for (String menuId:menuIdArray){
            roleMapper.insertRoleMenu(roleParam.getRid(),Integer.parseInt(menuId));
        }
    }

    /**
     * 更新角色
     * 先删除原有角色的菜单
     * 更新角色信息
     * 插入新的角色的菜单
     * @param roleParam
     * @param menuIds
     */
    @Override
    public void updateRole(Role roleParam, String menuIds) {
        roleMapper.deleteRoleMenu(roleParam.getRid());
        roleMapper.updateRole(roleParam);
        String[] menuIdArray=menuIds.split(",");
        for (String menuId:menuIdArray){
            roleMapper.insertRoleMenu(roleParam.getRid(),Integer.parseInt(menuId));
        }
    }

    /**
     * 删除角色
     * 先删除角色对应的菜单
     * 删除角色对应的用户
     * 删除角色本身
     * @param rid
     */
    @Override
    public void deleteRole(Integer rid) {
        roleMapper.deleteRoleMenu(rid);
        if(userMapper.selectUserByRid(rid)!=null) {
            userMapper.deleteUserByRid(rid);
        }
        roleMapper.deleteRole(rid);
    }

    /**
     * 删除多个角色
     * @param rids
     */
    @Override
    public void deleteRoles(String rids) {
        String[] ridArray=rids.split(",");
        for(String rid:ridArray){
            roleMapper.deleteRoleMenu(Integer.parseInt(rid));
            userMapper.deleteUserByRid(Integer.parseInt(rid));
            roleMapper.deleteRole(Integer.parseInt(rid));
        }
    }
}

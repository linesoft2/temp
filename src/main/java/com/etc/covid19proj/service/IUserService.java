package com.etc.covid19proj.service;

import com.etc.covid19proj.entity.SysUser;

import java.util.List;

/**
 * 用户的业务逻辑接口
 */
public interface IUserService {
    /**
     * 根据指定的用户编号查找用户信息
     * @param uid 用户编号
     * @return
     */
    public SysUser findByUid(Integer uid);

    /**
     * 根据给定条件查找用户信息
     * @param userParam 查找条件封装对象
     * @return 符合条件的用户集合
     */
    public List<SysUser> findByConditions(SysUser userParam);

    /**
     * 添加用户
     * @param userParam
     * @return
     */
    public boolean addUser(SysUser userParam);

    /**
     * 修改用户
     * @param userParam
     * @return
     */
    public boolean updateUser(SysUser userParam);

    /**
     * 删除指定用户
     * @param uid
     * @return
     */
    public void deleteUser(Integer uid);

    /**
     * 删除指定多个用户
     * @param uids
     */
    public void deleteUsers(String uids);
}
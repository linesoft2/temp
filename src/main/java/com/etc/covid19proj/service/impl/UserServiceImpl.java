package com.etc.covid19proj.service.impl;

import com.etc.covid19proj.entity.SysUser;
import com.etc.covid19proj.mapper.UserMapper;
import com.etc.covid19proj.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户的业务逻辑接口的实现类
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    //自动装配用户Mapper对象
    @Resource
    private UserMapper userMapper;

    /**
     * 通过用户id查找用户的业务逻辑实现
     * @param uid 用户编号
     * @return
     */
    @Override
    public SysUser findByUid(Integer uid) {
        return userMapper.selectByUid(uid);
    }

    /**
     * 通过组合条件查找用户业务逻辑实现
     * @param userParam 查找条件封装对象
     * @return
     */
    @Override
    public List<SysUser> findByConditions(SysUser userParam) {
        return userMapper.selectByConditions(userParam);
    }

    /**
     * 添加新的用户信息，需要先判断当前用户的用户名是否在用户表中存在
     * 不存在可以进行插入操作
     * @param userParam
     * @return
     */
    @Override
    public boolean addUser(SysUser userParam) {
        SysUser user=userMapper.selectByUsername(userParam.getUsername());
        if(user==null){
            userMapper.insertUser(userParam);
            return true;
        }
        return false;
    }

    /**
     * 判断当前用户是否存在于表中，存在则执行更新
     * @param userParam
     * @return
     */
    @Override
    public boolean updateUser(SysUser userParam) {
        SysUser user=userMapper.selectByUsername(userParam.getUsername());
        if(user==null || (user!=null && user.getUid()==userParam.getUid())){
            userMapper.updateUser(userParam);
            return true;
        }
        return false;
    }

    /**
     * 删除指定id的用户业务逻辑实现
     * @param uid
     */
    @Override
    public void deleteUser(Integer uid) {
        userMapper.deleteUser(uid);
    }

    /**
     * 批量删除用户，所有的待删除的用户ID存在同一个字符串中，并且不同的id用","分割
     * "1,2,3,4"
     * @param uids
     */
    @Override
    public void deleteUsers(String uids) {
        //字符串分割函数split,分割多个用户id的字符串
        String[] uidArray=uids.split(",");
        for(String uid:uidArray){
            userMapper.deleteUserByRid(Integer.parseInt(uid));
        }
    }
}

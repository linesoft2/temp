package com.etc.covid19proj.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 负责记录登录操作相关信息的对象，实现security接口UserDetails
 * 是系统的值对象，
 */
public class SecurityUser implements UserDetails {

    //User也是security中的内置的类，负责保存登录后的用户相关信息
    private User user;
    private Integer uid;
    private Integer rid;
    private List<Menu> menuList;

    public SecurityUser(){}
    public SecurityUser(User user, Integer uid, List<Menu> menuList,Integer rid) {
        this.user = user;
        this.uid = uid;
        this.menuList = menuList;
        this.rid=rid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 以下所有security的用户有效性的判断方法，因为user表中没有对应的字段，所有
     * 以下所有方法都返回true，
     * isAccountNonExpired判断用户是否过期
     * isAccountNonLocked判断用户是否未必锁定
     * isCredentialsNonExpired判断用户认证是否过期
     * isEnabled用户是否启用
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

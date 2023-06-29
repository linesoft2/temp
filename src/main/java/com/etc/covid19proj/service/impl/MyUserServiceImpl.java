package com.etc.covid19proj.service.impl;

import com.etc.covid19proj.entity.Menu;
import com.etc.covid19proj.entity.SecurityUser;
import com.etc.covid19proj.entity.SysUser;
import com.etc.covid19proj.mapper.MenuMapper;
import com.etc.covid19proj.mapper.RoleMapper;
import com.etc.covid19proj.mapper.UserMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现SpringSecurity内的用户登录访问数据库操作
 */
@Service
public class MyUserServiceImpl implements UserDetailsService {

    //自动装配密码编码对象，用户的mapper对象，角色的mapper对象，菜单的mapper对象
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private MenuMapper menuMapper;

    /**
     * 1,判断用户是否存在
     * 2,判断用户是否合法
     * 3,设置用户权限
     * 4,返回合法用户
     * @param name 用户名
     * @return 返回一个登录成功后记录用户相关的所有信息，权限，角色
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        //根据用户名查找用户并判断用户名是否合法，否则抛出异常
        SysUser user=userMapper.selectByUsername(name);
        if (user==null){
            throw new BadCredentialsException("usb");
        }
        //Java Web编程中有Servlet对象,抽象对象，记录web应用中和请求以及响应相关的上下文对象，servlet可以表示所有应用层的链接
        //ServletRequestAttributes和当前请求中的属性值对象，
        //RequestContextHolder是Spring容器内的请求对象，是经过Spring封装过的Request对象
        //HttpServletRequest是ServletRequest的子类，是和http相关的链接请求对象
        HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //通过http的请求对象获得浏览器传过来的密码
        String password=request.getParameter("password");
        //如果密码无效，则抛出异常
        if(password!=null && !"".equals(password) && !password.equals(user.getPassword())){
            throw new BadCredentialsException("usb");
        }
        //用户名和密码都匹配，登录成功，获取用户权限，包括用户的菜单权限和角色
        if(user!=null){
            //获取和用户对应的所有菜单
            List<Menu> menuList=menuMapper.selectByRid(user.getRid());
            //创建SpringSecurity的权限集合
            List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
            //将菜单说明和权限关联
            for (Menu menu:menuList){
                GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(menu.getIdentification());
                grantedAuthorities.add(grantedAuthority);
            }
            //创建一个SpringSecurity的用户描述对象，包含了用户名，用户密码的编码，用户权限
            User userDetail =new User(user.getUsername(), passwordEncoder.encode(user.getPassword()),grantedAuthorities);
            //返回自定义的安全用户的信息，包括用户id，用户的菜单权限，用户角色
            return new SecurityUser(userDetail,user.getUid(),menuList, user.getRid());
        }
        return null;
    }
}

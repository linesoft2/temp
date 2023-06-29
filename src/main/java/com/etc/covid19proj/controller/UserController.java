package com.etc.covid19proj.controller;

import com.etc.covid19proj.entity.Role;
import com.etc.covid19proj.entity.SysUser;
import com.etc.covid19proj.service.IMenuService;
import com.etc.covid19proj.service.IRoleService;
import com.etc.covid19proj.service.IUserService;
import com.etc.covid19proj.service.impl.MyUserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户相关所有操作控制器
 */
@Controller
public class UserController {
    //自动装配用户的业务逻辑，菜单业务逻辑，角色业务逻辑，验证业务逻辑
    @Resource
    private IUserService userService;
    @Resource
    private MyUserServiceImpl myUserService;
    @Resource
    private IMenuService menuService;
    @Resource
    private IRoleService roleService;
    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 登录
     * @return
     */
    @RequestMapping("/login.html")
    public String toLogin(){
        return "login";
    }

    /**
     * 用户列表展示
     * @param userParam 查询信息封装对象
     * @param currentPage 当前页
     * @return 用户列表
     */
    @RequestMapping("/userList")
    public ModelAndView userList(SysUser userParam,Integer currentPage){
        ModelAndView modelAndView=new ModelAndView("user/userList");
        if(currentPage==null){
            currentPage=1;
        }
        PageHelper.startPage(currentPage,10);
        List<SysUser> userList=userService.findByConditions(userParam);
        PageInfo<SysUser> pageInfo=new PageInfo<SysUser>(userList);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.addObject("searchUser",userParam);
        return modelAndView;
    }

    /**
     * 为添加用户界面提供所有可分配的角色信息
     * @return
     */
    @RequestMapping("/addUser")
    public ModelAndView toAddUser(){
        ModelAndView modelAndView=new ModelAndView("user/addUser");
        List<Role> roleList=roleService.findAll();
        modelAndView.addObject("roleList",roleList);
        return modelAndView;
    }

    /**
     * 保存用户信息
     * @param userParam
     * @return
     */
    @RequestMapping("saveUser")
    public ModelAndView saveUser(SysUser userParam){
        ModelAndView modelAndView=new ModelAndView("forward:userList");
        boolean flag=userService.addUser(userParam);
        if(!flag){
            modelAndView.setViewName("forward:addUser");
            modelAndView.addObject("msg","用户添加失败");
        }
        return modelAndView;
    }

    /**
     * 删除用户
     * @param uid
     * @return
     */
    @RequestMapping("/deleteUser")
    public ModelAndView toDeleteUser(Integer uid){
        ModelAndView modelAndView=new ModelAndView("forward:userList");
        userService.deleteUser(uid);
        return modelAndView;
    }

    /**
     * 获取待修改的用户信息和所有可分配的角色信息
     * @param uid
     * @return
     */
    @RequestMapping("/modifyUser")
    public ModelAndView toUpdateUser(Integer uid){
        ModelAndView modelAndView=new ModelAndView("user/updateUser");
        SysUser user=userService.findByUid(uid);
        List<Role> roleList=roleService.findAll();
        modelAndView.addObject("roleList",roleList);
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    /**
     * 更新用户
     * @param userParam
     * @return
     */
    @RequestMapping("updateUser")
    public ModelAndView updateUser(SysUser userParam){
        ModelAndView modelAndView=new ModelAndView("forward:userList");
        boolean flag=userService.updateUser(userParam);
        if(!flag){
            modelAndView.setViewName("forward:updateUser?uid="+userParam.getUid());
            modelAndView.addObject("msg","用户更新失败");
        }
        return modelAndView;
    }

    /**
     * 用户详情页
     * @param uid
     * @return
     */
    @RequestMapping("toUserDetail")
    public ModelAndView toUserDetail(Integer uid){
        ModelAndView modelAndView=new ModelAndView("user/detailUser");
        SysUser user=userService.findByUid(uid);
        modelAndView.addObject("user",user);
        return modelAndView;
    }
}

package com.etc.covid19proj.controller;

import com.etc.covid19proj.entity.Menu;
import com.etc.covid19proj.entity.Role;
import com.etc.covid19proj.entity.Tree;
import com.etc.covid19proj.service.IMenuService;
import com.etc.covid19proj.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * 角色操作控制器
 */
@Controller
public class RoleController {
    //自动装配角色服务和菜单服务
    @Resource
    private IRoleService roleService;
    @Resource
    private IMenuService menuService;

    /**
     * 角色列表
     * @param roleParam 角色查询条件对象
     * @param currentPage 当前页
     * @return 角色集合
     */
    @RequestMapping("roleList")
    public ModelAndView roleList(Role roleParam,Integer currentPage){
        ModelAndView modelAndView=new ModelAndView("role/roleList");
        if(currentPage==null) {
            currentPage = 1;
        }
        PageHelper.startPage(currentPage,10);
        List<Role> roleList=roleService.findByConditions(roleParam);
        PageInfo<Role> pageInfo=new PageInfo<>(roleList);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.addObject("searchRole",roleParam);
        return modelAndView;
    }

    /**
     * 转入添加角色页面
     * @return
     */
    @RequestMapping("/addRole")
    public ModelAndView toAddRole(){
        ModelAndView modelAndView=new ModelAndView("role/addRole");
        return modelAndView;
    }

    /**
     * 创建菜单树对象,最先完成的是第三级的菜单树，其次是第二级，最后是完整的菜单树
     * @param rid 角色id
     * @return 菜单树的json对象格式
     */
    @RequestMapping("/treeList")
    @ResponseBody
    public List<Tree> treeList(Integer rid){
        //菜单树对象
        List<Tree> treeList=new ArrayList<>();
        //角色拥有的菜单项集合
        List<Menu> roleMenuList=menuService.findByRid(rid);
        //菜单项是否被选中标记集合
        Map<Integer,Boolean> roleMenuMap=new HashMap<>();
        //获取所有角色拥有的菜单项并标记被选中
        if(rid!=null){
            Iterator<Menu> iterator=roleMenuList.iterator();
            while(iterator.hasNext()){
                Menu menu=iterator.next();
                //标记菜单被选中
                roleMenuMap.put(menu.getMid(), true);
            }
        }
        //获取所有菜单项
        List<Menu> menuList=menuService.findAll();
        //菜单树的节点
        Tree treeNode=null;
        //对所有菜单项遍历，将菜单对象转换为菜单树节点对象
        for (Menu menu:menuList){
            //判断是否是一级菜单
            if (menu.getLevel()==1){
                treeNode=new Tree();
                treeNode.setId(menu.getMid());
                treeNode.setName(menu.getMname());
                //当前遍历的菜单项属于角色权限
                if(roleMenuMap.get(menu.getMid()) != null){
                    treeNode.setChecked(true);
                }else{
                    treeNode.setChecked(false);
                }
                //递归子菜单树
                setChlid(treeNode,menuList,roleMenuMap);
                //向菜单树中加入菜单节点对象
                treeList.add(treeNode);
            }
        }
        return treeList;
    }

    /**
     * 对子菜单递归
     * @param treeNode 上级菜单树的节点
     * @param menuList 菜单项集合
     * @param roleMenuMap 角色拥有的菜单项集合
     */
    private void setChlid(Tree treeNode, List<Menu> menuList, Map<Integer, Boolean> roleMenuMap) {
        //子菜单树
        List<Tree> childList=new ArrayList<>();
        //菜单树的子节点
        Tree childTree=null;
        //遍历菜单项，创建子菜单树节点，向菜单树添加节点
        for (Menu menu:menuList){
            //判断是否是子菜单项
            if (menu.getLevel()>1){
                //判断当前遍历的菜单项是否是菜单树节点的子项，如果是则将菜单项转换为菜单树节点
                if (menu.getPmid()==treeNode.getId()){
                    childTree=new Tree();
                    childTree.setId(menu.getMid());
                    childTree.setName(menu.getMname());
                    if(roleMenuMap.get(menu.getMid()) != null){
                        childTree.setChecked(true);
                    }else{
                        childTree.setChecked(false);
                    }
                    setChlid(childTree,menuList,roleMenuMap);
                    //向子菜单树中加入菜单树节点
                    childList.add(childTree);
                }
            }
        }
        //是否有子菜单树
        if (childList.size()==0){
            return;
        }
        //将子菜单加入到父级菜单中
        treeNode.setChildren(childList);
    }

    /**
     * 保存角色
     * @param roleParam
     * @param menuIds
     * @return
     */
    @RequestMapping("saveRole")
    public ModelAndView saveRole(Role roleParam,String menuIds){
        ModelAndView modelAndView=new ModelAndView("forward:roleList");
        roleService.addRole(roleParam,menuIds);
        return modelAndView;
    }

    /**
     * 获取待修改的角色信息
     * @param rid
     * @return
     */
    @RequestMapping("modifyRole")
    public ModelAndView modifyRole(Integer rid){
        ModelAndView modelAndView=new ModelAndView("role/updateRole");
        Role roleParam=new Role();
        roleParam.setRid(rid);
        Role role=roleService.findByConditions(roleParam).get(0);
        modelAndView.addObject("role",role);
        return modelAndView;
    }

    /**
     * 更新角色
     * @param roleParam
     * @param menuIds
     * @return
     */
    @RequestMapping("updateRole")
    public ModelAndView updateRole(Role roleParam,String menuIds){
        ModelAndView modelAndView=new ModelAndView("forward:roleList");
        roleService.updateRole(roleParam,menuIds);
        return modelAndView;
    }

    /**
     * 角色详情
     * @param rid
     * @return
     */
    @RequestMapping("toRoleDetail")
    public ModelAndView toRoleDetail(Integer rid){
        ModelAndView modelAndView=new ModelAndView("role/detailRole");
        Role roleParam=new Role();
        roleParam.setRid(rid);
        Role role=roleService.findByConditions(roleParam).get(0);
        modelAndView.addObject("role",role);
        return modelAndView;
    }

    /**
     * 删除角色
     * @param rid
     * @return
     */
    @RequestMapping("deleteRole")
    public ModelAndView toDeleteRole(Integer rid){
        ModelAndView modelAndView=new ModelAndView("forward:roleList");
        roleService.deleteRole(rid);
        return modelAndView;
    }

    /**
     * 批量删除角色
     * @param rids
     * @return
     */
    @RequestMapping("deleteRoles")
    public ModelAndView toDeleteRoles(String rids){
        ModelAndView modelAndView=new ModelAndView("forward:roleList");
        roleService.deleteRoles(rids);
        return modelAndView;
    }
}

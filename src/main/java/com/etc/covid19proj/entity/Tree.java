package com.etc.covid19proj.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 树形结构，封装了用户享有的菜单权限
 */
@Data
@NoArgsConstructor
public class Tree {
    //菜单编号
    private Integer id;
    //父级菜单编号
    private Integer pid;
    //菜单名称
    private String name;
    //菜单是否被选中，选中是true，未被选中false
    private boolean checked;
    //父级菜单是否展开，默认是展开状态true
    private boolean open=true;
    //子菜单列表
    private List<Tree> children;
}

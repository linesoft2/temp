package com.etc.covid19proj.service.impl;

import com.etc.covid19proj.entity.Menu;
import com.etc.covid19proj.mapper.MenuMapper;
import com.etc.covid19proj.service.IMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单业务逻辑实现类
 */
@Service
@Transactional
public class MenuServiceImpl implements IMenuService {
    //自动装配Mapper对象
    @Resource
    private MenuMapper menuMapper;

    /**
     * 根据角色id查找菜单业务逻辑实现
     * @param rid
     * @return
     */
    @Override
    public List<Menu> findByRid(Integer rid) {
        return menuMapper.selectByRid(rid);
    }

    /**
     * 查找所有菜单业务逻辑实现
     * @return
     */
    @Override
    public List<Menu> findAll() {
        return menuMapper.selectAll();
    }
}

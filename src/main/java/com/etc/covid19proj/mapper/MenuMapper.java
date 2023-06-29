package com.etc.covid19proj.mapper;

import com.etc.covid19proj.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface MenuMapper {

    /**
     *
     * @return 所有菜单信息
     */
    @Select("select mid,mname,murl,pmid,level,sort,identification from t_menu")
    public List<Menu> selectAll();

    /**
     * 查询当前角色的菜单权限
     * @param rid 角色id
     * @return 与角色对应的所有菜单信息
     */
    @Select("select m.mid,m.mname,m.murl,m.pmid,m.level,m.sort,m.identification from t_menu m," +
            "t_r_m rm where m.mid=rm.mid and rm.rid=#{rid}")
    public List<Menu> selectByRid(Integer rid);
}

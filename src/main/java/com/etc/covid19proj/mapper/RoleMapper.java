package com.etc.covid19proj.mapper;

import com.etc.covid19proj.entity.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 对角色信息的相关操作，包括增删改查（CRUD）
 * 操作的对象都是数据库role表进行ORM映射的Role对象
 */
@Mapper
@Repository
public interface RoleMapper {

    /**
     * 通过id查询角色
     * @param rid 角色id
     * @return 角色对象
     */
    @Select("select rid,rname,rdesc from t_role where rid=#{rid}")
    public Role selectByRid(Integer rid);

    /**
     * 查询所有角色
     * @return 所有的角色
     */
    @Select("select rid,rname,rdesc from t_role")
    public List<Role> selectAll();

    /**
     * 根据条件查询角色，如果按照给定的条件，需要rid和rname能够匹配
     * @param roleParam 角色参数
     * @return 返回符合条件的多个角色
     */
    @Select("<script>" +
            "select rid,rname,rdesc from t_role where 1=1 "+
            "<if test='rid != null'> and rid=#{rid}" +
            "</if>"+"<if test='rname!=null'> and rname like concat('%',#{rname},'%')" +
            "</if>"+
            "</script>")
    public List<Role> selectByConditions(Role roleParam);

    /**
     * 向数据库中插入一个角色记录
     * @Options附加选项注解，useGeneratedKeys数据库产生值，keyProperty对应的属性字段
     * @param roleParam 待插入的role
     */
    @Insert("insert into t_role(rname,rdesc) values(#{rname},#{rdesc})")
    @Options(useGeneratedKeys = true,keyProperty = "rid")
    public void inserRole(Role roleParam);

    /**
     * 更新一条角色记录
     * @param roleParam 角色参数
     */
    @Update("update t_role set rname=#{rname},rdesc=#{rdesc} where rid=#{rid}")
    public void updateRole(Role roleParam);

    /**
     * 添加一条角色和菜单的映射关系
     * @param rid 角色id
     * @param mid 菜单id
     */
    @Insert("insert into t_r_m(rid,mid) values(#{rid},#{mid})")
    public void insertRoleMenu(@Param("rid") Integer rid,@Param("mid") Integer mid);

    /**
     * 删除角色
     * @param rid 角色Id
     */
    @Delete("delete from t_role where rid=#{rid}")
    public void deleteRole(Integer rid);

    /**
     * 删除和角色对应的菜单信息
     * @param rid
     */
    @Delete("delete from t_r_m where rid=#{rid}")
    public void deleteRoleMenu(Integer rid);
}

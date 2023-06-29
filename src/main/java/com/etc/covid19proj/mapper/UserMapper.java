package com.etc.covid19proj.mapper;

import com.etc.covid19proj.entity.SysUser;
import net.sf.jsqlparser.schema.Column;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 对用户信息的相关操作，包括增删改查（CRUD）
 * 操作的对象都是数据库user表进行ORM映射的SysUser对象
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 根据指定的用户查询用户信息
     * @param username 待查询的用户名
     * @return 完整的用户信息
     */
    @Select("select uid,username,password,rid from t_user where username=#{username}")
    public SysUser selectByUsername(String username);

    /**
     * 查询用户和角色的一一对应关系
     * @Results声明结果集的数据组成
     * @Result结果集中每个数据分量的映射关系，column表示表中的列名，property表示类中的属性名
     * @One表示对象的一一映射关系，在此处表示一个用户只对应一个角色；select表示哪个类中哪个方法获取和当前用户对应的角色对象
     * @param uid 用户id
     * @return 返回一个带角色对象的用户对象
     */
    @Results({@Result(column="uid",property = "uid"),
    @Result(column = "username",property = "username"),
    @Result(column = "password",property = "password"),
    @Result(column = "rid",property = "rid"),
    @Result(column = "rid",property = "role",
            one = @One(select = "com.etc.covid19proj.mapper.RoleMapper.selectByRid"))})
    @Select("select uid,username,password,rid from t_user where uid=#{uid}")
    public SysUser selectByUid(Integer uid);

    /**
     * 根据用户名进行模式匹配查询，模糊查询
     * <script> mybatis的sql脚本标签
     * <if test='username!=null'> mybatis的sql脚本中的分支标签，test判断表达式
     * 在if标签内使用and链接后续查询条件，避免1=1覆盖后面所有的查询
     * @param userParam 用户对象
     * @return 用户和角色的对象
     */
    @Results({@Result(column="uid",property = "uid"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "rid",property = "rid"),
            @Result(column = "rid",property = "role",
                    one = @One(select = "com.etc.covid19proj.mapper.RoleMapper.selectByRid"))})
    @Select("<script>" +
            "select uid,username,password,rid from t_user where 1=1 "+
            "<if test='username!=null'> and username like concat('%',#{username},'%')" +
            "</if>"+
            "</script>")
    public List<SysUser> selectByConditions(SysUser userParam);

    /**
     * 新增一条用户记录
     * @param userParam
     */
    @Insert("insert into t_user(username,password,rid) values(#{username},#{password},#{rid})")
    public void insertUser(SysUser userParam);

    /**
     * 更新一条用户记录
     * @param userParam
     */
    @Update("update t_user set username=#{username},password=#{password},rid=#{rid} where uid=#{uid}")
    public void updateUser(SysUser userParam);

    /**
     * 删除一条记录
     * @param uid 用户id
     */
    @Delete("delete from t_user where uid=#{uid}")
    public void deleteUser(Integer uid);

    /**
     * 删除指定角色下的用户
     * @param rid 角色id
     */
    @Delete("delete from t_user where rid=#{rid}")
    public void deleteUserByRid(Integer rid);

    /**
     * 获取对应角色的用户
     * @param rid
     * @return
     */
    @Select("select uid,username,password,rid from t_user where rid=#{rid}")
    public SysUser selectUserByRid(Integer rid);
}

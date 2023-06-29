package com.etc.covid19proj.mapper;

import com.etc.covid19proj.entity.Wz;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WzMapper {

    @Select("<script>" +
            "select * from t_covid19_wz where 1=1 "+
            "<if test='name!=null'> and name " +
            "like concat('%',#{name},'%')</if>"+
            "</script>")
    public List<Wz> selectByConditions(Wz wzParam);

    @Select("select * from t_covid19_wz where name=#{name}")
    public Wz selectByName(String name);

    @Insert("insert into t_covid19_wz values(#{name},#{cg},#{xb},#{jz},#{xh},#{xq},#{kc})")
    public void insertWz(Wz wzParam);

    @Update("update t_covid19_wz set name=#{name},cg=#{cg},xb=#{xb},jz=#{jz}," +
            "xh=#{xh},xq=#{xq},kc=#{kc} where name=#{srcName}")
    public void updateWz(Wz wzParam);
}

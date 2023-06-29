package com.etc.covid19proj.mapper;

import com.etc.covid19proj.entity.Overseasinput;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OverseasinputMapper {
    @Select("<script>" +
            "select * from t_covid19_overseasinput where 1=1"+
            "<if test='provinceShortName!=null'> and provinceShortName like " +
            "concat('%',#{provinceShortName},'%')</if>"+
            " <if test='datetime!=null'> and datetime=#{datetime}</if>"+
            "</script>")
    public List<Overseasinput> selectOverseasinputByConditions(Overseasinput overseasinputParam);

    @Select("select distinct concat(pid,',', provinceShortName) from t_covid19_overseasinput")
    public List<String> selectDistinctProvinceName();

    @Select("select * from t_covid19_overseasinput where pid=#{pid} and datetime=#{datetime}")
    public Overseasinput selectByPidAndDatetime(@Param("pid") Integer pid,@Param("datetime") String datetime);

    @Insert("insert into t_covid19_overseasinput values(#{datetime},#{provinceShortName}" +
            ",#{pid},#{confirmedCount})")
    public void insertOverseasinput(Overseasinput overseasinputParam);

    @Update("update t_covid19_overseasinput set confirmedCount=#{confirmedCount} " +
            "where datetime=#{datetime} and pid=#{pid}")
    public void updateOverseasinput(Overseasinput overseasinputParam);
}

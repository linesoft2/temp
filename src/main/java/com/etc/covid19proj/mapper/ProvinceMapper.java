package com.etc.covid19proj.mapper;

import com.etc.covid19proj.entity.Province;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 当日省份疫情数据mapper对象
 */

@Mapper
@Repository
public interface ProvinceMapper {
    /**
     * 条件查询，查询符合条件的所有的身份记录
     * @param provinceParam
     * @return
     */
    @Select("<script>" +
            "select * from t_covid19_provinces where 1=1 "+
            "<if test='provinceShortName!=null'> and provinceShortName " +
            "like concat('%',#{provinceShortName},'%')</if>"+
            "<if test='datetime!=null'> and datetime=#{datetime}</if>"+
            "</script>")
    public List<Province> selectProvinceByConditions(Province provinceParam);

    /**
     * 查询省份名称和位置id，为添加和更新提供省份信息
     * @return
     */
    @Select("select distinct concat(locationId,',',provinceShortName) from t_covid19_provinces")
    public List<String> selectDistinctProvinceNames();

    /**
     * 根据主键查询
     * @param locationId
     * @param datetime
     * @return
     */
    @Select("select * from t_covid19_provinces where locationId=#{locationId} and datetime=#{datetime}")
    public Province selectByLocationIdAndDateTime(@Param("locationId") Integer locationId,
                                                  @Param("datetime") String datetime);

    /**
     * 插入疫情信息
     * @param provinceParam
     */
    @Insert("insert into t_covid19_provinces values(#{datetime},#{locationId},#{provinceShortName}," +
            "#{cityName},#{currentConfirmedCount},#{confirmedCount},#{suspectedCount}," +
            "#{curedCount},#{deadCount},0)")
    public void insertProvince(Province provinceParam);

    /**
     * 更新当日疫情信息
     * @param provinceParam
     */
    @Update("update t_covid19_provinces set currentConfirmedCount=#{currentConfirmedCount}," +
            "confirmedCount=#{confirmedCount},suspectedCount=#{suspectedCount},curedCount=#{curedCount}," +
            "deadCount=#{deadCount} where datetime=#{datetime} and locationId=#{locationId}")
    public void updateProvince(Province provinceParam);
}

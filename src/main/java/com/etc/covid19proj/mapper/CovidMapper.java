package com.etc.covid19proj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CovidMapper {

    /**
     * 全国省份疫情累计确诊数据
     * @param datetime
     * @return
     */
    @Select("select provinceShortName as name,confirmedCount as value " +
            "from t_covid19_provinces where datetime=#{datetime}")
    public List<Map<String,Object>> getNationalData(String datetime);

    /**
     * 查询全国每天的疫情数据
     * @return
     */
    @Select("select dateId,confirmedIncr as '新增确诊',confirmedCount as '累计确诊',suspectedCount as " +
            "'疑似病例', curedCount as '累计治愈', deadCount as '累计死亡' from t_covid19_internaldays")
    public List<Map<String,Object>> getCovidTimeData();

    /**
     * 查询全国各省份境外输入病例前十
     * @param datetime
     * @return
     */
    @Select("select provinceShortName as `name`,confirmedCount as `value` from t_covid19_overseasinput " +
            "where datetime=#{datetime} order by `value` desc limit 10")
    public List<Map<String,Object>> getCovidImportData(String datetime);

    /**
     * 查询防疫物资使用情况
     * @return
     */
    @Select("select name,`cg` as `采购`,`xh` as `下拨`,`jz` as `捐赠`,`xh` as `消耗` ," +
            "`xq` as `需求` ,`kc` as `库存` from t_covid19_wz")
    public List<Map<String,Object>> getCovidWz();
}

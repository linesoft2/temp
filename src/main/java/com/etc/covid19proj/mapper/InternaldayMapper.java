package com.etc.covid19proj.mapper;

import com.etc.covid19proj.entity.Internalday;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 疫情历史数据Mapper接口
 */
@Mapper
@Repository
public interface InternaldayMapper {

    /**
     * 根据起止日期查询历史疫情数据
     * @param internaldayParam
     * @return
     */
    @Select("<script>" +
            "select * from t_covid19_internaldays where 1=1"+
            "<if test='startDate!=null'> and dateId &gt;= #{startDate}</if>"+
            "<if test='endDate!=null'> and dateId &lt;= #{endDate}</if>"+
            "order by dateId"+
            "</script>")
    public List<Internalday> selectByConditions(Internalday internaldayParam);

    @Select("select * from t_covid19_internaldays where dateId=#{dateId}")
    public Internalday selectByDateId(String dateId);
}

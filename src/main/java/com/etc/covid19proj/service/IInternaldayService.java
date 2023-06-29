package com.etc.covid19proj.service;

import com.etc.covid19proj.entity.Internalday;

import java.util.List;

public interface IInternaldayService {

    /**
     * 查找满足过滤条件的历史疫情数据
     * @param internaldayParam
     * @return
     */
    public List<Internalday> findByConditions(Internalday internaldayParam);

    /**
     * 查找指定日期的疫情历史数据
     * @param dateId
     * @return
     */
    public Internalday findByDateId(String dateId);
}

package com.etc.covid19proj.service;

import com.etc.covid19proj.entity.Province;

import java.util.List;

public interface IProvinceService {

    public List<Province> findProvinceByConditions(Province provinceParam);

    public List<String> findDistinctProvinceName();

    public boolean addProvince(Province provinceParam);

    public Province findByLocationIdAndDatetime(Integer locationId,String datetime);

    public boolean updateProvince(Province provinceParam);
}

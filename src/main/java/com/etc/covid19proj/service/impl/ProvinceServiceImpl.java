package com.etc.covid19proj.service.impl;

import com.etc.covid19proj.entity.Province;
import com.etc.covid19proj.mapper.ProvinceMapper;
import com.etc.covid19proj.service.IProvinceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 当日省份疫情数据业务实现类
 */
@Service
@Transactional
public class ProvinceServiceImpl implements IProvinceService {

    @Resource
    private ProvinceMapper provinceMapper;
    @Override
    public List<Province> findProvinceByConditions(Province provinceParam) {
        return provinceMapper.selectProvinceByConditions(provinceParam);
    }

    @Override
    public List<String> findDistinctProvinceName() {
        return provinceMapper.selectDistinctProvinceNames();
    }

    @Override
    public boolean addProvince(Province provinceParam) {
        String[] param = provinceParam.getProvinceName().split(",");
        provinceParam.setLocationId(
                Integer.parseInt(param[0]));
        provinceParam.setProvinceShortName(param[1]);
        Province province=provinceMapper.selectByLocationIdAndDateTime(
                provinceParam.getLocationId(),provinceParam.getDatetime()
        );
        if (province==null){
            provinceMapper.insertProvince(provinceParam);
            return true;
        }
        return false;
    }

    @Override
    public Province findByLocationIdAndDatetime(Integer locationId, String datetime) {
        return provinceMapper.selectByLocationIdAndDateTime(locationId,datetime);
    }

    @Override
    public boolean updateProvince(Province provinceParam) {
        Province province=provinceMapper.selectByLocationIdAndDateTime(
                provinceParam.getLocationId(),provinceParam.getDatetime()
        );
        if (province!=null){
            provinceMapper.updateProvince(provinceParam);
            return true;
        }
        return false;
    }
}

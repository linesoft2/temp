package com.etc.covid19proj.service.impl;

import com.etc.covid19proj.mapper.CovidMapper;
import com.etc.covid19proj.service.ICovidService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CovidService implements ICovidService {
    @Resource
    private CovidMapper covidMapper;
    @Override
    public List<Map<String, Object>> getNationalMapData(String datetime) {
        return covidMapper.getNationalData(datetime);
    }

    @Override
    public List<Map<String, Object>> getCovidTimeData() {
        return covidMapper.getCovidTimeData();
    }

    @Override
    public List<Map<String, Object>> getCovidImportData(String datetime) {
        return covidMapper.getCovidImportData(datetime);
    }

    @Override
    public List<Map<String, Object>> getCovidWz() {
        return covidMapper.getCovidWz();
    }
}

package com.etc.covid19proj.service;

import java.util.List;
import java.util.Map;

public interface ICovidService {
    public List<Map<String,Object>> getNationalMapData(String datetime);

    public List<Map<String,Object>> getCovidTimeData();

    public List<Map<String,Object>> getCovidImportData(String datetime);

    public List<Map<String,Object>> getCovidWz();
}

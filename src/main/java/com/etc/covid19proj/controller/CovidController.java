package com.etc.covid19proj.controller;

import com.etc.covid19proj.bean.Result;
import com.etc.covid19proj.service.ICovidService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 数据可视化控制器
 * 控制器获取的数据以json形式返回到前台页面
 */
@RestController //返回json数据
@RequestMapping("covid") //在类上使用表示所有控制器方法地址的上层地址
@CrossOrigin(origins = "*",maxAge = 3600)
public class CovidController {

    @Resource
    private ICovidService covidService;

    /**
     * 查询全国省份累计确诊数据，地图显示
     * @return 返回的类型是Result对象，Result对象封装了响应状态码，响应状态消息以及响应数据
     */
    @RequestMapping("getNationalMapData") //实际访问地址covid/getNationalMapData
    public  Result getNationalMapData(){
        String datetime="2021-12-24";
        List<Map<String,Object>> data=covidService.getNationalMapData(datetime);
        return Result.success(data);
    }

    /**
     * 查询全国历史疫情数据，折线图显示
     * @return
     */
    @RequestMapping("getCovidTimeData")
    public Result getCovidTimedata(){
        List<Map<String,Object>> data=covidService.getCovidTimeData();
        return Result.success(data);
    }


    /**
     * 查询全国各省份境外输入病例前十，饼状图显示
     * @return
     */
    @RequestMapping("getCovidImportData")
    public Result getCovidImportData(){
        String datetime="2021-12-24";
        List<Map<String,Object>> data=covidService.getCovidImportData(datetime);
        return Result.success(data);
    }

    /**
     * 查询物资使用情况，柱状图显示
     * @return
     */
    @RequestMapping("getCovidWz")
    public  Result getCovidWz(){
        List<Map<String,Object>> data=covidService.getCovidWz();
        return Result.success(data);
    }
}

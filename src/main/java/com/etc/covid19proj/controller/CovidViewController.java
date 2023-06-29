package com.etc.covid19proj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 视图跳转控制器，点击左侧边栏的菜单跳转到获取数据展示动图的控制器中
 */
@Controller
public class CovidViewController {
    @RequestMapping("/nationalMapData.html")
    public String toNationalMapData(){
        return "view/nationalMapData";
    }

    @RequestMapping("/covidTimeData.html")
    public String toCovidTimeData(){
        return "view/covidTimeData";
    }

    @RequestMapping("/covidImportData.html")
    public String toCovidImportData(){
        return "view/covidImportData";
    }

    @RequestMapping("/covidWz.html")
    public String toCovidWz(){
        return "view/covidWz";
    }
}

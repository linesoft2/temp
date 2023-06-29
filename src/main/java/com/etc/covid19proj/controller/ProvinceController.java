package com.etc.covid19proj.controller;

import com.etc.covid19proj.entity.Province;
import com.etc.covid19proj.service.IProvinceService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ProvinceController {
    @Resource
    private IProvinceService provinceService;

    @RequestMapping("provinceList")
    public ModelAndView provinceList(Province provinceParam){
        ModelAndView modelAndView=new ModelAndView("province/provinceList");
        if(provinceParam.getDatetime()==null || "".equals(provinceParam.getDatetime())) {
//            Date date=new Date();
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//            provinceParam.setDatetime(sdf.format(date));
            provinceParam.setDatetime("2021-12-24");
        }
        List<Province> provinceList=provinceService.findProvinceByConditions(provinceParam);
        modelAndView.addObject("provinceList",provinceList);
        modelAndView.addObject("searchProvince",provinceParam);
        return modelAndView;
    }

    @RequestMapping("toProvinceDetail")
    public ModelAndView toProvinceDetail(Integer locationId,String datetime){
        ModelAndView modelAndView=new ModelAndView("province/detailProvince");
        Province province=provinceService.findByLocationIdAndDatetime(locationId,datetime);
        modelAndView.addObject(province);
        return modelAndView;
    }

    @RequestMapping("modifyProvince")
    public ModelAndView toModifyProvince(Integer locationId,String datetime){
        ModelAndView modelAndView=new ModelAndView("province/updateProvince");
        Province province=provinceService.findByLocationIdAndDatetime(locationId,datetime);
        List<String> provinceNames=provinceService.findDistinctProvinceName();
        modelAndView.addObject("province",province);
        modelAndView.addObject("provinceNames",provinceNames);
        return modelAndView;
    }

    @RequestMapping("updateProvince")
    public ModelAndView updateProvince(Province provinceParam){
        ModelAndView modelAndView=new ModelAndView("forward:provinceList");
        provinceService.updateProvince(provinceParam);
        return modelAndView;
    }

    @RequestMapping("addProvince")
    public ModelAndView toAddProvince(){
        ModelAndView modelAndView=new ModelAndView("province/addProvince");
        List<String> provinceNames=provinceService.findDistinctProvinceName();
        modelAndView.addObject("provinceNames",provinceNames);
        return modelAndView;
    }

    @RequestMapping("saveProvince")
    public ModelAndView saveProvince(Province provinceParam){
        ModelAndView modelAndView=new ModelAndView("forward:provinceList");
        boolean flag=provinceService.addProvince(provinceParam);
        if (!flag){
            modelAndView.setViewName("forward:addProvince");
            modelAndView.addObject("msg","添加失败");
        }
        return modelAndView;
    }
}

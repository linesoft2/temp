package com.etc.covid19proj.controller;

import com.etc.covid19proj.entity.Wz;
import com.etc.covid19proj.service.IWzService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class WzController {
    @Resource
    private IWzService wzService;

    @RequestMapping("wzList")
    public ModelAndView wzList(Wz wzParam,Integer current){
        ModelAndView modelAndView=new ModelAndView("wz/wzList");
        List<Wz> wzList=wzService.findByConditions(wzParam);
        if (current==null){
            current=1;
        }
        PageHelper.startPage(current,10);
        PageInfo<Wz> pageInfo=new PageInfo(wzList);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.addObject("searchWz",wzParam);
        return modelAndView;
    }

    @RequestMapping("toWzDetail")
    public ModelAndView toWzDetail(String name){
        ModelAndView modelAndView=new ModelAndView("wz/detailWz");
        Wz wz=wzService.findByName(name);
        modelAndView.addObject("wz",wz);
        return modelAndView;
    }

    @RequestMapping("addWz")
    public ModelAndView toAddWz(){
        ModelAndView modelAndView=new ModelAndView("wz/addWz");
        return modelAndView;
    }

    @RequestMapping("saveWz")
    public ModelAndView saveWz(Wz wzParam){
        ModelAndView modelAndView=new ModelAndView("forward:wzList");
        if(!wzService.addWz(wzParam)){
            modelAndView.setViewName("forward:addWz");
            modelAndView.addObject("msg","failure");
        }
        return modelAndView;
    }

    @RequestMapping("modifyWz")
    public ModelAndView toUpdateWz(String name,Integer isUpdateError){
        ModelAndView modelAndView=new ModelAndView("wz/updateWz");
        Wz wz=wzService.findByName(name);
        modelAndView.addObject("wz",wz);
        if (isUpdateError!=null){
            modelAndView.addObject("msg","failure");
        }
        return modelAndView;
    }
    @RequestMapping("updateWz")
    public ModelAndView updateWz(Wz wzParam){
        ModelAndView modelAndView=new ModelAndView("forward:wzList");
        if(!wzService.updateWz(wzParam)){
            modelAndView.setViewName("redirect:modifyWz?name=" +wzParam.getName()+
                    "&isUpdateError=1");
        }
        return modelAndView;
    }
}

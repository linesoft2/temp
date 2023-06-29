package com.etc.covid19proj.controller;

import com.etc.covid19proj.entity.Overseasinput;
import com.etc.covid19proj.mapper.OverseasinputMapper;
import com.etc.covid19proj.service.impl.OverseasinputService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class OverseasinputController {
    @Resource
    private OverseasinputService overseasinputService;

    @RequestMapping("overseasinputList")
    public ModelAndView overseaseinputList(Overseasinput overseasinputParam){
        ModelAndView modelAndView=new ModelAndView("overseasinput/overseasinputList");
        if(overseasinputParam.getDatetime()==null || "".equals(overseasinputParam.getDatetime())){
            overseasinputParam.setDatetime("2021-12-24");
        }
        List<Overseasinput> overseasinputList=
                overseasinputService.findOverseasinputByConditions(overseasinputParam);
        modelAndView.addObject("overseasinputList",overseasinputList);
        modelAndView.addObject("searchOverseasinput",overseasinputParam);
        return modelAndView;
    }

    @RequestMapping("addOverseasinput")
    public ModelAndView toAddOverseasinput(){
        ModelAndView modelAndView=new ModelAndView("overseasinput/addOverseasinput");
        List<String> provinceNames=overseasinputService.findDistinctProvinceName();
        modelAndView.addObject("provinceNames",provinceNames);
        return modelAndView;
    }

    @RequestMapping("saveOverseasinput")
    public ModelAndView saveOverseasinput(Overseasinput overseasinputParam){
        ModelAndView modelAndView=new ModelAndView("forward:overseasinputList");
        if (!overseasinputService.addOverseasinput(overseasinputParam)){
            modelAndView.setViewName("forward:addOverseasinput");
            modelAndView.addObject("msg","****");
        }
        return modelAndView;
    }

    @RequestMapping("modifyOverseasinput")
    public ModelAndView modifyOverseasinput(Integer pid,String datetime){
        ModelAndView modelAndView=new ModelAndView("overseasinput/updateOverseasinput");
        Overseasinput overseasinput=overseasinputService.findByPidAndDatetime(pid,datetime);
        modelAndView.addObject("overseasinput",overseasinput);
        List<String> provincenames=overseasinputService.findDistinctProvinceName();
        modelAndView.addObject("provinceNames",provincenames);
        return modelAndView;
    }

    @RequestMapping("updateOverseasinput")
    public ModelAndView updateOverseasinput(Overseasinput overseasinputParam){
        ModelAndView modelAndView = new ModelAndView("forward:overseasinputList");
        overseasinputService.updateOverseasinput(overseasinputParam);
        return modelAndView;
    }

    @RequestMapping("toOverseasinputDetail")
    public ModelAndView toOverseasinputDetail(Integer pid,String datetime){
        ModelAndView modelAndView=new ModelAndView("overseasinput/detailOverseasinput");
        Overseasinput overseasinput=overseasinputService.findByPidAndDatetime(pid,datetime);
        modelAndView.addObject("overseasinput",overseasinput);
        return modelAndView;
    }
}

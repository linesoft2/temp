package com.etc.covid19proj.controller;

import com.etc.covid19proj.entity.Internalday;
import com.etc.covid19proj.service.IInternaldayService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 历史数据信息控制器
 */
@Controller
public class InternaldayController {
    @Resource
    private IInternaldayService internaldayService;

    /**
     * 历史疫情信息列表，分页，根据日期起止查询
     * @param internaldayParam
     * @param currentPage
     * @return
     */
    @RequestMapping("/internaldayList")
    public ModelAndView internaldayList(Internalday internaldayParam,Integer currentPage){
        ModelAndView modelAndView=new ModelAndView("internalday/internaldayList");
        if(currentPage==null)
            currentPage=1;
        if(internaldayParam!=null && "".equals(internaldayParam.getStartDate())){
            internaldayParam.setStartDate(null);
        }
        if (internaldayParam!=null && "".equals(internaldayParam.getEndDate())){
            internaldayParam.setEndDate(null);
        }
        PageHelper.startPage(currentPage,10);
        List<Internalday> internaldayList=internaldayService.findByConditions(internaldayParam);
        PageInfo<Internalday> pageInfo=new PageInfo<>(internaldayList);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.addObject("searchInternalday",internaldayParam);
        return modelAndView;
    }

    /**
     * 疫情信息的详情页
     * @param dateId
     * @return
     */
    @RequestMapping("toInternaldayDetail")
    public ModelAndView toInternaldayDetail(String dateId){
        ModelAndView modelAndView=new ModelAndView("internalday/detailInternalday");
        Internalday internalday=internaldayService.findByDateId(dateId);
        modelAndView.addObject("internalday",internalday);
        return modelAndView;
    }

}

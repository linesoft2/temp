package com.etc.covid19proj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 首页控制器
 */
@Controller
public class IndexController {

    /**
     * 加载页头
     * @return
     */
    @RequestMapping("/top.html")
    public String toTop(){
        return "top";
    }

    /**
     * 加载页脚
     * @return
     */
    @RequestMapping("/footer.html")
    public String toFooter(){
        return "footer";
    }

    /**
     * 加载左侧菜单
     * @return
     */
    @RequestMapping("/left.html")
    public String toLeft(){
        return "left";
    }

    /**
     * 加载欢迎信息
     * @return
     */
    @RequestMapping("/welcome.html")
    public String toWelcome(){
        return "welcome";
    }

    /**
     * 加载内容部分
     * @return
     */
    @RequestMapping("/main.html")
    public String toMain(){
        return "main";
    }
}

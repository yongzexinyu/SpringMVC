package com.xiexin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pages")
public class PagesController {
    @RequestMapping("/reg")
    public  String reg(){
        System.out.println("请求进入hello....");
        return "reg";//必须和xx.jsp一样
    }
    @RequestMapping("/regForm")
    public String regForm(){
        return  "regForm";
    }

    @RequestMapping("/login")
    public String login(){
        return  "login";
    }

    @RequestMapping("/loginForm")
    public String loginForm(){
        return  "loginForm";
    }

    @RequestMapping("/ajaxCommit")
    public String ajaxCommit(){
        return  "ajaxCommit";
    }
    @RequestMapping("/nongZuoWu")
    public  String  nongZuoWu(){
        return  "nongZuoWu";
    }
}

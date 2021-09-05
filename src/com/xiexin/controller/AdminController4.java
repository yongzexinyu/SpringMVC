package com.xiexin.controller;

import com.xiexin.bean.AdminInfo;
import com.xiexin.bean.Dog;
import com.xiexin.bean.Lover;
import com.xiexin.bean.NongZuoWu;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/admin")
public class AdminController4 {
    private ArrayList<String> adminAiHao;

    //第一种收取参数方式：数据类型接收参数
    @RequestMapping("/reg")//layui版本的
    @ResponseBody//这个注解就是 返回给前端的 json数据
    public  Map reg(String adminName, String adminPwd, String adminPwdR, String adminTime, String adminSex,@RequestParam("adminAiHao[]") ArrayList<String> adminAiHao, String adminCity, String adminOpen){
        this.adminAiHao = adminAiHao;
        System.out.println("adminTime = " + adminTime);
        System.out.println("adminSex = " + adminSex);
        System.out.println("adminAiHao = " + adminAiHao);
        System.out.println("adminCity = " + adminCity);
        System.out.println("adminOpen = " + adminOpen);
        Map codeMap=new HashMap<>();
        if(!adminPwd.equals(adminPwdR)){
            codeMap.put("code","4401");
            codeMap.put("msg","你输入的秘码和重复密码不一致，注册失败");

            return  codeMap;
        }
        if(adminName.length()<=0){
            codeMap.put("code","4202");
            codeMap.put("msg","adminName表单需要写完整");

            return  codeMap;
        }
        if(adminPwd.length()<=0){
            codeMap.put("code","4202");
            codeMap.put("msg","adminPwd表单需要写完整");

            return  codeMap;
        }

        codeMap.put("code",0);
        codeMap.put("msg","注册成功");

        return  codeMap;

    }
    //传统版本 不返回json 跳转使用转发或者重定向
    @RequestMapping("/regForm")
    public  String regForm(String adminName,String adminPwd){
        System.out.println("adminName = " + adminName);
        System.out.println("adminPwd = " + adminPwd);
        //注册成功跳转到登录页
        return "loginForm";
    }
    //SpringMVC
    @RequestMapping("/regByBean")
    @ResponseBody
    public  Map regByBean(AdminInfo adminInfo){
        System.out.println("adminInfo = " + adminInfo);
        Map codeMap=new HashMap();
        codeMap.put("code",0);
        codeMap.put("msg","注册成功");
        return  codeMap;
    }

    //ajax接收数据/集合
    @RequestMapping("/ajax03")
    @ResponseBody
    public Map ajax03(@RequestParam("ids[]") List<Integer> ids){
        //当前后端参数不一样的时候 那么就需要注解调整
        for (Integer id:ids) {
            System.out.println("id = " + id);
        }
        Map codeMap=new HashMap();
        codeMap.put("code",0);
        codeMap.put("msg","请求访问成功");
        codeMap.put("data",ids);
        return  codeMap;
    }
    @RequestMapping("/ajax04")
    @ResponseBody
    public Map ajax04(@RequestBody AdminInfo adminInfo){//注解就是值得前端用json请求
        System.out.println("adminInfo = " + adminInfo);
        Map codeMap=new HashMap();
        codeMap.put("code",0);
        codeMap.put("msg","请求访问成功");
        codeMap.put("data",adminInfo);
        return  codeMap;
    }
    //ajax05 接收前端传来的多个对象
    @RequestMapping("/ajax05")
    @ResponseBody
    public  Map ajax05(@ModelAttribute Lover lover, @ModelAttribute Dog dog){
        System.out.println("lover = " + lover);
        System.out.println("dog = " + dog);
        Map codeMap=new HashMap();
        codeMap.put("code",0);
        codeMap.put("msg","请求访问成功");
        codeMap.put("data1","lover");
        codeMap.put("data2","dog");
        return  codeMap;
    }
    //前端传来的多个对象 需要根据请求的前缀进行绑定
    @InitBinder("lover")
    public  void binding01(WebDataBinder webDataBinder){//WebDataBinder 网络数据绑定
        webDataBinder.setFieldDefaultPrefix("lover.");//设置前缀

    }
    @InitBinder("dog")
    public  void binding02(WebDataBinder webDataBinder){//WebDataBinder 网络数据绑定
        webDataBinder.setFieldDefaultPrefix("dog.");

    }
    //ajax06 json收取多个对象
    @RequestMapping("/ajax06")
    @ResponseBody           //@GetMapping 和 @RequestBody 同时使用等着被开除
    public  Map ajax06(@RequestBody List<Lover> loverList){//@RequestBody 他是方法体中 拿取数据的 所以不能用GET请求！！！
        for (Lover lover : loverList) {
            System.out.println("lover = " + lover);
        }
        Map codeMap=new HashMap();
        codeMap.put("code",0);
        codeMap.put("msg","请求访问成功");
        codeMap.put("data","loverList");
        return  codeMap;
    }
    //ajax07 Map传参
    @RequestMapping("/ajax07")
    @ResponseBody       //十分常用 还记得我们Servlet 夺标的动态参数，就是用map  07能搞定一切
    public  Map ajax07(@RequestBody Map map){
        System.out.println("map的adminName = " + map.get("adminName"));
        Map codeMap=new HashMap();
        codeMap.put("code",0);
        codeMap.put("msg","请求访问成功");
        codeMap.put("data","map");
        return  codeMap;
    }
    //ajax08 Map传参
    @RequestMapping("/ajax08")
    @ResponseBody
    public  Map ajax08(Lover lover,@RequestParam( value = "limit" ,required = false,defaultValue = "5")Integer pageSize,Integer page){
        System.out.println("lover = " + lover);
        System.out.println("page = " + page);
        System.out.println("pageSize = " + pageSize);

        return  null;
    }
    @RequestMapping("/nongZuoWu")
    @ResponseBody
    public  Map  nongZuoWu(@RequestBody NongZuoWu nongZuoWu){
        System.out.println("nongZuoWu = " + nongZuoWu);
        Map codeMap=new HashMap();
        codeMap.put("code",0);
        codeMap.put("msg","购买成功");
        codeMap.put("data",nongZuoWu);
        return  codeMap;
    }
    //第一种的springmvc的传值方式！！  原始方式:request + session + request 的转发
    //传统的mvc方法（不返回json数据，不使用 @ResponseBody） 他要跳转 jsp  跳转jsp的方式1 返回值是String
    //页面传值： 即 四大作用域 request session application page
    @RequestMapping("/yuansheng")//什么是页面传值  登录页
    public String yuansheng(AdminInfo adminInfo, HttpSession session){
        //   public String yuansheng(HttpSession session HttpServletRequest request){
        System.out.println("原生方式 页面传值");
        System.out.println("adminInfo = " + adminInfo);
        //登录如果验证成功 就需要把 登录信息 放入到session域中
        session.setAttribute("adminInfo",adminInfo);
        return "home";
    }
    //第二种springmvc的传值方式
    @RequestMapping("/modelAndView")
    public ModelAndView  modelAndView(AdminInfo adminInfo){
        //model 和 view 同俗 数据和显示 modelAndView 可以代替转发功能
        ModelAndView mv=new ModelAndView();
        mv.addObject("adminName",adminInfo.getAdminName());
        mv.addObject("adminPwd",adminInfo.getAdminPwd());
        System.out.println("以上是model的绑定，即数据的绑定");
        mv.setViewName("home");
        return  mv;
    }
    //第三种springmvcde 传值方式 ！！ model
    @RequestMapping("/model")
    public String  model(AdminInfo adminInfo, Model model){
        model.addAttribute("adminName",adminInfo.getAdminName());
        model.addAttribute("adminPwd",adminInfo.getAdminPwd());

        return  "home";
    }
    //第四种 springmvc 的传值方式 ！！ modelMap
    @RequestMapping("/modelMap")
    public String modelMap(AdminInfo adminInfo, ModelMap modelMap){
        modelMap.put("adminName",adminInfo.getAdminName());
        modelMap.put("adminPwd",adminInfo.getAdminPwd());
        return  "home";
    }
    //第五种  springmvc传值方式 map灵活
    @RequestMapping("/map")
    public  String  map(AdminInfo adminInfo,Map<String,Object> map){
        map.put("adminName",adminInfo.getAdminName());
        map.put("adminPwd",adminInfo.getAdminPwd());
        return "home";
    }
}

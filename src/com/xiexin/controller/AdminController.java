package com.xiexin.controller;

import com.xiexin.bean.AdminInfo;
import com.xiexin.bean.Dog;
import com.xiexin.bean.Lover;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/admin")
public class AdminController {
    private ArrayList<String> adminAiHao;
    //注册成功后，如果是单体项目-----就要跳转到登录页面，这个跳转是后台的转发，重定向，总之就是
    //后台负责跳转，携带数据的跳转页面的
    //如果是 新型的项目 即前后端分离的，那么 后台 只负责 返回给前端json数据
    //跳转是 前端来处理的 前端根据 后台返回code 代码，进行跳转。
    //如果前端负责跳转，他会起一个好听的名字，叫做路由

    //什么是前后端分离 即: 项目上的分离 和 数据上的分离
    //项目上的分离： 前端一个项目 后台一个项目  2个项目. 他们的认证是 token/jwt + redis
    //数据上的分离： 还是一个项目 只不过 前后端 用json来交互数据
    //很少在用jstl/el 表达式 来写项目。他们的认证 是 session

    //layui 在ssm 框架 的使用，其实是 数据上的分离 也可以项目上的分离。
    //那么他就是 json 交互的， 那么后台 只需要 给他 返回json数据就可以了

    //第一种收取参数方式：数据类型接收参数
    @RequestMapping("/reg")//layui版本的
    @ResponseBody//这个注解就是 返回给前端的 json数据
    public  Map reg(String adminName, String adminPwd, String adminPwdR, String adminTime, String adminSex, ArrayList<String> adminAiHao, String adminCity, String adminOpen){
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
        webDataBinder.setFieldDefaultPrefix("lover.");//设置前缀 lover.name：老薛

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
    @RequestMapping("/ajax08")
    @ResponseBody
    public  Map ajax08(Lover lover,@RequestParam( value = "limit" ,required = false,defaultValue = "5")Integer pageSize,Integer page){
        System.out.println("lover = " + lover);
        System.out.println("page = " + page);
        System.out.println("pageSize = " + pageSize);

        return  null;
    }

}

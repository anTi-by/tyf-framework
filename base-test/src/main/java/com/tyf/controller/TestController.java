package com.tyf.controller;

import com.tyf.pojo.User;
import com.tyf.utils.SSOcheck;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/a")
@Controller
public class TestController {
    @RequestMapping("")
    public String hello(ModelMap map){
        map.addAttribute("host", "http://blog.didispace.com");
        return "index.html";
    }

    @RequestMapping("/b")
    @ResponseBody
    public String hellso(User user, HttpServletResponse response){
        boolean istrue=SSOcheck.check(user.getAccount(),user.getPass());
        if(istrue){
           Cookie coo=new Cookie("sso","sso");
            coo.setPath("/");
            response.addCookie(coo);
            return "success";
        }
        System.out.println(user.getAccount()+"===========================");
        return "hello.html";
    }

}

package com.tyf.controller;

import com.tyf.pojo.User;
import com.tyf.utils.SSOcheck;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/sso")
@Controller
public class SSOController {
    @RequestMapping("/login")
    public String Login(User user, HttpServletResponse response, ModelMap map){
        boolean istrue= SSOcheck.check(user.getAccount(),user.getPassword());
        if(istrue){
            Cookie coo=new Cookie("ssoo","ssoo");
            coo.setPath("/");
            response.addCookie(coo);
            return user.getGotoUrl();
        }
        System.out.println(user.getAccount()+"===========================");
        return "index";
    }
}

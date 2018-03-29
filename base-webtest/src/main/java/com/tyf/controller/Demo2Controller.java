package com.tyf.controller;

import com.tyf.utils.SSOcheck;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/demo2")
@Controller
public class Demo2Controller {
    @RequestMapping("")
    public String main(HttpServletRequest request,ModelMap map){
       boolean res= SSOcheck.checkCookie(request);
       if (res){
           return "demo2";
       }
        map.put("gotoUrl","demo2");
        return "index";
    }
}

package com.tyf.controller;

import com.tyf.utils.SSOcheck;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/demo1")
@Controller
public class Demo1Controller {
    @RequestMapping("")
    public String main(HttpServletRequest request,ModelMap map){
        request.getSession().setAttribute("nihao","dff");
        boolean res= SSOcheck.checkCookie(request);
        if (res){
            return "demo1";
        }
        map.put("gotoUrl","demo1");
        return "index";
    }
}

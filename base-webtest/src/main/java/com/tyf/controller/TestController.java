package com.tyf.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/t")
public class TestController {
    @RequestMapping("/t")
    @ResponseBody
    public String Test(HttpServletRequest request){
        System.out.println(request.getMethod());
        return "ggggg";


    }
}

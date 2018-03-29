package com.tyf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/a")
@Controller
public class TestController {
    @RequestMapping("/a")
    @ResponseBody
    public String hello(){
        return  "https://blog.csdn.net/lxn39830435731415926/article/details/46434241";
    }

    @RequestMapping("/b")
    public String hellso(){
        return "hello.html";
    }

}

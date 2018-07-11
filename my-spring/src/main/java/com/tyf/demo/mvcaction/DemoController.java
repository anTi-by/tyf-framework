package com.tyf.demo.mvcaction;


import com.tyf.demo.service.IDemoService;
import com.tyf.mvcframework.webmvc.annotaion.TYAutowired;
import com.tyf.mvcframework.webmvc.annotaion.TYController;
import com.tyf.mvcframework.webmvc.annotaion.TYRequestMapping;
import com.tyf.mvcframework.webmvc.annotaion.TYRequestParam;

@TYController
@TYRequestMapping("/demo")
public class DemoController {
    @TYAutowired
    private IDemoService iDemoService;
    @TYRequestMapping("/query.json")
    public void query(@TYRequestParam("name") String name){

    }
}

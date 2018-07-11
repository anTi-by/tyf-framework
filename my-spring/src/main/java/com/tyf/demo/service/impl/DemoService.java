package com.tyf.demo.service.impl;

import com.tyf.demo.service.IDemoService;
import com.tyf.mvcframework.webmvc.annotaion.TYService;

@TYService
public class DemoService implements IDemoService {
    public String getName(int id) {
        return "HELLO :"+id;
    }
}

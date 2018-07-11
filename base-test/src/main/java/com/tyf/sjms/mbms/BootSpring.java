package com.tyf.sjms.mbms;

import java.util.HashMap;
import java.util.Map;

public class BootSpring extends StartSpring {
    Map<String,String> map=new HashMap<>();
    @Override
    void initialize() {
        map.put("ini","initialize");
    }

    @Override
    void start() {
        System.out.println(map.get("ini"));
    }

    @Override
    void end() {
        map.remove("ini");
    }
}

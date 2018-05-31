package com.tyf.sjms.mmms;

import com.tyf.sjms.mmms.children.ModuleA;
import com.tyf.sjms.mmms.children.ModuleB;
import com.tyf.sjms.mmms.children.ModuleC;

public class Facade {
    ModuleA a = new ModuleA();
    ModuleB b = new ModuleB();
    ModuleC c = new ModuleC();
    /**
     * 下面这些是A、B、C模块对子系统外部提供的方法
     */
    public void a1(){
        a.a1();
    }
    public void b1(){
        b.b1();
    }
    public void c1(){
        c.c1();
    }
}

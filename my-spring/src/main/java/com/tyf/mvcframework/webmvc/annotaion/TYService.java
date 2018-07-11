package com.tyf.mvcframework.webmvc.annotaion;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented //表示注解是可以被识别的
public @interface TYService {
    String value() default "";
}

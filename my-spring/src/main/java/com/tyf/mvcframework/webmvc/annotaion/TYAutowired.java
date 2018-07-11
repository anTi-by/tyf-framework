package com.tyf.mvcframework.webmvc.annotaion;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented //表示注解是可以被识别的
public @interface TYAutowired {
    String value() default "";
}

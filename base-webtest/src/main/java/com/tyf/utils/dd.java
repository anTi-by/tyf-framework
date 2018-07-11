package com.tyf.utils;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class dd {
    public static void main(String[] args) throws Throwable {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource res = resolver.getResource("classpath:com/baobaotao/beanfactory/beans.xml");
        BeanFactory bf = new XmlBeanFactory(res);
        System.out.println("init BeanFactory.");

        System.out.println("car bean is ready for use!");
    }
}

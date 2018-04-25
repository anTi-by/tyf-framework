package com.tyf.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

public class MyServletRequestAttributeListener implements ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("MyServletRequestAttributeListener_attributeAdded :"+servletRequestAttributeEvent.getName());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("MyServletRequestAttributeListener_attributeRemoved :"+servletRequestAttributeEvent.getName());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("MyServletRequestAttributeListener_attributeReplaced :"+servletRequestAttributeEvent.getName());
    }
}

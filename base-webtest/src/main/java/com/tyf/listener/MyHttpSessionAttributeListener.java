package com.tyf.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("MyHttpSessionAttributeListener_attributeAdded :"+httpSessionBindingEvent.getName());
        System.out.println(httpSessionBindingEvent.getSession().getAttribute("nihao")+"=====================================");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("MyHttpSessionAttributeListener_attributeRemoved :"+httpSessionBindingEvent.getName());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("MyHttpSessionAttributeListener_attributeReplaced :"+httpSessionBindingEvent.getName());
    }
}

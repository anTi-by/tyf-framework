package com.tyf.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class FirstListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        System.out.println("监听器启动");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("监听器销毁");
    }
}

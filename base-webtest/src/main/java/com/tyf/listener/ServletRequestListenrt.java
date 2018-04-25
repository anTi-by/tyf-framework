package com.tyf.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class ServletRequestListenrt implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("requestDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println("requestInitialized");
    }
}

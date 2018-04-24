package com.tyf.sjms.gczms;

public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
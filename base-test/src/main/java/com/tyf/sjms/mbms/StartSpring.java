package com.tyf.sjms.mbms;

public abstract class StartSpring {
    abstract void initialize();
    abstract void start();
    abstract void end();
    public void startSpring(){
        initialize();
        start();
        end();
    }
}

package com.tyf.nio;

import java.io.IOException;

/**
 * NIO 实现时间服务器
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port=8080;
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer,"NIO-MultiplexerTimeServer-001").start();
    }
}

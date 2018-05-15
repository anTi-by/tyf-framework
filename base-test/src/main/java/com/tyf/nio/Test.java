package com.tyf.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

public class Test {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8099);
        serverSocket.bind(hostAddress);
    }
}

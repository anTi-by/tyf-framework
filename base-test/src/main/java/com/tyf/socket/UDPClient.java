package com.tyf.socket;

import com.sun.xml.internal.ws.wsdl.writer.document.http.Address;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        String s = "测试UDP!";

        // 创建发送端Socket, 绑定本机IP地址, 绑定任意一个未使用的端口号
        DatagramSocket socket = new DatagramSocket();

        // 创建发送端Packet, 指定数据, 长度, 地址, 端口号
        DatagramPacket packet = new DatagramPacket(s.getBytes("UTF-8"), s.getBytes().length, InetAddress.getByName("127.0.0.1"), 8081);

        // 使用Socket发送Packet
        socket.send(packet);

        // 关闭Socket
        socket.close();
    }
}

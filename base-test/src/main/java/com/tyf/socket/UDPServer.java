package com.tyf.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket DatagramSocket =new DatagramSocket(8081);
            // 创建接收端Packet, 用来接收数据
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            while (true){
                DatagramSocket.receive(packet);
                byte[] data = packet.getData();
                int len = packet.getLength();
                String s = new String(data, 0, len, "UTF-8");
                System.out.println(s);
            }
            // 用Socket接收Packet, 未收到数据时会阻塞


            // 关闭Socket
           // DatagramSocket.close();

            // 从Packet中获取数据

        } catch (Exception e) {
            e.printStackTrace();
        }
        // DatagramPacket DatagramPacket=new DatagramPacket();
    }
}

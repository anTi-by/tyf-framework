package com.tyf.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
//为每一个链接创建一个线程
public class TimeServerHander implements Runnable {
    private Socket socket;

    public TimeServerHander(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in =null;
        PrintWriter out=null;
        try{
            in=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out=new PrintWriter(this.socket.getOutputStream(),true);
            String currentTime=null;
            String body=null;
            while(true){
                body=in.readLine();
                if(body==null)
                    break;
                System.out.println("receive: "+body);
                currentTime="QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
                out.println(currentTime);
            }
        }catch (IOException e){

        }finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

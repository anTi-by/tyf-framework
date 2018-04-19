package com.tyf.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// 实现伪异步
public class TimeServerWyb {
    public static void main(String[] args) throws IOException {
        int port=8080;
        if(args!=null&&args.length>0){
            try {
                port=Integer.valueOf(args[0]);
            }catch (NumberFormatException e){

            }

        }

        ServerSocket serverSocket=null;

        try{
            serverSocket=new ServerSocket(port);
            System.out.println("socket start in port:"+port);
            Socket socket=null;
            while(true){
                TimeServerHanderExecutePool pool=new TimeServerHanderExecutePool(10,3);
                socket=serverSocket.accept();
                pool.execute(new TimeServerHander(socket));
            }

        }catch (Exception e){

        }finally {
            if (serverSocket!=null){
                serverSocket.close();
                serverSocket=null;
            }
        }
    }
}

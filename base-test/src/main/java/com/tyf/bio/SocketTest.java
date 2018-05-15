package com.tyf.bio;

import com.mysql.jdbc.util.ServerController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SocketTest {

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
            int i=1;
            while(true){
                socket=serverSocket.accept();
                System.out.println("client in " +(i++));
                BufferedReader in =null;
                PrintWriter out=null;
                try{
                    in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out=new PrintWriter(socket.getOutputStream(),true);
                    String currentTime=null;
                    String body=null;
                    while(true){
                        body=in.readLine();
                        System.out.println("");
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
                        System.out.println("");
                        out.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
//                new Thread(new TimeServerHander(socket)).start();
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

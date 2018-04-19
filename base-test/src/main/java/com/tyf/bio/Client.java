package com.tyf.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        int port =8080;
        Socket socket=null;
        BufferedReader in=null;
        PrintWriter out=null;
        try{
            socket=new Socket("127.0.0.1",port);
            while(true){
                in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out =new PrintWriter(socket.getOutputStream(),true);
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String str;
                str = br.readLine();
                //"QUERY TIME ORDER"
                out.println(str);



            System.out.println("send success");
            String res=in.readLine();
            System.out.println("NOW IS "+res);
            }
        }catch (Exception E){

        }finally {
            out.close();
            try {
                in.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}

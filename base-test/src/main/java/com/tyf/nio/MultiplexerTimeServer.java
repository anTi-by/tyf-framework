package com.tyf.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimeServer implements Runnable {
    private Selector selector;   //多路复用器
    private ServerSocketChannel servChannel;   //管道
    private  volatile  boolean stop;

    /**
     * 初始化多路复用器，绑定监听端口
     * 在构造方法中进行资源初始化。创建多路复用器Selector,ServerSocketChannel,对Channel和TCP参数进行配置。
     * 将ServerSocketChannel设置为一部非阻塞模式，backlog（队列大小）设置为1024.系统资源初始化成功后，将ServerSocketChannel注册到Selector.
     * 监听SelectionKey.OP_ACCEPT操作位。如果资源初始化失败，则退出。
     * @param port
     */
    public MultiplexerTimeServer(int port) {
        try {
            selector= Selector.open();//开启多路复用器
            servChannel=ServerSocketChannel.open();//开启管道
            servChannel.configureBlocking(false);//设置为非阻塞模式
            servChannel.socket().bind(new InetSocketAddress(port),1024);//绑定端口
            servChannel.register(selector,SelectionKey.OP_ACCEPT);//将管道注册到多路复用器上
            System.out.println("Server Start in Port :"+port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public  void stop(){
        this.stop=true;
    }

    /**
     * while循环体中循环遍历selector，他的休眠时间为1s。无论是否有读写等事件发生，selector每隔1s都会被唤醒一次。
     * selector也提供了一个午餐的select方法：当有处于就绪状态的Channel时，selector将返回该Channel的SelectionKey集合。通过对就绪状态的Channel集合进行迭代，
     * 可以进行网络的异步读写操作。
     */
    @Override
    public void run() {
        while(!stop){
            try {
                selector.select(1000);//设置多路复用器休眠时间1s。
                Set<SelectionKey> selectionKeys =selector.selectedKeys();//获取就绪
                Iterator<SelectionKey> it=selectionKeys.iterator();
                SelectionKey key=null;
                while(it.hasNext()){
                    key=it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    }catch (Exception e){
                        if (key!=null){
                            key.cancel();
                            if(key.channel()!=null){
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //多路复用器关闭后，所有注册在上面啊的Channel和Pipe扽资源都会被自动去注册并关闭，所以不需要重复释放资源
        if (selector!=null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private  void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            //处理新介入的请求消息
            if(key.isAcceptable()){
                ServerSocketChannel ssc =(ServerSocketChannel)key.channel();
                SocketChannel sc=ssc.accept();
                sc.configureBlocking(false);
                //将新链接的客户端注册到复用器上
                sc.register(selector,SelectionKey.OP_READ);
            }
            if (key.isReadable()){
                //读取数据
                SocketChannel sc =(SocketChannel)key.channel();
                ByteBuffer readBuffer=ByteBuffer.allocate(1024);
                int readByts=sc.read(readBuffer);
                if(readByts>0){
                    readBuffer.flip();
                    byte[] bytes=new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body =new String (bytes,"UTF-8");
                    System.out.println("Server Receive order :"+body);
                    String currentTime="QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
                    doWrite(sc,currentTime);
                }else if(readByts<0){
                    //对链路进行关闭
                    key.cancel();
                    sc.close();
                }else {
                    //读到0字节  ，忽略
                }
            }
        }
    }
    private  void doWrite(SocketChannel channel,String response) throws  IOException{
        if(response!=null&& response.trim().length()>0){
            byte[] bytes=response.getBytes();
            ByteBuffer writeBuffer=ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }

    }
}

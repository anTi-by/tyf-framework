package com.tyf.nio;

import com.sun.corba.se.spi.ior.Writeable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 将数据从一个通道复制到另一个通道或从一个文件复制到另一个文件的示例：
 */
public class ChannelDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream in =new FileInputStream("C:\\tyf\\jl.txt");
        ReadableByteChannel source=in.getChannel();
        FileOutputStream out=new FileOutputStream("C:\\tyf\\jloo.txt");
        WritableByteChannel d=out.getChannel();
        copyData(source,d);
        source.close();
        d.close();
    }

    private static void copyData(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(20 * 1024);
        while (src.read(buffer) != -1) {
            // The buffer is used to drained
            buffer.flip();
            // keep sure that buffer was fully drained
            while (buffer.hasRemaining()) {
                dest.write(buffer);
            }
            buffer.clear(); // Now the buffer is empty, ready for the filling
        }
    }
}

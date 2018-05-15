package com.tyf.nio;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BufferedReaderDemo {
    public static void main(String[] args) throws IOException {
        InputStream in = Files.newInputStream( Paths.get("C:\\tyf\\jl.txt"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        System.out.println("Reading the Line of testout.txt file: \n" + bufferedReader.readLine());
        bufferedReader.close();
    }
}

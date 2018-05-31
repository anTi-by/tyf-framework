package com.tyf.utils;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;



public class DESUtils {
    private static String key="WBVhRcjciUk7tUAZ/bzHzVgVYUXI3IlJ"; //密钥
    private static final String DES3 = "DESede";
    /**
     * 3DES ECB模式加密
     */
    public static String des3EncodeECB(String data) {
        try {
            SecretKey DESKey = new SecretKeySpec(Base64.decodeBase64(key), DES3);    //生成密钥
            Cipher cipher = Cipher.getInstance(DES3 + "/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, DESKey);
            byte[] b=cipher.doFinal(data.getBytes("UTF-8"));
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(b).replaceAll("\r", "").replaceAll("\n", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 3DES ECB模式解密
     */
    public static String des3DecodeECB(String data) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytesrc = decoder.decodeBuffer(data);
            SecretKey DESKey = new SecretKeySpec(Base64.decodeBase64(key), DES3);    //生成密钥
            Cipher cipher = Cipher.getInstance(DES3 + "/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, DESKey);
            byte[] retByte =  cipher.doFinal(bytesrc);
            return new String(retByte);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 根据字符串生成密钥字节数组
     *
     * @param keyStr 密钥字符串
     */
    private static byte[] build3DesKey(String keyStr) {
        try {
            byte[] key = new byte[24];    //声明一个24位的字节数组，默认里面都是0
            byte[] temp = keyStr.getBytes("UTF-8");    //将字符串转成字节数组
            if (key.length > temp.length) {
                //如果temp不够24位  ，则拷贝temp数组整个长度的内容到key数组中
                System.arraycopy(temp, 0, key, 0, temp.length);
            } else {
                //如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
                System.arraycopy(temp, 0, key, 0, key.length);
            }
            return key;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    * QLal40jz9+HLgkHlF8vzmg==
        fjgs@123
    */

    //测试类
    
    public static void main(String[] args) {
        String a=DESUtils.des3EncodeECB("fjgs@123");
        String B= DESUtils.des3DecodeECB(a);
        System.out.println(a);
        System.out.println(B);
    }
}

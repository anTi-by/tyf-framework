package com.tyf.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckImgUtils {

    public static void main(String[] args) {
       List<String> a= CheckImgUtils.getImgSrcList("ewqewq<img alt=\"\" src=\"1.jpg\"/> <img alt=\"\" src=\"1.jpg\">" +
                "fdfsfsd</img> <img widsj='fff' alt=\"\" src=\"1.jpg\">ff");

        for (String ss:a) {
            System.out.println(ss);
        }
    }
    public static List<String> getImgSrcList(String content){
        List<String> list = new ArrayList<String>();
//目前img标签标示有3种表达式
//<img alt="" src="1.jpg"/> <img alt="" src="1.jpg"></img> <img alt="" src="1.jpg">
//开始匹配content中的<img />标签
        Pattern p_img = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)");
        Matcher m_img = p_img.matcher(content);
        boolean result_img = m_img.find();
        if (result_img) {
            while (result_img) {
//获取到匹配的<img />标签中的内容
                String str_img = m_img.group(2);
//开始匹配<img />标签中的src
                Pattern p_src = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
                Matcher m_src = p_src.matcher(str_img);
                if (m_src.find()) {
                    String str_src = m_src.group(3);
                    list.add(str_src);
                }
//匹配content中是否存在下一个<img />标签，有则继续以上步骤匹配<img />标签中的src
                result_img = m_img.find();
            }
        }
        return list;
    }
}

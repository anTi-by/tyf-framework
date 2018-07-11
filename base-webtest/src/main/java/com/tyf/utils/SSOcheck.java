package com.tyf.utils;

import org.springframework.beans.factory.xml.XmlBeanFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class SSOcheck {

    public static boolean check(String acccount,String pass){
        if (acccount.equals("1234")){
            return true;
        }

        return false;
    }

    public static boolean checkCookie(HttpServletRequest request){
       Cookie[] cookie= request.getCookies();
      if(cookie!=null){
          for(Cookie co:cookie){
              if (co.getName().equals("ssoo")&&co.getValue().equals("ssoo")){
                  return true;
              }
          }
      }
        return false;
    }

}

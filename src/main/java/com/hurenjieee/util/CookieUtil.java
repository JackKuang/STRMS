package com.hurenjieee.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Description: Cookie工具类
 * @Author: JackKuang
 * @Since: 2017年1月23日下午10:52:41  
 */
public class CookieUtil {
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null || name == null || name.length() == 0) {
          return null;
        }
        for (int i = 0; i < cookies.length; i++) {
          if (name.equals(cookies[i].getName())
              && request.getServerName().equals(cookies[i].getDomain())) {
            return cookies[i];
          }
        }
        return null;
      }

      public static void deleteCookie(HttpServletRequest request,
          HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
          cookie.setPath(getPath(request));
          cookie.setValue("");
          cookie.setMaxAge(0);
          response.addCookie(cookie);
        }
      }

      public static void setCookie(HttpServletRequest request,
          HttpServletResponse response, String name, String value) {
        setCookie(request, response, name, value, 0x278d00);
      }

      public static void setCookie(HttpServletRequest request,
          HttpServletResponse response, String name, String value, int maxAge) {
       if(value==null){
        value="";
       }else{
       try {
        value=URLEncoder.encode(value,"utf-8");
       } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
       }
       }
        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(getPath(request));
        response.addCookie(cookie);
      }

      private static String getPath(HttpServletRequest request) {
        String path = request.getContextPath();
        return (path == null || path.length()==0) ? "/" : path;
      }
   }

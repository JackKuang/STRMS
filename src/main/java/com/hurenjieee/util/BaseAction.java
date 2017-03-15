package com.hurenjieee.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.CookiesAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.web.context.ServletContextAware;

import com.hurenjieee.service.BaseService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public abstract class BaseAction<T, ID extends Serializable> extends ActionSupport
        implements ServletContextAware, ServletRequestAware, ServletResponseAware, ApplicationAware, SessionAware, CookiesAware {

    private static final long serialVersionUID = 1L;

    // ------------------BaseService 和 Object 作为基本类-------------------------
    public BaseService<T, ID> service;

    public T object;

    public abstract BaseService<T, ID> getService();

    public abstract T getObject();
    
    private Map<String, Object> resultMap;

    // ------------------BaseService 和 Object 作为基本类-------------------------

    // -------------------------资源访问设置---------------------------------
    // request请求
    private HttpServletRequest  request;
    // request请求
    private HttpServletResponse response;

    // 全局application,针对于整个系统而言
    private Map<String, Object> applicationMap;

    // session存在服务器端，关闭浏览器就消失。
    private Map<String, Object> sessionMap;

    // cookie存在客户端中，银盘持久化
    private Map<String, String> cookieMap;

    private ServletContext servletContext;

    @Override
    public void setApplication(Map<String, Object> application){
        this.applicationMap = application;
    }

    @Override
    public void setServletRequest(HttpServletRequest request){
        // request.setCharacterEncoding("");
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response){
        this.response = response;
    }

    @Override
    public void setCookiesMap(Map<String, String> cookies){
        this.cookieMap = cookies;
    }

    @Override
    public void setSession(Map<String, Object> session){
        this.sessionMap = session;
    }

    public HttpServletRequest getRequest(){
        return request;
    }

    public HttpServletResponse getResponse(){
        return response;
    }

    public Map<String, Object> getApplicationMap(){
        return applicationMap;
    }

    public Map<String, Object> getSessionMap(){
        return sessionMap;
    }

    public Map<String, String> getCookieMap(){
        return cookieMap;
    }

    public ServletContext getServletContext(){
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext){
        this.servletContext = servletContext;
    }

    // -------------------------资源访问设置结束-------------------------------

    // -----------------------通用访问参数---------------------------
    private Map<String, Integer> intMap;

    private Map<String, String> strMap;

    private Map<String, Double> douMap;

    public Map<String, Integer> getIntMap(){
        return intMap;
    }

    public void setIntMap(Map<String, Integer> intMap){
        this.intMap = intMap;
    }

    public Map<String, String> getStrMap(){
        return strMap;
    }

    public void setStrMap(Map<String, String> strMap){
        this.strMap = strMap;
    }

    public Map<String, Double> getDouMap(){
        return douMap;
    }

    public void setDouMap(Map<String, Double> douMap){
        this.douMap = douMap;
    }

    public Map<String, Object> getResultMap(){
        return  resultMap==null?new HashMap<String, Object>():resultMap;
    }

    
    public void setResultMap(Map<String, Object> resultMap){
        this.resultMap = resultMap;
    }
    // -----------------------通用访问参数结束---------------------------

    // --------------通用方法----------------

    /**
     * 直接返回resMapde的JSON类型
     * @param resultMap 返回Map
     */
    protected void ResponseJson(Map<String, Object> resultMap){

        // 通过json-lib包来实现Map转String
        JSONObject jsonMap = JSONObject.fromObject(resultMap);
        String jsonString = jsonMap.toString();

        response.setContentType("text/html; charset=utf-8"); // 字符编码
        response.setHeader("pragma","no-cache"); // 不缓存
        response.setHeader("cache-control","no-cache");

        try {
            response.getWriter().write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String save(){
        try {
        	getResultMap();
            getService().saveOrUpdate(getObject());
            resultMap.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result","fail");
            resultMap.put("reason","未知错误！");
        }
        return "json";
    }

    public String update(){
        try {
        	getResultMap();
            getService().update(getObject());
            resultMap.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result","fail");
            resultMap.put("reason","未知错误！");
        }
        return "json";
    }
    
    public String delete(){
        try {
        	getResultMap();
            getService().delete(getObject());
            resultMap.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result","fail");
            resultMap.put("reason","未知错误！");
        }
        return "json";
    }
    
    public String list(){
        try {
        	getResultMap();
            List<T> list = getService().getList();
            resultMap.put("result","success");
            resultMap.put("list",list);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result","fail");
            resultMap.put("reason","未知错误！");
        }
        return "json";
    }
    
    // --------------通用方法----------------
}

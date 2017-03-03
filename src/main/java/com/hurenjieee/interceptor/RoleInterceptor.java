package com.hurenjieee.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hurenjieee.entity.Admin;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class RoleInterceptor implements Interceptor {

    @Override
    public void destroy(){

    }

    @Override
    public void init(){

    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception{
        // 增加返回结果前事件，实际开发过程中可以查看信息
        // 取得请求相关的ActionContext实例
        ActionContext actionContext = invocation.getInvocationContext();
        Map<String, Object> session = actionContext.getSession();
        String userType = (String) session.get("userType");
        HttpServletRequest httpServletRequest = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
        String path = httpServletRequest.getServletPath();
        //"/admin/admin!index.action"
        System.out.println(path);
        String servletPath = httpServletRequest.getServletPath();
        String loginUserType = servletPath.split("/")[1];
        // 去除不需要过滤的网址
        if (loginUserType.equals(userType) || "/login!login.action".equals(servletPath) || "/login!toLogin.action".equals(servletPath)) {
            // 经过Servlet方法
            return invocation.invoke();
        }
        session.clear();
        return Action.LOGIN;
    }

}

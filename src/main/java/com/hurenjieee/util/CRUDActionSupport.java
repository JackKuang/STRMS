package com.hurenjieee.util;

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

import com.hurenjieee.entity.Page;
import com.opensymphony.xwork2.ActionSupport;

public abstract class CRUDActionSupport<T> extends ActionSupport
		implements ServletContextAware, ServletRequestAware, ServletResponseAware, ApplicationAware, SessionAware, CookiesAware {

	private static final long serialVersionUID = 1L;

	// -------------------------资源访问设置---------------------------------
	// request请求
	private HttpServletRequest request;
	// request请求
	private HttpServletResponse response;
	// 全局application,针对于整个系统而言
	// 存在服务器端（服务器一般不停止，如果要停止就存在数据库，启动后在从数据库取出）
	private Map<String, Object> applicationMap;
	// session存在服务器端，关闭浏览器就消失。
	private Map<String, Object> sessionMap;
	// cookie存在客户端中，银盘持久化
	private Map<String, String> cookieMap;
	
	private ServletContext servletContext;

	@Override
	public void setApplication(Map<String, Object> application) {
		this.applicationMap = application;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// request.setCharacterEncoding("");
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setCookiesMap(Map<String, String> cookies) {
		this.cookieMap = cookies;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.sessionMap = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Map<String, Object> getApplicationMap() {
		return applicationMap;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public Map<String, String> getCookieMap() {
		return cookieMap;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	// -------------------------资源访问设置结束-------------------------------


	// -------------------------页码信息设置----------------------------------
	// 页码序号
	private int pageNo;
	// 页码大小
	private int pageSize;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	// 设置分页信息，调用这个方法，再在sql调用就可以执行分页操作
	public void setPageInfo(Page<T> page) {
		if (pageNo > 0) {
			page.setPageNo(pageNo);
		}
		if (pageSize > 0) {
			page.setDataSize(pageSize);
		}
	}
	// -------------------------页码信息设置结束-------------------------------

}

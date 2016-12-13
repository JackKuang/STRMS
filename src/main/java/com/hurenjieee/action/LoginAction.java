package com.hurenjieee.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.w3c.dom.css.ElementCSSInlineStyle;

import com.hurenjieee.service.LoginService;
import com.hurenjieee.util.CRUDActionSupport;
import com.hurenjieee.util.GlobalUtil;

@ParentPackage(value = "all") // 应用全局包
@Scope("prototype")
@Action(results = { @Result(name = "success-admin", location = "/WEB-INF/jsp/admin/index.jsp"),
		@Result(name = "success-teacher", location = "/WEB-INF/jsp/teacher/index.jsp"),
		@Result(name = "success-student", location = "/WEB-INF/jsp/student/index.jsp"),
		@Result(name = "toLogin", location = "/WEB-INF/jsp/login.jsp") })
public class LoginAction extends CRUDActionSupport<Object> {

	@Autowired
	LoginService loginService;
	private String userName;
	private String password;
	private String type;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String login() {
		String result= "toLogin";
		if("student".equals(type)){//学生登录
			getRequest().setAttribute("wrong", "1");
			getRequest().setAttribute("type", type);
			result = "toLogin";
		}else if("teacher".equals(type)){//教师登录
			getRequest().setAttribute("wrong", "1");
			getRequest().setAttribute("type", type);
			result = "toLogin";
		}else if("admin".equals(type)){//管理员登录
			loginService.login("admin", "111");
			if (loginService.login(userName, password)) {
				result = "success-admin";
			} else {
				getRequest().setAttribute("wrong", 1);
				getRequest().setAttribute("type", type);
				result = "toLogin";
			}
		}
		return result;
	}

	public String toLogin() {
		return "toLogin";
	}
}

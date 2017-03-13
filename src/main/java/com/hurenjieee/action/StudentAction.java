package com.hurenjieee.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;

//������඼��action��ת
@ParentPackage(value = "all") // Ӧ��ȫ�ְ�
@Scope("prototype")
@Namespace(value = "/teacher")
@Action(results = { @Result(name = "index", location = "/WEB-INF/jsp/teacher/index.jsp") })
public class StudentAction extends ActionSupport {
	String flag;

	public String index() {
		return "index";
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String redirectPage() {
		return flag;
	}
}
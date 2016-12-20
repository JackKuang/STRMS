package com.hurenjieee.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.util.CRUDActionSupport;

@ParentPackage(value = "all") // 应用全局包
@Scope("prototype")
@Namespace(value="/admin")
@Action(results = {
		@Result(name = "index", location = "/WEB-INF/jsp/admin/index.jsp")})
public class AdminAction extends CRUDActionSupport<Object> {
	
	public String index(){
		return "index";
	}
}
	
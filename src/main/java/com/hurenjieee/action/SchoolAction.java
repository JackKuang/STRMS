package com.hurenjieee.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.hibernate.type.StringRepresentableType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Admin;
import com.hurenjieee.entity.Student;
import com.hurenjieee.service.BaseService;
import com.hurenjieee.service.StudentService;
import com.hurenjieee.service.TeacherService;
import com.hurenjieee.util.BaseAction;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSON;

@ParentPackage(value = "json") // 应用全局包
@Scope("prototype")
@Namespace(value = "/admin")
@Action(results = {
		@Result(name = "json", type = "json", params = { "root", "resultMap"}) })
public class SchoolAction extends ActionSupport {

	Map<String, Object> resultMap;
	
	
	public Map<String, Object> getResultMap() {
		return resultMap;
	}


	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}


	public String testJson(){
		resultMap =new HashMap<String,Object>();
		Admin admin = new Admin();
		admin.setAdmName("admin");
		admin.setAdmPic("12333");
		resultMap.put("admin", admin);
		return "json";
	}
	
}

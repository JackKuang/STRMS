package com.hurenjieee.action;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Branch;
import com.hurenjieee.entity.Major;
import com.hurenjieee.service.BranchService;
import com.hurenjieee.util.CRUDActionSupport;

@ParentPackage(value = "json-default") // 应用全局包
@Scope("prototype")
@Namespace(value = "/admin")
@Action(results = { @Result(name = "index", location = "/WEB-INF/jsp/admin/index.jsp"),
		@Result(name = "branchList", type = "json", params = {"root", "resultMap","excludeProperties","^.*majors$"}) })
public class SchoolAction extends CRUDActionSupport<Object> {

	@Autowired
	BranchService branchService;
	
	
	Map<String, Object> resultMap;

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String branchList() throws IOException {
		resultMap = new TreeMap<String, Object>();
		List<Branch> list =  branchService.getList();
		resultMap.put("key",list);
		return "branchList";
	}

	public String index() {
		return "index";
	}
}

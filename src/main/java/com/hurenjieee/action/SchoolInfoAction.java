package com.hurenjieee.action;

import java.util.Map;
import java.util.TreeMap;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.service.BranchService;
import com.hurenjieee.service.CollectiveService;
import com.hurenjieee.service.MajorService;
import com.hurenjieee.util.CRUDActionSupport;

@ParentPackage(value = "json") // Ó¦ÓÃjson°ü
@Scope("prototype")
@Namespace(value="/admin")
@Action(value="featureClassesJson",results={@Result(name = "branchList", type="json")})
public class SchoolInfoAction  extends CRUDActionSupport<Object>{

	@Autowired
	BranchService branchService;
	
	@Autowired
	MajorService majorService;
	
	@Autowired
	CollectiveService collectiveService;
	
	private Map<String,Object> resultMap;
	
	public String branchList(){
		resultMap = new TreeMap<String,Object>();
		resultMap.put("key", "value");
		return SUCCESS;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}

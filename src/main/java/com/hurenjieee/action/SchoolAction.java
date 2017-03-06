package com.hurenjieee.action;

import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Admin;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value = "json") // 应用全局包
@Scope("prototype")
@Namespace(value = "/admin")
@Action(results = { @Result(name = "json",type = "json",params = { "root", "resultMap" }) })
public class SchoolAction extends ActionSupport {

    Map<String, Object> resultMap;

    public Map<String, Object> getResultMap(){
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap){
        this.resultMap = resultMap;
    }

    public String testJson(){
        resultMap = new HashMap<String, Object>();
        Admin admin = new Admin();
        admin.setAdmName("admin");
        admin.setAdmPic("12333");
        resultMap.put("admin",admin);
        return "json";
    }

}

package com.hurenjieee.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Admin;
import com.hurenjieee.entity.Branch;
import com.hurenjieee.service.BranchService;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value = "json") // 应用全局包
@Scope("prototype")
@Namespace(value = "/admin")
@Action(results = { @Result(name = "json",type = "json",params = { "root", "resultMap" }) })
public class SchoolAction extends ActionSupport {

    Map<String, Object> resultMap;
    
    private String braName;
    
    @Autowired
    private BranchService branchService;

    public Map<String, Object> getResultMap(){
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap){
        this.resultMap = resultMap;
    }
    
    public String getBraName(){
        return braName;
    }

    
    public void setBraName(String braName){
        this.braName = braName;
    }

    public String testJson(){
        resultMap = new HashMap<String, Object>();
        Admin admin = new Admin();
        admin.setAdmName("admin");
        admin.setAdmPicture("12333");
        resultMap.put("admin",admin);
        return "json";
    }
    public String saveBranch(){
        Branch branch = new Branch();
        branch.setBraName(braName);
        branchService.save(branch);
        resultMap = new HashMap<String, Object>();
        resultMap.put("result","success");
        return "json";
    }
}

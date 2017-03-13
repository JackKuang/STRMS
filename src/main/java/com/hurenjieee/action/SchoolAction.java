package com.hurenjieee.action;

import java.security.KeyStore.PrivateKeyEntry;
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
import com.hurenjieee.entity.Major;
import com.hurenjieee.service.BranchService;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value = "json") // 应用全局包
@Scope("prototype")
@Namespace(value = "/admin")
@Action(results = { @Result(name = "json",type = "json",params = { "root", "resultMap" }) })
public class SchoolAction extends ActionSupport {

    Map<String, Object> resultMap;

    private Branch branch;
    
    private Major major;

    @Autowired
    private BranchService branchService;

    public String testJson(){
        resultMap = new HashMap<String, Object>();
        Admin admin = new Admin();
        admin.setAdmName("admin");
        admin.setAdmPicture("12333");
        resultMap.put("admin",admin);
        return "json";
    }

    public Map<String, Object> getResultMap(){
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap){
        this.resultMap = resultMap;
    }

    public Branch getBranch(){
        return branch;
    }

    public void setBranch(Branch branch){
        this.branch = branch;
    }
    
    public Major getMajor(){
        return major;
    }

    public void setMajor(Major major){
        this.major = major;
    }
    
}

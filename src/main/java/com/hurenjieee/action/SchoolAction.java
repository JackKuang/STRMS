package com.hurenjieee.action;

import java.io.IOException;
import java.io.Serializable;
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
import org.hibernate.type.StringRepresentableType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Branch;
import com.hurenjieee.entity.Major;
import com.hurenjieee.entity.Student;
import com.hurenjieee.service.BaseService;
import com.hurenjieee.service.BranchService;
import com.hurenjieee.service.CollectiveService;
import com.hurenjieee.service.MajorService;
import com.hurenjieee.service.StudentService;
import com.hurenjieee.service.TeacherService;
import com.hurenjieee.util.BaseAction;

@ParentPackage(value = "json-default") // 应用全局包
@Scope("prototype")
@Namespace(value = "/admin")
@Action(results = { @Result(name = "index",location = "/WEB-INF/jsp/admin/index.jsp"),
        @Result(name = "json",type = "json",params = { "root", "resultMap"/* ,"excludeProperties","^.*majors$" */ }) })
public class SchoolAction extends BaseAction<Object, Serializable> {

    @Override
    public BaseService<Object, Serializable> getService(){
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getObject(){
        // TODO Auto-generated method stub
        return null;
    }

    @Autowired
    BranchService branchService;

    @Autowired
    MajorService majorService;

    @Autowired
    CollectiveService collectiveService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    Branch branch;
    Major  major;

    Map<String, Object> resultMap;

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

    public String branchList() throws IOException{
        resultMap = new TreeMap<String, Object>();
        List<Branch> list = branchService.getList();
        resultMap.put("data",list);
        return "json";
    }

    public String saveBranch(){
        resultMap = new TreeMap<String, Object>();
        String result = (String) branchService.save(branch);
        if ("".equals(result)) {
            resultMap.put("result","defailed");
        } else {
            resultMap.put("result","success");
        }
        return "json";
    }

    public String updateBranch(){
        resultMap = new TreeMap<String, Object>();
        branchService.update(branch);
        resultMap.put("result","success");
        return "json";
    }

    public String MajorList(){
        resultMap = new TreeMap<String, Object>();
        List<Major> list = majorService.getList();
        resultMap.put("data",list);
        return "json";
    }

    public String index(){
        return "index";
    }

    public String saveMajor(){
        resultMap = new TreeMap<String, Object>();
        String result = (String) majorService.save(major);
        if ("".equals(result)) {
            resultMap.put("result","defailed");
        } else {
            resultMap.put("result","success");
        }
        return "json";
    }

    public String updateMajor(){
        resultMap = new TreeMap<String, Object>();
        majorService.update(major);
        resultMap.put("result","success");
        return "json";
    }
}

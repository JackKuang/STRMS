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
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value = "all") // 应用全局包
@Scope("prototype")
@Namespace(value = "/admin")
@Action(results = { @Result(name = "index",location = "/WEB-INF/jsp/admin/index.jsp")/*,
        @Result(name = "json",type = "json",params = { "root", "resultMap" ,"excludeProperties","^.*majors$"  })*/ })
public class SchoolAction extends ActionSupport {
    String flag;
    public String index(){
        return flag;        
    }
    
    public String getFlag(){
        return flag;
    }
    
    public void setFlag(String flag){
        this.flag = flag;
    }
}



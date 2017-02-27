package com.hurenjieee.action;

import java.io.Serializable;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Admin;
import com.hurenjieee.service.AdminService;
import com.hurenjieee.service.BaseService;
import com.hurenjieee.util.BaseAction;

@ParentPackage(value = "all") // 应用全局包
@Scope("prototype")
@Namespace(value = "/admin")
@Action(results = { @Result(name = "index",location = "/WEB-INF/jsp/admin/index.jsp") })
public class AdminAction extends BaseAction<Admin, String> {

    public String index(){
        return "index";
    }

    @Override
    public BaseService<Admin, String> getService(){
        // TODO Auto-generated method stub
        return adminService;
    }

    @Override
    public Admin getObject(){
        // TODO Auto-generated method stub
        return admin == null ? new Admin() : admin;
    }

    @Autowired
    AdminService adminService;

    Admin admin;
}

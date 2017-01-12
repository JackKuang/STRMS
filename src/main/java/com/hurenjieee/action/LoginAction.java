package com.hurenjieee.action;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.type.StringRepresentableType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import com.hurenjieee.entity.Admin;
import com.hurenjieee.entity.Student;
import com.hurenjieee.entity.Teacher;
import com.hurenjieee.service.AdminService;
import com.hurenjieee.service.StudentService;
import com.hurenjieee.service.TeacherService;
import com.hurenjieee.util.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 登录模块不需要继承BaseAction，可以直接使用ActionSupport
 * 
 * @author Administrator
 */

/**
 *@Description:TODO(这里用一句话描述这个类的作用)
 *@Author:梅富桥
 *@Since:2017年1月12日下午4:14:57  
 */
@ParentPackage(value = "all") // 应用全局包
@Scope("prototype")
@Action(results = { @Result(name = "success-admin",type = "redirectAction",location = "admin/admin!index.action"),
        @Result(name = "success-teacher",type = "redirectAction",location = "teacher/teacher!index.action"),
        @Result(name = "success-student",type = "redirectAction",location = "student/student!index.action"),
        @Result(name = "toLogin",location = "/WEB-INF/jsp/login.jsp") })
public class LoginAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    @Autowired
    AdminService adminService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;
    private String userName;
    private String password;
    private String userType;

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getUserType(){
        return userType;
    }

    public void setUserType(String userType){
        this.userType = userType;
    }

    /**
     *@Description: TODO(这里用一句话描述这个方法的作用) 
     *@Author: 梅富桥
     *@Since: 2017年1月12日下午4:18:32
     *@return
     */
    public String login(){
        // ActionContext是Action执行时的上下文
        // ServletActionContext提供直接与Servlet容器交互的途径
        String result = "toLogin";
        ActionContext actionContext = ActionContext.getContext ();
        Map<String, Object> sessionMap = actionContext.getSession ();
        // 清理Session
        sessionMap.clear ();
        // 获取Request
        HttpServletRequest request = ServletActionContext.getRequest ();
        // 学生登录
        if ("student".equals (userType)) {
            Student student = studentService.getStudentByUsernameAndPassword (userName, password);
            if (student != null) {
                sessionMap.put ("userType", "student");
                sessionMap.put ("student", student);
                result = "success-admin";
            } else {
                request.setAttribute ("wrong", 1);
                request.setAttribute ("userType", userType);
                result = "toLogin";
            }
        }
        // 教师登录
        else if ("teacher".equals (userType)) {
            Teacher teacher = teacherService.getTeacherByUsernameAndPassword (userName, password);
            if (teacher != null) {
                sessionMap.put ("userType", "teacher");
                sessionMap.put ("student", teacher);
                result = "success-admin";
            } else {
                request.setAttribute ("wrong", 1);
                request.setAttribute ("type", userType);
                result = "toLogin";
            }
        }
        // 管理员登录
        else if ("admin".equals (userType)) {
            Admin admin = adminService.getAdminByUserNameAndPassword (userName, password);
            if (admin != null) {
                sessionMap.put ("userType", "admin");
                sessionMap.put ("admin", admin);
                result = "success-admin";
            } else {
                request.setAttribute ("wrong", 1);
                request.setAttribute ("type", userType);
                result = "toLogin";
            }
        }
        return result;
    }

    public String toLogin(){
        return "toLogin";
    }
}

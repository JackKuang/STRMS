package com.hurenjieee.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Admin;
import com.hurenjieee.entity.Student;
import com.hurenjieee.entity.Teacher;
import com.hurenjieee.service.AdminService;
import com.hurenjieee.service.StudentService;
import com.hurenjieee.service.TeacherService;
import com.hurenjieee.util.Md5AndSha;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 * @Description: TODO
 * @Author: JackKuang
 * @Since: 2017年4月5日上午9:03:44  
 */
@ParentPackage(value = "all") // 应用全局包
@Scope("prototype")
@Action(results = { @Result(name = "success-admin",type = "redirectAction",location = "admin/admin!index.action"),
        @Result(name = "success-teacher",type = "redirectAction",location = "teacher/teacher!index.action"),
        @Result(name = "success-student",type = "redirectAction",location = "student/student!index.action"),
        @Result(name = "toLogin",location = "/WEB-INF/jsp/common/login.jsp") })
public class LoginAction extends ActionSupport {

    @Autowired
    AdminService   adminService;

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

    public String login(){
        String result = "toLogin";
        // 清理Session

        ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> sessionMap = actionContext.getSession();
        sessionMap.clear();
        Map<String, Object> request = (Map<String, Object>) actionContext.get("request");
        // MD5加密
        //System.out.println( Md5AndSha.convertMD5("1"));
        //c4ca4238a0b923820dcc509a6f75849b
        password = Md5AndSha.convertMD5(password);

        // 学生登录
        if ("student".equals(userType)) {
            Student student = studentService.getStudentByUsernameAndPassword(userName,password);
            if (student != null) {
                sessionMap.put("userType","student");
                sessionMap.put("student",student);
                result = "success-student";
            } else {
                request.put("wrong",1);
                request.put("userType",userType);
                result = "toLogin";
            }
        }
        // 教师登录
        else if ("teacher".equals(userType)) {
            Teacher teacher = teacherService.getTeacherByUsernameAndPassword(userName,password);
            if (teacher != null) {
                sessionMap.put("userType","teacher");
                sessionMap.put("teacher",teacher);
                result = "success-teacher";
            } else {
                request.put("wrong",1);
                request.put("type",userType);
                result = "toLogin";
            }
        }
        // 管理员登录
        else if ("admin".equals(userType)) {
            Admin admin = adminService.getAdminByUserNameAndPassword(userName,password);
            if (admin != null) {
                sessionMap.put("userType","admin");
                sessionMap.put("admin",admin);
                result = "success-admin";
            } else {
                request.put("wrong",1);
                request.put("type",userType);
                result = "toLogin";
            }
        }
        password = "";
        return result;
    }

    public String toLogin(){
        return "toLogin";
    }
    
    /**
     * @Description: 清理sesson后推出
     * @Author: JackKuang
     * @Since: 2017年4月5日上午9:03:48
     * @return
     */
    public String exit(){
        ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> sessionMap = actionContext.getSession();
        String userType = (String)sessionMap.get("userType");
        sessionMap.remove(userType);
        sessionMap.remove("userType");
        return "toLogin";
    }
    
}

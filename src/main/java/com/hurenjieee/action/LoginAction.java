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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value = "all") // 应用全局包
@Scope("prototype")
@Action(results = { @Result(name = "success-admin", type = "redirectAction", location = "admin/admin!index.action"),
		@Result(name = "success-teacher", type = "redirectAction", location = "teacher/teacher!index.action"),
		@Result(name = "success-student", type = "redirectAction", location = "student/student!index.action"),
		@Result(name = "toLogin", location = "/WEB-INF/jsp/common/login.jsp") })
public class LoginAction extends ActionSupport {

	@Autowired
	AdminService adminService;

	@Autowired
	TeacherService teacherService;

	@Autowired
	StudentService studentService;
	private String userName;
	private String password;
	private String userType;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String login() {
		String result = "toLogin";
		// 清理Session

		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> sessionMap = actionContext.getSession();
		sessionMap.clear();
		Map<String, Object> request = (Map<String, Object>) actionContext.get("request");

		// MD5加密
		// password = Md5AndSha.convertMD5(password);

		// 学生登录
		if ("student".equals(userType)) {
			Student student = studentService.getStudentByUsernameAndPassword(userName, password);
			if (student != null) {
				sessionMap.put("userType", "student");
				sessionMap.put("student", student);
				result = "success-admin";
			} else {
				request.put("wrong", 1);
				request.put("userType", userType);
				result = "toLogin";
			}
		}
		// 教师登录
		else if ("teacher".equals(userType)) {
			Teacher teacher = teacherService.getTeacherByUsernameAndPassword(userName, password);
			if (teacher != null) {
				sessionMap.put("userType", "teacher");
				sessionMap.put("student", teacher);
				result = "success-admin";
			} else {
				request.put("wrong", 1);
				request.put("type", userType);
				result = "toLogin";
			}
		}
		// 管理员登录
		else if ("admin".equals(userType)) {
			Admin admin = adminService.getAdminByUserNameAndPassword(userName, password);
			if (admin != null) {
				sessionMap.put("userType", "admin");
				sessionMap.put("admin", admin);
				result = "success-admin";
			} else {
				request.put("wrong", 1);
				request.put("type", userType);
				result = "toLogin";
			}
		}
		password = "";
		return result;
	}

	public String toLogin() {
		return "toLogin";
	}
}

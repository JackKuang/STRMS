package com.hurenjieee.action;

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
import com.hurenjieee.util.CRUDActionSupport;

@ParentPackage(value = "all") // 应用全局包
@Scope("prototype")
@Action(results = { @Result(name = "success-admin", type = "redirectAction", location = "admin/admin!index.action"),
		@Result(name = "success-teacher", type = "redirectAction", location = "teacher/teacher!index.action"),
		@Result(name = "success-student", type = "redirectAction", location = "student/student!index.action"),
		@Result(name = "toLogin", location = "/WEB-INF/jsp/login.jsp") })
public class LoginAction extends CRUDActionSupport<Object> {

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
		getSessionMap().clear();
		// 学生登录
		if ("student".equals(userType)) {
			Student student = studentService.getStudentByUsernameAndPassword(userName, password);
			if (student != null) {
				getSessionMap().put("userType", "student");
				getSessionMap().put("student", student);
				result = "success-admin";
			} else {
				getRequest().setAttribute("wrong", 1);
				getRequest().setAttribute("userType", userType);
				result = "toLogin";
			}
		}
		// 教师登录
		else if ("teacher".equals(userType)) {
			Teacher teacher = teacherService.getTeacherByUsernameAndPassword(userName, password);
			if (teacher != null) {
				getSessionMap().put("userType", "teacher");
				getSessionMap().put("student", teacher);
				result = "success-admin";
			} else {
				getRequest().setAttribute("wrong", 1);
				getRequest().setAttribute("type", userType);
				result = "toLogin";
			}
		}
		// 管理员登录
		else if ("admin".equals(userType)) {
			Admin admin = adminService.getAdminByUserNameAndPassword(userName, password);
			if (admin != null) {
				getSessionMap().put("userType", "admin");
				getSessionMap().put("admin", admin);
				result = "success-admin";
			} else {
				getRequest().setAttribute("wrong", 1);
				getRequest().setAttribute("type", userType);
				result = "toLogin";
			}
		}
		return result;
	}

	public String toLogin() {
		return "toLogin";
	}
}

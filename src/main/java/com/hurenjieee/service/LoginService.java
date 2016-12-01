package com.hurenjieee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurenjieee.dao.LoginDao;
import com.hurenjieee.entity.Student;

@Service
public class LoginService {
	
	@Autowired
	LoginDao loginDao;
	
	public boolean login(String name,String password){
		if("admin".equals(name) && "123".equals(password))
			return true;
		else
			return false;
	}
	public void save(Student student){
		loginDao.save(student);
	}
}

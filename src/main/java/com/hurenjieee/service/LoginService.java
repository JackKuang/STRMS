package com.hurenjieee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurenjieee.dao.LoginDao;
import com.hurenjieee.entity.Userr;

@Service
public class LoginService {
	
	@Autowired
	LoginDao loginDao;
	
	public Userr login(String name,String password){
		return loginDao.selectByNameAndPassword(name,password);
	}
}

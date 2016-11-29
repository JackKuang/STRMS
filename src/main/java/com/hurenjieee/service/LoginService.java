package com.hurenjieee.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	public boolean login(String name,String password){
		if("admin".equals(name) && "123".equals(password))
			return true;
		else
			return false;
	}
}

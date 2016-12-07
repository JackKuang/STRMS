package com.hurenjieee.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurenjieee.dao.LoginDao;
import com.hurenjieee.entity.Userr;
import com.hurenjieee.util.BaseDao;

@Service
public class LoginService {
	
	@Autowired
	LoginDao loginDao;
	
	
	/*
	 * 这串代码可以实现Service和Dao层的整合在一起,把Dao层的代码拉到Service中。
	 * 因为自定义的Dao也是继承于BaseDao，
	 * 但是根据实际业务逻辑，还是选择了Dao和Service分离
	 * 
	private BaseDao<Userr,Integer> loginDao = new BaseDao<Userr,Integer>();
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		loginDao.setSessionFactory(sessionFactory);
	}
	*/
	
	public Userr login(String name,String password){
		return loginDao.selectByNameAndPassword(name, password);
	}
}

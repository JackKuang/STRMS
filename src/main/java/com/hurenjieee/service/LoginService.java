package com.hurenjieee.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurenjieee.entity.Userr;
import com.hurenjieee.util.BaseDao;

@Service
@Transactional
public class LoginService extends BaseService<Userr,String>{
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		BaseDao<Userr,String> dao = new BaseDao<Userr,String>();
		dao.setSessionFactory(sessionFactory);
		setDao(dao);
	}
	public boolean login(String userName,String passWord){
		List<Userr> list=getDao().getListByHQL("from Userr u where u.userName = '" + userName + "' and u.passWord = '" + passWord+"'");
		if(list.size()==1)
			return true;
		return false;
	}
}

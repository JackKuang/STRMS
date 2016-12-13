package com.hurenjieee.service;

import org.hibernate.SessionFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurenjieee.entity.Dictionary;
import com.hurenjieee.entity.Userr;
import com.hurenjieee.util.BaseDao;

@Service
public class DictionaryService extends BaseService<Dictionary, String>{
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		BaseDao<Dictionary,String> dao = new BaseDao<Dictionary,String>();
		dao.setSessionFactory(sessionFactory);
		setDao(dao);
	}
}

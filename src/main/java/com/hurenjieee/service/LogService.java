package com.hurenjieee.service;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurenjieee.entity.Log;
import com.hurenjieee.util.BaseDao;

@Service
@Transactional
public class LogService extends BaseService<Log, String> {
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		BaseDao<Log,String> dao = new BaseDao<Log,String>();
		dao.setSessionFactory(sessionFactory);
		setDao(dao);
	}
}

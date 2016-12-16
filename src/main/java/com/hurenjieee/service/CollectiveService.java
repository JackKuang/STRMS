package com.hurenjieee.service;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurenjieee.entity.Collective;
import com.hurenjieee.util.BaseDao;

@Service
@Transactional
public class CollectiveService extends BaseService<Collective, String> {
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		BaseDao<Collective,String> dao = new BaseDao<Collective,String>();
		dao.setSessionFactory(sessionFactory);
		setDao(dao);
	}
}

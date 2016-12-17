package com.hurenjieee.service;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurenjieee.entity.Branch;
import com.hurenjieee.util.BaseDao;

@Service
@Transactional
public class BranchService extends BaseService<Branch, String> {
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		BaseDao<Branch,String> dao = new BaseDao<Branch,String>();
		dao.setSessionFactory(sessionFactory);
		setDao(dao);
	}
}

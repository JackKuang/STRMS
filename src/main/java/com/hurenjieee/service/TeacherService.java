package com.hurenjieee.service;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurenjieee.entity.Teacher;
import com.hurenjieee.util.BaseDao;

@Service
@Transactional
public class TeacherService extends BaseService<Teacher, String> {

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		BaseDao<Teacher,String> dao = new BaseDao<Teacher,String>();
		dao.setSessionFactory(sessionFactory);
		setDao(dao);
	}
	
	public boolean login(String userName,String password){
		Teacher teacher=getDao().getByHQL("from Teacher t where t.teaName = '" + userName + "' and t.teaPassword = '" + password+"'");
		return teacher!=null;
	}
}

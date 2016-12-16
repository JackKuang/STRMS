package com.hurenjieee.service;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurenjieee.entity.Student;
import com.hurenjieee.util.BaseDao;

@Service
@Transactional
public class StudentService extends BaseService<Student, String> {

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		BaseDao<Student,String> dao = new BaseDao<Student,String>();
		dao.setSessionFactory(sessionFactory);
		setDao(dao);
	}

	public boolean login(String userName,String password){
		Student student = getDao().getByHQL("from Student s where s.stuName = '" + userName + "' and s.stuPassword = '" + password+"'");
		return student!=null;
	}
}

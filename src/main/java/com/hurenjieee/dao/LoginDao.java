package com.hurenjieee.dao;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hurenjieee.entity.Student;

@Repository
public class LoginDao {
//public class LoginDao extends BaseDao<Student,Integer>{
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		// 需要开启事物，才能得到CurrentSession
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public void save(Student student)
    {
        System.out.println(getSession().save(student));
    }

}

package com.hurenjieee.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hurenjieee.entity.Student;

@Repository
@Transactional
public class LoginDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public void save(Student student)
    {
        System.out.println(getSession().save(student));
    }

}

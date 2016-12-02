package com.hurenjieee.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hurenjieee.entity.Student;
import com.hurenjieee.util.BaseDao;

@Repository
public class LoginDao extends BaseDao<Student,Integer>{
	
	@Transactional
	public void save(Student student)
    {
        System.out.println(getSession().save(student));
    }

}

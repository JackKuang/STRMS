package com.hurenjieee.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.hurenjieee.entity.Student;

@Repository
@Transactional
public class StudentDao extends BaseDao<Student, String> {

}

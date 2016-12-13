package com.hurenjieee.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.hurenjieee.entity.Teacher;

@Repository
@Transactional
public class TeacherDao extends BaseDao<Teacher, String> {

}

package com.hurenjieee.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.hurenjieee.entity.Admin;

@Repository
@Transactional
public class AdminDao extends BaseDao<Admin, String> {

}

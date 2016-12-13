package com.hurenjieee.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.hurenjieee.entity.Major;

@Repository
@Transactional
public class MajorDao extends BaseDao<Major, String> {

}

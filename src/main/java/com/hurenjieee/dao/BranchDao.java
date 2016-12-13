package com.hurenjieee.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.hurenjieee.entity.Branch;

@Repository
@Transactional
public class BranchDao extends BaseDao<Branch, String> {

}

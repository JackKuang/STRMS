package com.hurenjieee.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.hurenjieee.entity.Collective;

@Repository
@Transactional
public class LogDao extends BaseDao<Collective, String> {

}

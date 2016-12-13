package com.hurenjieee.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.hurenjieee.entity.Log;

@Repository
@Transactional
public class CollectiveDao extends BaseDao<Log, String> {

}

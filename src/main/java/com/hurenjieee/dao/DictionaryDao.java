package com.hurenjieee.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.hurenjieee.entity.Dictionary;
import com.hurenjieee.util.BaseDao;

@Repository
@Transactional
public class DictionaryDao extends BaseDao<Dictionary, String> {

}

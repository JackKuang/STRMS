package com.hurenjieee.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.hurenjieee.entity.Dictionary;

@Repository
@Transactional
public class DictionaryDao extends BaseDao<Dictionary, String> {
	
}

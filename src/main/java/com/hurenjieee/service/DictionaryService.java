package com.hurenjieee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurenjieee.dao.DictionaryDao;
import com.hurenjieee.entity.Dictionary;

@Service
public class DictionaryService {
	
	@Autowired
	DictionaryDao dictionaryDao;
	
	public String insert(Dictionary dictionary){
		return dictionaryDao.save(dictionary);		
	}
	
	public List<Dictionary> getList(){
		return dictionaryDao.getListByHQL("");
	}
	
	public void update(Dictionary dictionary){
		dictionaryDao.update(dictionary);
	}
	
	public void delete(String uuid){
		dictionaryDao.deleteById(uuid);
	}

}

package com.hurenjieee.util;

import java.io.Serializable;
import java.util.List;

import com.hurenjieee.util.BaseDao;

public class BaseService<T,ID extends Serializable>{
	
	private BaseDao<T, ID> dao;

	public BaseDao<T, ID> getDao() {
		return dao;
	}

	public void setDao(BaseDao<T, ID> dao) {
		this.dao = dao;
	}
	
	public List<T> getListByHQL(String hqlString, Object... values){
		return getDao().getListByHQL(hqlString, values);
	}
	public T get(ID id){
		return getDao().get(id);
	}
}

package com.hurenjieee.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.hurenjieee.entity.Userr;
import com.hurenjieee.util.BaseDao;

@Repository
@Transactional
public class LoginDao extends BaseDao<Userr, Integer>{
	
	public Userr selectByNameAndPassword(String userName, String password) {
		String hql = "from Userr u where u.userName = '" + userName + "' and u.passWord = '" + password+"'";
		List<Userr> list = getListByHQL(hql);
		if (list.size() == 0)
			return null;
		return list.get(0);
	}
	public void save(Userr userr){
		super.save(userr);
	}
}

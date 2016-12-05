package com.hurenjieee.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hurenjieee.entity.Userr;

@Repository
public class LoginDao {

		@Autowired
		SessionFactory sessionFactory;

	public Userr selectByNameAndPassword(String userName, String password) {
//		Configuration cfg = new Configuration();
//		SessionFactory sessionFactory = cfg.configure().buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();
		String hql = "from Userr u where u.userName = '" + userName + "' and u.passWord = '" + password+"'";
		Query query = session.createQuery(hql);
		List<Userr> list = query.list();
		if (list.size() == 0)
			return null;
		return list.get(0);
	}
}

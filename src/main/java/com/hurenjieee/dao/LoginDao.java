package com.hurenjieee.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.hurenjieee.entity.Userr;

@Repository
public class LoginDao {

	public Userr login(String userName, String password) {
		Configuration cfg = new Configuration();
		SessionFactory sf = cfg.configure().buildSessionFactory();

		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Userr u where u.userName = '" + userName + "' and u.passWord = '" + password+"'";
		Query query = session.createQuery(hql);
		List<Userr> list = query.list();
		transaction.commit();
		session.close();
		sf.close();
		if (list.size() == 0)
			return null;
		return list.get(0);
	}
}

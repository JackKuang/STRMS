package com.hurenjieee.service;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurenjieee.entity.Relation;
import com.hurenjieee.util.BaseDao;

@Service
@Transactional
public class RelationService extends BaseService<Relation, Long> {

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        BaseDao<Relation, Long> dao = new BaseDao<Relation, Long>();
        dao.setSessionFactory(sessionFactory);
        setDao(dao);
    }

	public void deleteByTeaId(Relation relation) {
		// TODO Auto-generated method stub
		 String hql = "Delete FROM Relation r Where r.relTeaId = "+ relation.getRelTeaId() ;     
	     getDao().queryHql(hql); 
	}

}

package com.hurenjieee.service;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurenjieee.entity.Branch;
import com.hurenjieee.util.BaseDao;

@Service
@Transactional
public class BranchService extends BaseService<Branch, Long> {

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        BaseDao<Branch, Long> dao = new BaseDao<Branch, Long>();
        dao.setSessionFactory(sessionFactory);
        setDao(dao);
    }
    
    public Branch selectByBraId(Long braId){
        return getDao().getByHQL("from Branch b where b.braId = ?",braId);
    }

    public Branch selectByBraName(String braName){
        return getDao().getByHQL("from Branch b where b.braName = ?",braName);
    }
}

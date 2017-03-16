package com.hurenjieee.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurenjieee.entity.Major;
import com.hurenjieee.util.BaseDao;

@Service
@Transactional
public class MajorService extends BaseService<Major, Long> {

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        BaseDao<Major, Long> dao = new BaseDao<Major, Long>();
        dao.setSessionFactory(sessionFactory);
        setDao(dao);
    }
    
    public List<Major> listByMajBraId(Major major){
    	    return getDao().getListByHQL("from Major m where m.majBraId = ?", major.getMajBraId());
    }
}

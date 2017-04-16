package com.hurenjieee.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurenjieee.entity.Comment;
import com.hurenjieee.util.BaseDao;

@Service
@Transactional
public class CommentService extends BaseService<Comment, Long> {

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        BaseDao<Comment, Long> dao = new BaseDao<Comment, Long>();
        dao.setSessionFactory(sessionFactory);
        setDao(dao);
    }
    
    public List<Comment> getListByComResId(Long comResId){
        return getDao().getListByHQL("from Comment c where c.comResId = ? order by c.comDate desc",comResId);
    }
}

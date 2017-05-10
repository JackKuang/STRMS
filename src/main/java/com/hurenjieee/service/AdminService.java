package com.hurenjieee.service;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurenjieee.entity.Admin;
import com.hurenjieee.util.BaseDao;

@Service
@Transactional
public class AdminService extends BaseService<Admin, Long> {

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        BaseDao<Admin, Long> dao = new BaseDao<Admin, Long>();
        dao.setSessionFactory(sessionFactory);
        setDao(dao);
    }

    public Admin getAdminByUserNameAndPassword(String userName,String password){
        return getDao().getByHQL("from Admin a where a.admName = ? and a.admPassword = ? ",userName,password);
    }

	public void updatePassword(Admin admin) {
		// TODO Auto-generated method stub
		String hql = "update Admin a set a.admPassword = '"+admin.getAdmPassword()+"' where a.admId =" +admin.getAdmId();
		getDao().queryHql(hql);
	}
}

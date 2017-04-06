package com.hurenjieee.service;

import java.text.Normalizer.Form;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurenjieee.entity.Resource;
import com.hurenjieee.util.BaseDao;

@Service
@Transactional
public class ResourceService extends BaseService<Resource, Long> {

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        BaseDao<Resource, Long> dao = new BaseDao<Resource, Long>();
        dao.setSessionFactory(sessionFactory);
        setDao(dao);
    }

    public List<Resource> getListByReaParIdAndReaTeaId(Resource resource){
        return getDao().getListByHQL("from Resource r where r.resParId = ? and r.reaTeaId = ? and r.reaName like ? ",resource.getResParId(),resource.getResTeaId(),"%"+resource.getResName()+"%");
    }
    
    public List<Resource> getListByReaParIdAndReaTeaIdAndApprove(Resource resource){
        return getDao().getListByHQL("from Resource r where r.resState = 10 and r.resParId = ? and r.reaTeaId = ? and r.reaName like ? ",resource.getResParId(),resource.getResTeaId(),"%"+resource.getResName()+"%");
    }

    public void updateCustom(Resource resource){
        StringBuilder sql = new StringBuilder();
        sql.append("update resource set ");
        if (resource.getResName() != null && !"".equals(resource.getResName())) sql.append("res_name = '" + resource.getResName() + "', ");
        if (resource.getResState() != null && !"".equals(resource.getResState())) sql.append("res_state = " + resource.getResState() + ", ");
        if (resource.getResParId() != null && !"".equals(resource.getResParId())) sql.append("res_par_id = " + resource.getResParId() + ",");
        if (resource.getResAdmId() != null && !"".equals(resource.getResAdmId())) sql.append("res_adm_id = " + resource.getResParId() + ",");
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" where res_id = " + resource.getResId() + " ");
        if (resource.getResUuid() != null && !"".equals(resource.getResUuid())) sql.append("and res_uuid = '" + resource.getResUuid() + "' ");
        if (resource.getResTeaId() != null && !"".equals(resource.getResTeaId())) sql.append("and res_tea_id = " + resource.getResTeaId() + " ");
        getDao().querySql(sql.toString());
    }

}

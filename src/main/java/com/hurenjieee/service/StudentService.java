package com.hurenjieee.service;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurenjieee.entity.Student;
import com.hurenjieee.util.BaseDao;
import com.hurenjieee.util.Md5AndSha;

@Service
@Transactional
public class StudentService extends BaseService<Student, Long> {

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        BaseDao<Student, Long> dao = new BaseDao<Student, Long>();
        dao.setSessionFactory(sessionFactory);
        setDao(dao);
    }

    public Student getStudentByUsernameAndPassword(String userName,String password){
        return getDao().getByHQL("from Student s where s.stuNo = '" + userName + "' and s.stuPassword = '" + password + "'");
    }
    
    public Student selectByStuId(Long stuId){
        return getDao().getByHQL("from Student s where s.stuId = ? ",stuId);
    }

    public void saveOrUpdate(Student student){
    	if(student.getStuId()==0 || "".equals(student.getStuPassword()))
    		student.setStuPassword(Md5AndSha.convertMD5("123456"));
        getDao().saveOrUpdate(student);
    }
}

package com.hurenjieee.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Branch;
import com.hurenjieee.entity.Major;
import com.hurenjieee.entity.Teacher;
import com.hurenjieee.service.BaseService;
import com.hurenjieee.service.MajorService;
import com.hurenjieee.service.TeacherService;
import com.hurenjieee.util.BaseAction;

@ParentPackage(value = "json") // 应用全局包
@Scope("prototype")
@Namespace(value = "/admin")
@Action(results = { @Result(name = "json",type = "json",params = { "root", "resultMap" }) ,
        @Result(name = "jsonSon",type = "json",params = { "root", "resultMapSon" }) })
public class TeacherOperateAction extends BaseAction<Teacher, Long> {

        
    @Autowired
    TeacherService teacherService;

    Teacher        teacher;

    Map<String, Object> resultMapSon;
    
    @Override
    public BaseService<Teacher, Long> getService(){
        // TODO Auto-generated method stub
        return teacherService;
    }

    @Override
    public Teacher getObject(){
        // TODO Auto-generated method stub
        return teacher == null ? new Teacher() : teacher;
    }
    
    
    
    
    public Teacher getTeacher(){
        return teacher;
    }

    
    public void setTeacher(Teacher teacher){
        this.teacher = teacher;
    }

    public Map<String, Object> getResultMapSon(){
        return resultMapSon;
    }

    
    public void setResultMapSon(Map<String, Object> resultMapSon){
        this.resultMapSon = resultMapSon;
    }
    
}

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
import com.hurenjieee.util.PageResults;

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
    
    private Integer pageNo;
    
    private Integer pageSize;
    
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

    
    public Integer getPageNo(){
        return pageNo;
    }

    
    public void setPageNo(Integer pageNo){
        this.pageNo = pageNo;
    }

    
    public Integer getPageSize(){
        return pageSize;
    }

    
    public void setPageSize(Integer pageSize){
        this.pageSize = pageSize;
    }

    public String page(){
        try {
            resultMapSon = new HashMap<String, Object>();
            resultMapSon = new HashMap<String, Object>();
            String hql = "From Teacher t";
            if(teacher!= null && !"".equals(teacher.getTeaName()))
                hql = hql + "t.teaNo like  '%"+teacher.getTeaName()+ "%' or t.teaName like  '%"+teacher.getTeaName()+ "%'";
            PageResults<Teacher> pageResults = getService().getListByPage(hql,hql,pageNo,pageSize);
            resultMapSon.put("result","success");
            resultMapSon.put("content",pageResults);
        } catch (Exception e) {
            e.printStackTrace();
            resultMapSon.put("result","fail");
            resultMapSon.put("reason","未知错误！");
        }
        return "jsonSon";
    }
    
    
}

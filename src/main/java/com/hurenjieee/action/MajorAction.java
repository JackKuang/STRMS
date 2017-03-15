package com.hurenjieee.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Major;
import com.hurenjieee.service.BaseService;
import com.hurenjieee.service.MajorService;
import com.hurenjieee.util.BaseAction;

@ParentPackage(value = "json") // 应用全局包
@Scope("prototype")
@Namespace(value = "/admin")
@Action(results = { @Result(name = "json",type = "json",params = { "root", "resultMap" }) })
public class MajorAction extends BaseAction<Major, Long> {

    @Autowired
    MajorService majorService;

    Major major;

    @Override
    public BaseService<Major, Long> getService(){
        // TODO Auto-generated method stub
        return majorService;
    }

    @Override
    public Major getObject(){
        // TODO Auto-generated method stub
        return major == null ? new Major() : major;
    }

    
    public Major getMajor(){
        return major;
    }

    
    public void setMajor(Major major){
        this.major = major;
    }
    
    public String listByMajBraId(){
    	try {
    		getResultMap();
    		List<Major> list = majorService.listByMajBraId(major);
    		getResultMap().put("result", "success");
    		getResultMap().put("content", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    	return "json";
    }

}

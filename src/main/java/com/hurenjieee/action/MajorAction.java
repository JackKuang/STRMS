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
import com.hurenjieee.service.BaseService;
import com.hurenjieee.service.MajorService;
import com.hurenjieee.util.BaseAction;

@ParentPackage(value = "json") // 应用全局包
@Scope("prototype")
@Namespace(value = "/admin")
@Action(results = { @Result(name = "json",type = "json",params = { "root", "resultMap" }) ,
        @Result(name = "jsonSon",type = "json",params = { "root", "resultMapSon" }) })
public class MajorAction extends BaseAction<Major, Long> {

        
    @Autowired
    MajorService majorService;

    Major        major;

    Map<String, Object> resultMapSon;
    
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
    
    public Map<String, Object> getResultMapSon(){
        return resultMapSon;
    }

    
    public void setResultMapSon(Map<String, Object> resultMapSon){
        this.resultMapSon = resultMapSon;
    }

    public String listByMajBraId(){
        try {
            resultMapSon = new HashMap<String,Object>();
            List<Major> list = majorService.listByMajBraId(major);
            resultMapSon.put("result","success");
            resultMapSon.put("content",list);
        } catch (Exception e) {
            e.printStackTrace();
            resultMapSon.put("result","failed");
        }
        return "jsonSon";
    }
    
    public String getById(){
        try {
            resultMapSon = new HashMap<String, Object>();
            Major major2 = majorService.selectByMajId(major);
            resultMapSon.put("result","success");
            resultMapSon.put("content",major2);
        } catch (Exception e) {
            e.printStackTrace();
            resultMapSon.put("result","fail");
            resultMapSon.put("reason","未知错误！");
        }
        return "jsonSon";
    }
    
}

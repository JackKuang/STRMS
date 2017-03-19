package com.hurenjieee.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Collective;
import com.hurenjieee.entity.Major;
import com.hurenjieee.service.BaseService;
import com.hurenjieee.service.CollectiveService;
import com.hurenjieee.util.BaseAction;

@ParentPackage(value = "json") // 应用全局包
@Scope("prototype")
@Namespace(value = "/admin")
@Action(results = { @Result(name = "json",type = "json",params = { "root", "resultMap" }) ,
        @Result(name = "jsonSon",type = "json",params = { "root", "resultMapSon" }) })
public class CollectiveAction extends BaseAction<Collective, Long> {

    @Autowired
    CollectiveService collectiveService;

    Collective collective;
    
    Map<String, Object> resultMapSon;
    

    @Override
    public BaseService<Collective, Long> getService(){
        // TODO Auto-generated method stub
        return collectiveService;
    }

    @Override
    public Collective getObject(){
        // TODO Auto-generated method stub
        return collective == null ? new Collective() : collective;
    }

    
    public Collective getCollective(){
        return collective;
    }

    
    public void setCollective(Collective collective){
        this.collective = collective;
    }

    
    public Map<String, Object> getResultMapSon(){
        return resultMapSon;
    }

    
    public void setResultMapSon(Map<String, Object> resultMapSon){
        this.resultMapSon = resultMapSon;
    }

    public String getById(){
        try {
            resultMapSon = new HashMap<String, Object>();
            Collective collective2 = collectiveService.selectByColId(collective);
            resultMapSon.put("result","success");
            resultMapSon.put("content",collective2);
        } catch (Exception e) {
            e.printStackTrace();
            resultMapSon.put("result","fail");
            resultMapSon.put("reason","未知错误！");
        }
        return "jsonSon";
    }

}

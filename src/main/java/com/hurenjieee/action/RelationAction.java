package com.hurenjieee.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Relation;
import com.hurenjieee.entity.Teacher;
import com.hurenjieee.service.BaseService;
import com.hurenjieee.service.RelationService;
import com.hurenjieee.util.BaseAction;

@ParentPackage(value = "json") // 应用全局包
@Scope("prototype")
@Namespace(value = "/admin")
@Action(results = { @Result(name = "json",type = "json",params = { "root", "resultMap" }) ,
        @Result(name = "jsonSon",type = "json",params = { "root", "resultMapSon" }) })
public class RelationAction extends BaseAction<Relation, Long> {

    @Autowired
    RelationService relationService;

    Relation relation;

    String relColIds;
    
    Map<String, Object> resultMapSon;
    
    @Override
    public BaseService<Relation, Long> getService(){
        // TODO Auto-generated method stub
        return relationService;
    }

    @Override
    public Relation getObject(){
        // TODO Auto-generated method stub
        return relation == null ? new Relation() : relation;
    }

    
    public Relation getRelation(){
        return relation;
    }

    
    public void setRelation(Relation relation){
        this.relation = relation;
    }

    
    public Map<String, Object> getResultMapSon(){
        return resultMapSon;
    }

    
    public void setResultMapSon(Map<String, Object> resultMapSon){
        this.resultMapSon = resultMapSon;
    }

	public String getRelColIds() {
		return relColIds;
	}

	public void setRelColIds(String relColIds) {
		this.relColIds = relColIds;
	}

	public String saveMutiple(){
        try {
            resultMapSon = new HashMap<String, Object>();
            relationService.deleteByTeaId(relation);
            String[] strings = relColIds.split(",");
            for(String s:strings){
            	Relation r= new Relation();
            	r.setRelTeaId(relation.getRelTeaId());
            	r.setRelColId(Long.valueOf(s));
            	relationService.save(r);            	
            }
            resultMapSon.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            resultMapSon.put("result","fail");
            resultMapSon.put("reason","未知错误！");
        }
        return "jsonSon";
    }
       
}

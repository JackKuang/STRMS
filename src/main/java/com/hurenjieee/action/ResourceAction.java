package com.hurenjieee.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Resource;
import com.hurenjieee.entity.Teacher;
import com.hurenjieee.service.BaseService;
import com.hurenjieee.service.ResourceService;
import com.hurenjieee.util.BaseAction;

@ParentPackage(value = "json") // 应用全局包
@Scope("prototype")
@Action(results = { @Result(name = "json",type = "json",params = { "root", "resultMap" }) ,
        @Result(name = "jsonSon",type = "json",params = { "root", "resultMapSon" }) })
public class ResourceAction extends BaseAction<Resource, Long> {

    @Autowired
    ResourceService resourceService;

    Resource resource;

    Map<String, Object> resultMapSon;
    
    Map<String, Object> reqMap;
    
    @Override
    public BaseService<Resource, Long> getService(){
        // TODO Auto-generated method stub
        return resourceService;
    }

    @Override
    public Resource getObject(){
        // TODO Auto-generated method stub
        return resource == null ? new Resource() : resource;
    }

    public Resource getResource(){
        return resource;
    }
    
    public void setResource(Resource resource){
        this.resource = resource;
    }

    public Map<String, Object> getResultMapSon(){
        return resultMapSon;
    }

    public void setResultMapSon(Map<String, Object> resultMapSon){
        this.resultMapSon = resultMapSon;
    }
    

    
    public Map<String, Object> getReqMap(){
        return reqMap;
    }

    
    public void setReqMap(Map<String, Object> reqMap){
        this.reqMap = reqMap;
    }

    public String page(){
        try {
            resultMapSon = new HashMap<String, Object>();
            Map<String,Object> map = getSessionMap();
            Long resTeaId = ((Teacher) getSessionMap().get("teacher")).getTeaId();
            //获取到文件List
            resource.setResTeaId(resTeaId);
            List<Resource> resourceList = resourceService.getListByRea(resource);
            resultMapSon.put("result","success");
            resultMapSon.put("rows",resourceList);
            resultMapSon.put("path","path");
        } catch (Exception e) {
            e.printStackTrace();
            resultMapSon.put("result","fail");
            resultMapSon.put("reason","未知错误！");
        }
        return "jsonSon";
    }

    public String pageDownload(){
        try {
            resultMapSon = new HashMap<String, Object>();
            Map<String,Object> map = getSessionMap();
            Long resTeaId = ((Teacher) getSessionMap().get("teacher")).getTeaId();
            //获取到文件List
            resource.setResTeaId(resTeaId);
            List<Resource> resourceList = resourceService.getListByReaAndApprove(resource);
            resultMapSon.put("result","success");
            resultMapSon.put("rows",resourceList);
            resultMapSon.put("path","path");
        } catch (Exception e) {
            e.printStackTrace();
            resultMapSon.put("result","fail");
            resultMapSon.put("reason","未知错误！");
        }
        return "jsonSon";
    }
    
}

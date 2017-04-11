package com.hurenjieee.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Id;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Resource;
import com.hurenjieee.entity.Teacher;
import com.hurenjieee.service.BaseService;
import com.hurenjieee.service.ResourceService;
import com.hurenjieee.util.BaseAction;
import com.hurenjieee.util.Node;

@ParentPackage(value = "json") // 应用全局包
@Scope("prototype")
@Namespace(value = "/resource")
@Action(results = { @Result(name = "json",type = "json",params = { "root", "resultMap" }) ,
        @Result(name = "jsonSon",type = "json",params = { "root", "resultMapSon" }) })
public class ResourceAction extends BaseAction<Resource, Long> {

    @Autowired
    ResourceService resourceService;

    Resource resource;

    Map<String, Object> resultMapSon;
    
    Map<String, Object> reqMap;
    
    private File[] fff; //上传的文件
    private String[] fffFileName; //文件名称
    private String[] fffContentType; //文件类型
    
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
    
    
    public File[] getFff(){
        return fff;
    }

    
    public void setFff(File[] fff){
        this.fff = fff;
    }

    
    public String[] getFffFileName(){
        return fffFileName;
    }

    
    public void setFffFileName(String[] fffFileName){
        this.fffFileName = fffFileName;
    }

    
    public String[] getFffContentType(){
        return fffContentType;
    }

    
    public void setFffContentType(String[] fffContentType){
        this.fffContentType = fffContentType;
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
    

    public String update(){
        try {
            resultMapSon = new HashMap<String, Object>();
            resourceService.updateCustom(resource);
            resultMapSon.put("result","success");
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
    
    public String saveFiles(){
        try {
            resultMapSon = new HashMap<String, Object>();
            String realpath = ((Map<String,String>)getServletContext().getAttribute("prop")).get("filePath");
            if (fff != null) {
                File savedir=new File(realpath);
                if(!savedir.getParentFile().exists())
                    savedir.getParentFile().mkdirs();
                for(int i=0;i<fff.length;i++){
                    String uuid = UUID.randomUUID().toString();
                    File savefile = new File(savedir, uuid);
                    FileUtils.copyFile(fff[i], savefile);
                    Resource resource2 = new Resource();
                    resource2.setResTeaId(resource.getResTeaId());
                    resource2.setResName(fffFileName[i]);
                    resource2.setResParId(resource.getResParId());
                    resource2.setResUuid(uuid);
                    resource2.setResState(1);
                    resourceService.save(resource2);
                }
            }
            resultMapSon.put("message", "文件上传成功");
            resultMapSon.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            resultMapSon.put("result","fail");
            resultMapSon.put("reason","未知错误！");
        }
        return "jsonSon";
    }
    
    public String folderTree(){
        try {
            resultMapSon = new HashMap<String, Object>();
            if (fff != null) {
            }
            List<Resource> list =resourceService.getListByReaTeaId(resource);
            List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
            Map<String,Object> map = new HashMap<>();
            //所有resource放进map里面
            for(Resource r:list){
                Map<String,Object> mapTemp = new HashMap<String,Object>();
                mapTemp.put("id",r.getResId());
                mapTemp.put("text",r.getResName());
                mapTemp.put("parId",r.getResParId());
                map.put(String.valueOf(r.getResId()),mapTemp);
            }
            for(Resource r:list){
                if(r.getResParId()!=0){
                    Map<String,Object> mapTemp = (Map<String,Object>)map.get(String.valueOf(r.getResParId()));
                    List<Map> listTemp ;
                    if(!map.containsKey("nodes")){
                        listTemp = new ArrayList<Map>();
                    }else{
                        listTemp = (List<Map>)mapTemp.get("nodes");
                    }
                    Map<String,Object> mapTemp2 = new HashMap<String,Object>();
                    mapTemp2.put("id",r.getResId());
                    mapTemp2.put("text",r.getResName());
                    mapTemp2.put("parId",r.getResParId());
                    listTemp.add(mapTemp2);
                    mapTemp.put("nodes",listTemp);
                }
            }
            Set<String> keySet = map.keySet();  
            //有了Set集合就可以获取其迭代器，取值  
            Iterator<String> it = keySet.iterator();  
            while (it.hasNext())  
            {
                String s = it.next();
                Map<String,Object> mapTemp = (Map<String,Object>)map.get(s);
                if (0==(Long)mapTemp.get("parId")) {
                    result.add(mapTemp);
                }
            }  
//            List<Node> listNode = new ArrayList<Node>();
//            int i=0;
//            while(i<list.size()){
//                Node node = new Node();
//                Resource r =list.get(i);
//                node.setId(r.getResId());
//                node.setText(r.getResName());
//                listNode.add(node);
//                i++;
//            }
            resultMapSon.put("result","success");
            resultMapSon.put("content",result);
        } catch (Exception e) {
            e.printStackTrace();
            resultMapSon.put("result","fail");
            resultMapSon.put("reason","未知错误！");
        }
        return "jsonSon";
    }
}

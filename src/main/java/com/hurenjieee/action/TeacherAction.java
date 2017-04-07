package com.hurenjieee.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Resource;
import com.hurenjieee.entity.Teacher;
import com.hurenjieee.service.ResourceService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

// 这里的类都做action跳转
@ParentPackage(value = "all") // 应用全局包
@Scope("prototype")
@Namespace(value = "/teacher")
@Action(results = { @Result(name = "index",location = "/WEB-INF/jsp/teacher/index.jsp"),
        @Result(name = "systemDetail",location = "/WEB-INF/jsp/teacher/systemDetail.jsp"),
        @Result(name = "resourceManager",location = "/WEB-INF/jsp/teacher/resourceManager.jsp"),
        @Result(name = "messageManager",location = "/WEB-INF/jsp/teacher/messageManager.jsp")})
public class TeacherAction extends ActionSupport {

    String flag;
    
    Long resParId;
    
    List<Resource> resourceList;
    
    @Autowired
    ResourceService resourceService;

    public String index(){
        return "index";
    }

    public String getFlag(){
        return flag;
    }

    public void setFlag(String flag){
        this.flag = flag;
    }

    
    public Long getResParId(){
        return resParId;
    }

    
    public void setResParId(Long resParId){
        this.resParId = resParId;
    }
    
    public List<Resource> getResourceList(){
        return resourceList;
    }

    
    public void setResourceList(List<Resource> resourceList){
        this.resourceList = resourceList;
    }

    public String redirectPage(){
        if("index".equals(flag)){
            //登录首页信息查看
            return "systemDetail";
        }else if("resourceManager".equals(flag)){
            //资源管理
            //resourceManager();
            return "resourceManager";
        }else if("messageManager".equals(flag)){
            //消息管理
            return "messageManager";            
        }else{
            return flag;
        }
    }
    public void resourceManager(){
        ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> sessionMap = actionContext.getSession();
        Long resTeaId = ((Teacher)sessionMap.get("teacher")).getTeaId();
        //获取到文件List
        //resourceList = resourceService.getListByReaParIdAndReaTeaId(resParId,resTeaId);
        //获取到文件路径
    }
}

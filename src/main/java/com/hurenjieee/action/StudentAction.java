package com.hurenjieee.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;

// 这里的类都做action跳转
@ParentPackage(value = "all") // 应用全局包
@Scope("prototype")
@Namespace(value = "/student")
@Action(results = { @Result(name = "index",location = "/WEB-INF/jsp/student/index.jsp"),
        @Result(name = "resourceManager",location = "/WEB-INF/jsp/student/resourceManager.jsp"),
        @Result(name = "myResourceManager",location = "/WEB-INF/jsp/student/myResourceManager.jsp")})
public class StudentAction extends ActionSupport {

    String flag;

    public String index(){
        return "index";
    }

    public String getFlag(){
        return flag;
    }

    public void setFlag(String flag){
        this.flag = flag;
    }

    public String redirectPage(){
        if("index".equals(flag)){
            //登录首页信息查看
            return "systemDetail";
        }else if("resourceManager".equals(flag)){
            //资源管理
            //resourceManager();
            return "resourceManager";
        }else if("myResourceManager".equals(flag)){
            //班级资源浏览
            return "myResourceManager";            
        }else{
            return flag;
        }
    }
}

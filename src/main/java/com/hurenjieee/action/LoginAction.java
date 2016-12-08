package com.hurenjieee.action;


import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Userr;
import com.hurenjieee.service.LoginService;
import com.hurenjieee.util.CRUDActionSupport;
import com.opensymphony.xwork2.ActionContext;

@ParentPackage(value = "all")//应用全局包 
@Scope("prototype")
@Action(
		results={
		@Result(name = "success", location = "/WEB-INF/jsp/success.jsp"),
		@Result(name = "error", location = "/WEB-INF/jsp/error.jsp")
		}
)
public class LoginAction extends CRUDActionSupport<Userr>{  
    
    private static final long serialVersionUID = 1L;

    @Autowired
    private LoginService loginService;
          
    private String userName;  
    private String passWord;
    private File file;
    private String fileFileName;
    
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getUserName() {  
        return userName;  
    }  
    public String getPassWord() {  
        return passWord;  
    }  
    public void setUserName(String userName) {  
        this.userName = userName;  
    }  
    public void setPassWord(String passWord) {  
        this.passWord = passWord;  
    }  
    public String execute() throws Exception{
    	 String realpath = getServletContext().getRealPath("/img");
    	 System.out.println("realpath: "+realpath);
    	 
         if (file != null) {
             File savefile = new File(new File(realpath), fileFileName);
             if (!savefile.getParentFile().exists())
                 savefile.getParentFile().mkdirs();
             FileUtils.copyFile(file, savefile);
             ActionContext.getContext().put("message", "文件上传成功");
         }
        if(loginService.login(userName, passWord)!=null){
               return SUCCESS;
          }else{  
               return ERROR;  
            }  
    }

}

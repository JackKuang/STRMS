package com.hurenjieee.action;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{  
    
    private static final long serialVersionUID = 1L;  
      
          
    private String userName;  
    private String passWord;  
      
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
        if(userName.equals("admin")&&passWord.equals("123")){  
               return SUCCESS;  
          }else{  
               return ERROR;  
            }  
    }

}

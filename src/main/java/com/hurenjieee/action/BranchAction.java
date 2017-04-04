package com.hurenjieee.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Branch;
import com.hurenjieee.service.BaseService;
import com.hurenjieee.service.BranchService;
import com.hurenjieee.util.BaseAction;

@ParentPackage(value = "json") // 应用全局包
@Scope("prototype")
@Namespace(value = "/admin")
@Action(results = { @Result(name = "json",type = "json",params = { "root", "resultMap" }) ,
        @Result(name = "jsonSon",type = "json",params = { "root", "resultMapSon" }) })
public class BranchAction extends BaseAction<Branch, Long> {

    @Autowired
    BranchService branchService;

    Branch branch;

    Map<String, Object> resultMapSon;
    
    @Override
    public BaseService<Branch, Long> getService(){
        // TODO Auto-generated method stub
        return branchService;
    }

    @Override
    public Branch getObject(){
        // TODO Auto-generated method stub
        return branch == null ? new Branch() : branch;
    }

    
    public Branch getBranch(){
        return branch;
    }

    
    public void setBranch(Branch branch){
        this.branch = branch;
    }

    
    public Map<String, Object> getResultMapSon(){
        return resultMapSon;
    }

    
    public void setResultMapSon(Map<String, Object> resultMapSon){
        this.resultMapSon = resultMapSon;
    }
       
    
    /**
     * @Description: 更具branch.braId来获取所相关的信息
     * @Author: JackKuang
     * @Since: 2017年3月26日下午7:47:11
     * @return
     */
    public String getById(){
        try {
            resultMapSon = new HashMap<String, Object>();
            Branch branch2 = branchService.selectByBraId(branch.getBraId());
            resultMapSon.put("result","success");
            resultMapSon.put("content",branch2);
        } catch (Exception e) {
            e.printStackTrace();
            resultMapSon.put("result","fail");
            resultMapSon.put("reason","未知错误！");
        }
        return "jsonSon";
    }
    
}

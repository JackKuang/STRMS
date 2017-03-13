package com.hurenjieee.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Collective;
import com.hurenjieee.service.BaseService;
import com.hurenjieee.service.CollectiveService;
import com.hurenjieee.util.BaseAction;

@ParentPackage(value = "json") // 应用全局包
@Scope("prototype")
@Namespace(value = "/admin")
@Action(results = { @Result(name = "json",type = "json",params = { "root", "resultMap" }) })
public class CollectiveAction extends BaseAction<Collective, Long> {

    @Autowired
    CollectiveService collectiveService;

    Collective collective;

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

}

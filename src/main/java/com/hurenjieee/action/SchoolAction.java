package com.hurenjieee.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Admin;
import com.hurenjieee.entity.Branch;
import com.hurenjieee.entity.Collective;
import com.hurenjieee.entity.Major;
import com.hurenjieee.service.BranchService;
import com.hurenjieee.service.CollectiveService;
import com.hurenjieee.service.MajorService;
import com.hurenjieee.util.Node;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value = "json") // 应用全局包
@Scope("prototype")
@Namespace(value = "/admin")
@Action(results = { @Result(name = "json",type = "json",params = { "root", "resultMap" }) })
public class SchoolAction extends ActionSupport {

    Map<String, Object>       resultMap;

    private Branch            branch;

    private Major             major;

    @Autowired
    private BranchService     branchService;
    @Autowired
    private MajorService      majorService;
    @Autowired
    private CollectiveService collectiveService;

    public String testJson(){
        resultMap = new HashMap<String, Object>();
        Admin admin = new Admin();
        admin.setAdmName("admin");
        admin.setAdmPicture("12333");
        resultMap.put("admin",admin);
        return "json";
    }

    public Map<String, Object> getResultMap(){
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap){
        this.resultMap = resultMap;
    }

    public Branch getBranch(){
        return branch;
    }

    public void setBranch(Branch branch){
        this.branch = branch;
    }

    public Major getMajor(){
        return major;
    }

    public void setMajor(Major major){
        this.major = major;
    }

    public String listAll(){
        try {
            resultMap = new HashMap<>();
            List<Node> listNode = new ArrayList<Node>();
            List<Branch> branchs = branchService.getList();
            for ( Branch branch : branchs ) {
                Node node = new Node();
                node.setText(branch.getBraName());
                List<Node> listNode2 = new ArrayList<Node>();
                for ( Major major : branch.getMajors() ) {
                    Node node2 = new Node();
                    node2.setText(major.getMajName());
                    // 获取到该分院下的所有班级
                    List<Collective> collectives = collectiveService.selectListByMajId(major.getMajId());
                    List<Node> listNode3 = new ArrayList<Node>();
                    for ( Collective collective : collectives ) {
                        boolean has = false;
                        // 循环node判断是否有存在
                        for ( Node node3 : listNode3 ) {
                            if (node3.getText().equals(collective.getColYear())) {
                                Node node4 = new Node();
                                node4.setText(collective.getColName());
                                node3.getNodes().add(node4);
                                has = true;
                            }
                        }
                        // 不存在则新增加
                        if (has == false) {
                            Node node3 = new Node();
                            Node node4 = new Node();
                            node4.setText(collective.getColName());
                            node3.getNodes().add(node4);
                            listNode3.add(node3);
                        }
                    }
                    //
                    node2.setNodes(listNode3);
                    listNode2.add(node2);
                }
                node.setNodes(listNode2);
                listNode.add(node);
            }
            resultMap.put("result",listNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }

}

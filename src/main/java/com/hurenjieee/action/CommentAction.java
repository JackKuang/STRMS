package com.hurenjieee.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Comment;
import com.hurenjieee.entity.Student;
import com.hurenjieee.entity.Teacher;
import com.hurenjieee.service.BaseService;
import com.hurenjieee.service.CommentService;
import com.hurenjieee.util.BaseAction;

@ParentPackage(value = "json") // 应用全局包
@Scope("prototype")
@Namespace(value = "/resource")
@Action(results = { @Result(name = "json",type = "json",params = { "root", "resultMap" }),
        @Result(name = "jsonSon",type = "json",params = { "root", "resultMapSon" }) })
public class CommentAction extends BaseAction<Comment, Long> {

    @Autowired
    CommentService      commentService;

    Comment             comment;

    Map<String, Object> resultMapSon;

    @Override
    public BaseService<Comment, Long> getService(){
        // TODO Auto-generated method stub
        return commentService;
    }

    @Override
    public Comment getObject(){
        // TODO Auto-generated method stub
        return comment == null ? new Comment() : comment;
    }

    public Comment getComment(){
        return comment;
    }

    public void setComment(Comment comment){
        this.comment = comment;
    }

    public Map<String, Object> getResultMapSon(){
        return resultMapSon;
    }

    public void setResultMapSon(Map<String, Object> resultMapSon){
        this.resultMapSon = resultMapSon;
    }

    public String getListByComResId(){
        try {
            resultMapSon = new HashMap<String, Object>();
            List<Comment> list = commentService.getListByComResId(comment.getComResId());
            resultMapSon.put("result","success");
            resultMapSon.put("content",list);
        } catch (Exception e) {
            e.printStackTrace();
            resultMapSon.put("result","fail");
            resultMapSon.put("reason","未知错误！");
        }
        return "jsonSon";
    }

    public String save(){
        try {
            resultMapSon = new HashMap<String, Object>();
            if (getSessionMap().get("teacher") != null) {
                comment.setComRoleType(2);
                comment.setComRoleId(((Teacher) getSessionMap().get("teacher")).getTeaId());

            } else if (getSessionMap().get("student") != null) {
                comment.setComRoleType(1);
                comment.setComRoleId(((Student) getSessionMap().get("student")).getStuId());
            } else {
                resultMapSon.put("result","fail");
                resultMapSon.put("reason","权限错误！");
                return "json";
            }
            comment.setComDate(new Date());
            getService().saveOrUpdate(comment);
            resultMapSon.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            resultMapSon.put("result","fail");
            resultMapSon.put("reason","未知错误！");
        }
        return "jsonSon";
    }

}

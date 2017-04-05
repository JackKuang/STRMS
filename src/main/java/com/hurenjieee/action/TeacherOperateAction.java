package com.hurenjieee.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.hurenjieee.entity.Branch;
import com.hurenjieee.entity.Student;
import com.hurenjieee.entity.Teacher;
import com.hurenjieee.service.BaseService;
import com.hurenjieee.service.BranchService;
import com.hurenjieee.service.TeacherService;
import com.hurenjieee.util.BaseAction;
import com.hurenjieee.util.ExcelUtil;
import com.hurenjieee.util.Md5AndSha;
import com.hurenjieee.util.PageResults;

@ParentPackage(value = "json") // 应用全局包
@Scope("prototype")
@Namespace(value = "/admin")
@Action(results = { @Result(name = "json",type = "json",params = { "root", "resultMap" }) ,
        @Result(name = "jsonSon",type = "json",params = { "root", "resultMapSon" }) })
public class TeacherOperateAction extends BaseAction<Teacher, Long> {

        
    @Autowired
    TeacherService teacherService;

    @Autowired
    BranchService branchService;
    
    Teacher       teacher;

    Map<String, Object> resultMapSon;
    
    private Integer pageNumber;
    private Integer pageSize;
    private File excelFile; //上传的文件
    private String excelFileName; //文件名称
    private String excelFileContentType; //文件类型

    
    @Override
    public BaseService<Teacher, Long> getService(){
        // TODO Auto-generated method stub
        return teacherService;
    }

    @Override
    public Teacher getObject(){
        // TODO Auto-generated method stub
        return teacher == null ? new Teacher() : teacher;
    }
    
    public Teacher getTeacher(){
        return teacher;
    }

    public void setTeacher(Teacher teacher){
        this.teacher = teacher;
    }

    public Map<String, Object> getResultMapSon(){
        return resultMapSon;
    }

    public void setResultMapSon(Map<String, Object> resultMapSon){
        this.resultMapSon = resultMapSon;
    }
    
    
    public Integer getPageNumber(){
        return pageNumber;
    }

    
    public void setPageNumber(Integer pageNumber){
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize(){
        return pageSize;
    }

    
    public void setPageSize(Integer pageSize){
        this.pageSize = pageSize;
    }
    
    public File getExcelFile(){
        return excelFile;
    }

    
    public void setExcelFile(File excelFile){
        this.excelFile = excelFile;
    }

    
    public String getExcelFileName(){
        return excelFileName;
    }

    
    public void setExcelFileName(String excelFileName){
        this.excelFileName = excelFileName;
    }

    
    public String getExcelFileContentType(){
        return excelFileContentType;
    }

    
    public void setExcelFileContentType(String excelFileContentType){
        this.excelFileContentType = excelFileContentType;
    }

    public String page(){
        try {
            resultMapSon = new HashMap<String, Object>();
            String hql = "From Teacher t";
            if(teacher!= null && !"".equals(teacher.getTeaName()))
                hql = hql + " where  t.teaNo like  '%"+teacher.getTeaName()+ "%' or t.teaName like  '%"+teacher.getTeaName()+ "%'";
            PageResults<Teacher> pageResults = getService().getListByPage(hql,hql,pageNumber,pageSize);
            resultMapSon.put("result","success");
            resultMapSon.put("rows",pageResults.getResults());
            resultMapSon.put("total",pageResults.getTotalCount());
        } catch (Exception e) {
            e.printStackTrace();
            resultMapSon.put("result","fail");
            resultMapSon.put("reason","未知错误！");
        }
        return "jsonSon";
    }

    public String getById(){
        try {
            resultMapSon = new HashMap<String, Object>();
            Teacher teacher2 = teacherService.selectByBraId(teacher);
            resultMapSon.put("result","success");
            resultMapSon.put("content",teacher2);
        } catch (Exception e) {
            e.printStackTrace();
            resultMapSon.put("result","fail");
            resultMapSon.put("reason","未知错误！");
        }
        return "jsonSon";
    }
    
    public String fileSave(){
        try {
            resultMapSon = new HashMap<String, Object>();
            if(excelFile!=null){
                ExcelUtil excelUtil = new ExcelUtil();
                List<Row> list = excelUtil.readExcel(excelFile.getPath());
                List<Teacher> teachers = new ArrayList<Teacher>();
                for(int i=1;i<list.size();i++){
                    Row row= list.get(i);
                    Teacher teacher = new Teacher();
                    teacher.setTeaNo(excelUtil.getCellValue(row.getCell(0)));
                    teacher.setTeaName(excelUtil.getCellValue(row.getCell(1)));
                    teacher.setTeaPhone(excelUtil.getCellValue(row.getCell(2)));
                    teacher.setTeaEmail(excelUtil.getCellValue(row.getCell(3)));
                    String level = excelUtil.getCellValue(row.getCell(4));
                    switch (level) {
                        case "教授":
                            teacher.setTeaLevel(1);
                            break;
                        case "副教授":
                            teacher.setTeaLevel(2);
                            break;
                        case "讲师":
                            teacher.setTeaLevel(3);
                            break;
                        case "助教":
                            teacher.setTeaLevel(4);
                            break;
                        default:
                            teacher.setTeaLevel(0);
                            break;
                    }
                    String braName =  excelUtil.getCellValue(row.getCell(5));
                    Branch branch = branchService.selectByBraName(braName);
                    if(branch!=null){
                        teacher.setTeaBraId(branch.getBraId());
                    }
                    teachers.add(teacher);
                }
                for(Teacher t:teachers){
                    t.setTeaPassword(Md5AndSha.convertMD5("123456"));
                    teacherService.save(t);
                }
                resultMapSon.put("result","success");
                resultMapSon.put("reason","未知错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMapSon.put("result","fail");
            resultMapSon.put("reason","未知错误！");
        }
        return "jsonSon";
    }
    
}

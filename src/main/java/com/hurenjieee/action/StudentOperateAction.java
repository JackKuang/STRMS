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

import com.hurenjieee.entity.Collective;
import com.hurenjieee.entity.Student;
import com.hurenjieee.service.BaseService;
import com.hurenjieee.service.CollectiveService;
import com.hurenjieee.service.StudentService;
import com.hurenjieee.util.BaseAction;
import com.hurenjieee.util.ExcelUtil;
import com.hurenjieee.util.Md5AndSha;
import com.hurenjieee.util.PageResults;

@ParentPackage(value = "json") // 应用全局包
@Scope("prototype")
@Namespace(value = "/admin")
@Action(results = { @Result(name = "json",type = "json",params = { "root", "resultMap" }) ,
        @Result(name = "jsonSon",type = "json",params = { "root", "resultMapSon" }) })
public class StudentOperateAction extends BaseAction<Student, Long> {

        
    @Autowired
    StudentService studentService;

    @Autowired
    CollectiveService collectiveService;

    Student        student;

    Map<String, Object> resultMapSon;
    
    private Integer pageNumber;
    
    private Integer pageSize;

    
    private File excelFile; //上传的文件
    private String excelFileName; //文件名称
    private String excelFileContentType; //文件类型

    
    @Override
    public BaseService<Student, Long> getService(){
        // TODO Auto-generated method stub
        return studentService;
    }

    @Override
    public Student getObject(){
        // TODO Auto-generated method stub
        return student == null ? new Student() : student;
    }
    
    
    public Student getStudent(){
        return student;
    }

    
    public void setStudent(Student student){
        this.student = student;
    }

    
    public Integer getPageNumber(){
        return pageNumber;
    }

    
    public void setPageNumber(Integer pageNumber){
        this.pageNumber = pageNumber;
    }

    public Map<String, Object> getResultMapSon(){
        return resultMapSon;
    }

    
    public void setResultMapSon(Map<String, Object> resultMapSon){
        this.resultMapSon = resultMapSon;
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
            String hql = "From Student s";
            if(student!= null && !"".equals(student.getStuName()))
                hql = hql + " where s.stuId like '%"+student.getStuName()+ "%' or s.stuName like  '%"+student.getStuName()+ "%'";
            PageResults<Student> pageResults = getService().getListByPage(hql,hql,pageNumber,pageSize);
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
            Student student2 = studentService.selectByStuId(student.getStuId());
            resultMapSon.put("result","success");
            resultMapSon.put("content",student2);
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
                List<Student> students = new ArrayList<Student>();
                for(int i=1;i<list.size();i++){
                    Row row= list.get(i);
                    Student student = new Student();
                    student.setStuNo(excelUtil.getCellValue(row.getCell(0)));
                    student.setStuName(excelUtil.getCellValue(row.getCell(1)));
                    student.setStuPhone(excelUtil.getCellValue(row.getCell(2)));
                    student.setStuEmail(excelUtil.getCellValue(row.getCell(3)));
                    String colName =  excelUtil.getCellValue(row.getCell(4));
                    Collective collective = collectiveService.selectByColName(colName);
                    if(collective!=null){
                        student.setStuColId(collective.getColId());
                    }
                    students.add(student);
                }
                for(Student s:students){
                    s.setStuPassword(Md5AndSha.convertMD5("123456"));
                    studentService.save(s);
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

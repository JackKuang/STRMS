<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>系统信息查看</title>
<jsp:include page="/WEB-INF/include/meta.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/css-jquery.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/adaptor.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/redirectPage.jsp"></jsp:include>
</head>
<body>
	<section class="content-header">
		<h1>资源管理</h1>
	</section>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-body">
						<table id="studentTable" class="table table-bordered table-striped dataTable" role="grid">
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
	
</body>

<script type="text/javascript">
	$(function(){
		initTable();
		loadTree();
	})
	function initTable() {  
        //先销毁表格  
        $("#studentTable").bootstrapTable('destroy');  
        //初始化表格,动态从服务器加载数据  
        $("#studentTable").bootstrapTable({  
            method: "get",  //使用get请求到服务器获取数据  
            url:"resource!page.action", 
            //url:"/STRMS/test.json",
            striped: true,  //表格显示条纹  
            //pagination: true, //启动分页 	 
            //pageSize: 10,  //每页显示的记录数  
            //pageNumber:1, //当前第几页  
            //search: true,  //是否启用查询  
            //showColumns: true,  //显示下拉框勾选要显示的列  
            //showRefresh: true,  //显示刷新按钮  
            sidePagination: "server", //表示服务端请求  
            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
            //设置为limit可以获取limit, offset, search, sort, order  
            queryParamsType : "undefined",   
            queryParams: function queryParams(params) {   //设置查询参数 
	            var param = {
	            	pageNumber: params.pageNumber,    
	                pageSize: params.pageSize,
	                'student.stuName':params.searchText
	            };
            	return param;
            },  
            columns: [{  
                field: 'stuNo',
                title: '学生编号'
            },{  
                field: 'stuName',
                title: '姓名'
            },{  
                field: 'stuPhone',
                title: '电话'
            },{  
                field: 'stuEmail',
                title: '邮箱'
            },{  
                field: 'stuColName',
                title: '专业'
            },{  
                field: 'stuBraName',
                title: '所在专业'
            }, {
                field: 'stuId',
                title: '操作',
                align: 'center',
                events: operateEvents,
                formatter: operateFormatter
            }],  
            onLoadSuccess: function(){  //加载成功时执行
            },  
            onLoadError: function(){  //加载失败时执行 
            } 
          });  
      }
	 function operateFormatter(value, row, index) {
	    return [
	        '<a class="add">编辑',
	        '</a>  ',
	        '<a class="remove">删除</i>',
	        '</a>'
	    ].join('');
	 }
	 window.operateEvents = {
        'click .add': function (e, value, row, index) {
        	showStudent(row.stuId);
        },
        'click .remove': function (e, value, row, index) {
        	deleteStudent(row.stuId);
        }
    };
	function deleteStudent(stuId) {
		bootbox.confirm("确认删除",function(result){
			if(result==true){
				$.ajax({
					url : 'student_operate!delete.action',
					type : 'POST', //GET
					async : true, //或false,是否异步
					data : {
						'student.stuId' : stuId
					},
					dataType : 'json',
					success : function(data, textStatus, jqXHR) {
						if (data.result == "success") {
							bootbox.alert("操作成功");
					        $("#studentTable").bootstrapTable('refresh');
						} else {
							bootbox.alert("操作失败");
						}
					}
				})
			}
		});
	}
	function loadTree(){
		$.ajax({
			url : 'school!listAll.action',
			type : 'POST', //GET
			async : true, //或false,是否异步
			dataType : 'json',
			success : function(data, textStatus, jqXHR) {
				tree = data.content;
			}
		})
	}
	function setMajor(braId){
		var nodes = tree;
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].id==braId){
				majList = nodes[i].nodes;
				$("#majSelect").empty();
				$("#yearSelect").empty();
				$("#colSelect").empty();
				for(var j=0;j<nodes[i].nodes.length;j++){
					var option = $("<option>").val(nodes[i].nodes[j].id).text(nodes[i].nodes[j].text);
					$("#majSelect").append(option);
				}
				return;
			}
		}
	}
	function setYear(majId){
		var nodes = majList;
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].id==majId){
				colList = nodes[i].nodes;
				$("#yearSelect").empty();
				$("#colSelect").empty();
				for(var j=0;j<nodes[i].nodes.length;j++){
					var option = $("<option>").val(nodes[i].nodes[j].text).text(nodes[i].nodes[j].text);
					$("#yearSelect").append(option);
				}
				return;
			}
		}
	}
	function setCollective(yearId){
		var nodes = colList;
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].text==yearId){
				majList = nodes[i].nodes;
				$("#colSelect").empty();
				for(var j=0;j<nodes[i].nodes.length;j++){
					var option = $("<option>").val(nodes[i].nodes[j].id).text(nodes[i].nodes[j].text);
					$("#colSelect").append(option);
				}
				return;
			}
		}
	}
</script>
</html>

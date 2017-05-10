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
		<h1>学生管理</h1>
	</section>
	<section class="content">

		<div class="row">
			<div class="col-md-6">
				<table class="table table-bordered text-center">
					<tr>
						<td>
							<button type="button" onclick="showStudent();"
								class="btn btn-block btn-primary btn-lg">增加学生</button>
						</td>
						<td>
							<form id="fileForm" action="student_operate!fileSave.action" method="post" enctype="multipart/form-data">
                 				<input type="file" id="excelFile" name="excelFile">
                 			</form>
						</td>
						<td>
							<button type="button" id="fileUpload"
								class="btn btn-block btn-primary btn-lg">文件导入</button>
						</td>
					</tr>
				</table>
			</div>
			<div class="col-md-12">
				<div class="box">
					<div class="box-body">
						<div id="toolbar">
							分院
							<select style="width:120px" id="braSelect2" onchange="setMajor2(this.value)" >
								<option value=""></option>
								<s:iterator var="branch" value="branchList">
									<option value="<s:property value="#branch.braId" />"><s:property
											value="#branch.braName" /></option>
								</s:iterator>
							</select>
							专业
							<select style="width:120px" id="majSelect2" onchange="setYear2(this.value)" ></select>
							入学时间
							<select style="width:120px" id="yearSelect2" onchange="setCollective2(this.value)" ></select>
							班级
							<select style="width:120px" id="colSelect2" ></select>
						</div>
						<table id="studentTable" class="table table-bordered table-striped dataTable" role="grid">
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
	<div class="modal" id="studentModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title" id="studentTitle">新增分院</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="studentForm"
							action="student_operate!save.action">
							<input type="hidden" id="stuId" name="student.stuId" />
							<div class="box-body">
								<div class="form-group">
									<label class="col-sm-2">学生编号</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="stuNo"
											name="student.stuNo" placeholder="学生编号">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2">学生姓名</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="stuName"
											name="student.stuName" placeholder="学生姓名">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2">电话</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="stuPhone"
											name="student.stuPhone" placeholder="电话">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2">邮箱</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="stuEmail"
											name="student.stuEmail" placeholder="邮箱">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2">所属分院</label>
									<div class="col-sm-10">
										<select class="form-control" id="braSelect" onchange="setMajor(this.value)">
											<option value=""></option>
											<s:iterator var="branch" value="branchList">
												<option value="<s:property value="#branch.braId" />"><s:property
														value="#branch.braName" /></option>
											</s:iterator>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2">专业名称</label>
									<div class="col-sm-10">
										<select class="form-control" id="majSelect" onchange="setYear(this.value)">
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2">入学时间</label>
									<div class="col-sm-10">
										<select class="form-control" id="yearSelect" onchange="setCollective(this.value)">
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2">班级名称</label>
									<div class="col-sm-10">
										<select class="form-control" id="colSelect"
											name="student.stuColId">
										</select>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" id="studentSave" class="btn btn-primary">保存</button>
						<button type="button" id="studentCancel" class="btn btn-default"
							data-dismiss="modal">取消</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
</body>

<script type="text/javascript">
	function showStudent(stuId) {
		if (stuId != null) {
			$("#studentTitle").html("修改学生");
			$("#studentId").val(stuId);
			$.ajax({
				url : 'student_operate!getById.action',
				type : 'POST', //GET
				async : true, //或false,是否异步
				dataType : 'json',
				data : {
					'student.stuId' : stuId
				},
				success : function(data, textStatus, jqXHR) {
					if (testData(data)) {
						$("#stuId").val(data.content.stuId);
						$("#stuNo").val(data.content.stuNo);
						$("#stuName").val(data.content.stuName);
						$("#stuPhone").val(data.content.stuPhone);
						$("#stuEmail").val(data.content.stuEmail);
						$("#braSelect").val(data.content.stuBraId);
						setMajor(data.content.stuBraId);
						$("#majSelect").val(data.content.stuMajId);
						setYear(data.content.stuMajId);
						$("#yearSelect").val(data.content.stuYearId);
						setCollective(data.content.stuYearId);
						$("#colSelect").val(data.content.stuColId);
						
					}
				}
			})
		} else {
			$("#studentTitle").html("新增学生");
		}
		$('#studentModal').modal('show');
	}

	$("#studentSave").click(function() {
		$("#studentForm").ajaxSubmit(function(data) {
			if(testData(data)) {
				bootbox.alert("操作成功");
				($("#studentForm"))[0].reset();
		        $("#studentTable").bootstrapTable('refresh');
			}
		})
		$('#studentModal').modal('hide');
	});
	
	$("#studentCancel").click(function() {
		($("#studentForm"))[0].reset();
	});
	
	$("#fileUpload").click(function() {
		$("#fileForm").ajaxSubmit(function(data) {
			if (testData(data)) {
				bootbox.alert("操作成功");
		        $("#studentTable").bootstrapTable('refresh');
			}
		})
	});
	
	var tree;
	var majList;
	var yearList;
	var colList;
	
	var tree2;
	var majList2;
	var yearList2;
	var colList2;
	
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
            url:"student_operate!page.action", 
	    	toolbar:"#toolbar",
            //url:"/STRMS/test.json",
            striped: true,  //表格显示条纹  
            pagination: true, //启动分页 	 
            pageSize: 10,  //每页显示的记录数  
            pageNumber:1, //当前第几页  
            search: true,  //是否启用查询  
            showColumns: true,  //显示下拉框勾选要显示的列  
            showRefresh: true,  //显示刷新按钮  
            sidePagination: "server", //表示服务端请求  
            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
            //设置为limit可以获取limit, offset, search, sort, order  
            queryParamsType : "undefined",   
            queryParams: function queryParams(params) {   //设置查询参数 
	            var param = {
	            	pageNumber: params.pageNumber,    
	                pageSize: params.pageSize,
	                'student.stuName':params.searchText,
	            	'student.stuBraId':$("#braSelect2").val(),
	            	'student.stuMajId':$("#majSelect2").val(),
	            	'student.stuYearId':$("#yearSelect2").val(),
	            	'student.stuColId':$("#colSelect2").val()
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
				tree2 = tree;
			}
		})
	}
	function setMajor(braId){
		var nodes = tree;
		$("#majSelect").empty();
		$("#yearSelect").empty();
		$("#colSelect").empty();
		$("#majSelect").append( $("<option>").val('').text(''));
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].id==braId){
				majList = nodes[i].nodes;
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
		$("#yearSelect").empty();
		$("#colSelect").empty();
		$("#yearSelect").append( $("<option>").val('').text(''));
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].id==majId){
				colList = nodes[i].nodes;
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
		$("#colSelect").empty();
		$("#colSelect").append( $("<option>").val('').text(''));
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].text==yearId){
				majList = nodes[i].nodes;
				for(var j=0;j<nodes[i].nodes.length;j++){
					var option = $("<option>").val(nodes[i].nodes[j].id).text(nodes[i].nodes[j].text);
					$("#colSelect").append(option);
				}
				return;
			}
		}
	}


	function setMajor2(braId){
		var nodes = tree2;
		$("#majSelect2").empty();
		$("#yearSelect2").empty();
		$("#colSelect2").empty();
		$("#majSelect2").append( $("<option>").val('').text(''));
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].id==braId){
				majList2 = nodes[i].nodes;
				for(var j=0;j<nodes[i].nodes.length;j++){
					var option = $("<option>").val(nodes[i].nodes[j].id).text(nodes[i].nodes[j].text);
					$("#majSelect2").append(option);
				}
			}
		}
		reloadStudentTable();
	}
	function setYear2(majId){
		var nodes = majList2;
		$("#yearSelect2").empty();
		$("#colSelect2").empty();
		$("#yearSelect2").append( $("<option>").val('').text(''));
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].id==majId){
				colList2 = nodes[i].nodes;
				for(var j=0;j<nodes[i].nodes.length;j++){
					var option = $("<option>").val(nodes[i].nodes[j].text).text(nodes[i].nodes[j].text);
					$("#yearSelect2").append(option);
				}
			}
		}
		reloadStudentTable();
	}
	function setCollective2(yearId){
		var nodes = colList2;
		$("#colSelect2").empty();
		$("#colSelect2").append( $("<option>").val('').text(''));
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].text==yearId){
				majList2 = nodes[i].nodes;
				for(var j=0;j<nodes[i].nodes.length;j++){
					var option = $("<option>").val(nodes[i].nodes[j].id).text(nodes[i].nodes[j].text);
					$("#colSelect2").append(option);
				}
			}
		}
		reloadStudentTable();
	}
	function reloadStudentTable(){
        $("#studentTable").bootstrapTable('refresh',{
            url:"student_operate!page.action",
            silent: true,
        	query:{
            	'student.stuBraId':$("#braSelect2").val(),
            	'student.stuMajId':$("#majSelect2").val(),
            	'student.stuYearId':$("#yearSelect2").val(),
            	'student.stuColId':$("#colSelect2").val()
            }
        });
	}
</script>
</html>

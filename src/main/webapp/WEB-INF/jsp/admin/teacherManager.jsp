<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>系统信息查看</title>
<jsp:include page="/WEB-INF/include/meta.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/adaptor.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/css-jquery.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/redirectPage.jsp"></jsp:include>
</head>
</head>
<body>
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>教师管理</h1>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="row">
			<div class="col-md-6">
				<table class="table table-bordered text-center">
					<tr>
						<td>
							<button type="button" onclick="showTeacher();"
								class="btn btn-block btn-primary btn-lg">增加教师</button>
						</td>
						<td>
							<form id="fileForm" action="teacher_operate!fileSave.action" method="post" enctype="multipart/form-data">
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
						</div>
						<table id="teacherTable" class="table table-bordered table-striped dataTable" role="grid">
						</table>
					</div>
				</div>
			</div>
		</div>
		</section>
		<div class="modal" id="teacherModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title" id="teahcherTitle">新增教师</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="teacherForm"
							action="teacher_operate!save.action">
							<input type="hidden" id="teaId" name="teacher.teaId" />
							<div class="box-body">
								<div class="form-group">
									<label class="col-sm-2">教师编号</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="teaNo"
											name="teacher.teaNo" placeholder="教师编号">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2">教师姓名</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="teaName"
											name="teacher.teaName" placeholder="教师姓名">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2">电话</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="teaPhone"
											name="teacher.teaPhone" placeholder="电话">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2">邮箱</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="teaEmail"
											name="teacher.teaEmail" placeholder="邮箱">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2">职位</label>
									<div class="col-sm-10">
										<select class="form-control" id="teaLevel"
											name="teacher.teaLevel">
											<option value="1">教授</option>
											<option value="2">副教授</option>
											<option value="3">讲师</option>
											<option value="4·">助教</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2">所属分院</label>
									<div class="col-sm-10">
										<select class="form-control" id="braSelect"
											name="teacher.teaBraId">
											<s:iterator var="branch" value="branchList">
												<option value="<s:property value="#branch.braId" />"><s:property
														value="#branch.braName" /></option>
											</s:iterator>
										</select>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" id="teacherSave" class="btn btn-primary">保存</button>
						<button type="button" id="teacherCancel" class="btn btn-default"
							data-dismiss="modal">取消</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		
		<div class="modal" id="collectiveModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title" id="collectiveTitle">设置班级</h4>
					</div>
					<div class="modal-body">
						<div class="box-body" id="collectives">
			        	</div>
			        	<div id="treeviewBranch"></div>
					</div>
					<div class="modal-footer">
						<button type="button" id="collectiveSave" class="btn btn-primary">保存</button>
						<button type="button" id="collectiveCancel" class="btn btn-default"
							data-dismiss="modal">取消</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
</body>
<script type="text/javascript">
	function showTeacher(teaId) {
		if (teaId != null) {
			$("#teacherTitle").html("修改教师");
			$("#teacherId").val(teaId);
			$.ajax({
				url : 'teacher_operate!getById.action',
				type : 'POST', //GET
				async : true, //或false,是否异步
				dataType : 'json',
				data : {
					'teacher.teaId' : teaId
				},
				success : function(data, textStatus, jqXHR) {
					if (testData(data)) {
						$("#teaId").val(data.content.teaId);
						$("#teaNo").val(data.content.teaNo);
						$("#teaName").val(data.content.teaName);
						$("#teaPhone").val(data.content.teaPhone);
						$("#teaEmail").val(data.content.teaEmail);
						$("#teacherId").val(data.content.teaBraId);
					}
				}
			})
		} else {
			$("#teacherTitle").html("新增教师");
		}
		$('#teacherModal').modal('show');
	}

	$("#teacherSave").click(function() {
		$("#teacherForm").ajaxSubmit(function(data) {
			if(testData(data)) {
				bootbox.alert("操作成功");
				($("#teacherForm"))[0].reset();
		        $("#teacherTable").bootstrapTable('refresh');
			}
		})
		$('#teacherModal').modal('hide');
	});

	$("#teacherCancel").click(function() {
		($("#teacherForm"))[0].reset();
	});
	$("#fileUpload").click(function() {
		$("#fileForm").ajaxSubmit(function(data) {
			if (testData(data)) {
				bootbox.alert("操作成功");
		        $("#teacherTable").bootstrapTable('refresh');
			}
		})
	});
	
	$(function(){
		initTable();
	})
	function initTable() {  
        //先销毁表格  
        $("#teacherTable").bootstrapTable('destroy');  
        //初始化表格,动态从服务器加载数据  
        $("#teacherTable").bootstrapTable({  
            method: "get",  //使用get请求到服务器获取数据  
            url:"teacher_operate!page.action", 
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
				     'teacher.teaName':params.searchText
				};
				return param;  
            },  
            columns: [{  
                field: 'teaNo',
                title: '教师编号'
            },{  
                field: 'teaName',
                title: '姓名'
            },{  
                field: 'teaPhone',
                title: '电话'
            },{  
                field: 'teaEmail',
                title: '邮箱'
            },{  
                field: 'teaLevel',
                title: '教师职位',
                formatter: levelFormatter
            },{  
                field: 'teaBraName',
                title: '所属分院'
            },{  
                field: 'collectiveNames',
                title: '管辖班级'
            },{
                field: 'teaId',
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
	 function levelFormatter(value, row, index) {
		 var level="";
		 if(value =="1"){
			 level = "教授";
		 }else if(value =="2"){
			 level = "副教授";
		 }else if(value =="3"){
			 level = "讲师";
		 }else if(value =="4"){
			 level = "助教";
		 }
		 return [level].join('');
	 }
	 function operateFormatter(value, row, index) {
		    return [
		        '<a class="editCollective">设置班级',
		        '</a>  ',
		        '<a class="add">编辑',
		        '</a>  ',
		        '<a class="remove">删除</i>',
		        '</a>'
		    ].join('');
		 }
	 window.operateEvents = {
        'click .add': function (e, value, row, index) {
        	showTeacher(row.teaId);
        },
        'click .remove': function (e, value, row, index) {
        	deleteTeacher(row.teaId);
        },
        'click .editCollective': function (e, value, row, index) {
        	editCollective(row.teaId);
        	ids = row.collectiveIds;
            names = row.collectiveNames;
            stringToHmtl();
        }
    };
	function deleteTeacher(teaId) {
		bootbox.confirm("确认删除",function(result){
			if(result==true)
				$.ajax({
					url : 'teacher_operate!delete.action',
					type : 'POST', //GET
					async : true, //或false,是否异步
					data : {
						'teacher.teaId' : teaId
					},
					dataType : 'json',
					success : function(data, textStatus, jqXHR) {
						if (data.result == "success") {
							bootbox.alert("操作成功");
					        $("#teacherTable").bootstrapTable('refresh');
						} else {
							bootbox.alert("操作失败");
						}
					}
				})
		});
	}
	var teaIdGlobal;
	function editCollective(teaId){
		teaIdGlobal = teaId
		$('#collectives').html('');
		reloadTree();
		$('#collectiveModal').modal('show');
	}


	$("#collectiveSave").click(function() {

		$.ajax({
			url : 'relation!saveMutiple.action',
			type : 'POST', //GET
			async : true, //或false,是否异步
			dataType : 'json',
			data : {
				'relation.relTeaId' : teaIdGlobal,
				'relColIds' : ids,
			},
			success : function(data, textStatus, jqXHR) {
				if (testData(data)) {
					bootbox.alert("操作成功");
					$('#collectiveModal').modal('hide');
			        $("#teacherTable").bootstrapTable('refresh');
				}
			}
		})
		$('#teacherModal').modal('hide');
	});

	function formatterData2(data,depth){
		var type = "";
		if(depth==1){
			type="Branch";
		}
		if(depth==2){			
			type="Major";
		}
		if(depth==4){
			type="Collective";
		}
		for(var i=0;i<data.length;i++){
			if(data[i].nodes==null || data[i].nodes.length==0){
				if(depth==4){
					data[i].text = data[i].text +"<div class='box-tools pull-right'><button type='button' onclick='addCollective(\""+data[i].id+"\",\""+data[i].text+"\")' class='btn btn-box-tool' data-widget='collapse'><i class='fa fa-plus'></i></button>" ;
				}
			}else{
				data[i].nodes =  formatterData2(data[i].nodes,depth+1);
			}
		}
		return data;
	}
	function reloadTree(){
		$.ajax({
			url : 'school!listAll.action',
			type : 'POST', //GET
			async : true, //或false,是否异步
			dataType : 'json',
			success : function(data, textStatus, jqXHR) {
				var content =  formatterData2(data.content,1);
				var $tree = $('#treeviewBranch').treeview({
					data : content,
					backColor: 'green',
				})
				//$('#treeviewBranch').on('nodeSelected', function(event, data) {
				//	addCollective(data.id,data.text);
                //});
			}
		})
	}

	var ids = "";
	var names = "";
	function addCollective(id,text){
		var hasCollective = false;
		if(ids!=null && ids!=""){
			var idString = ids.split(',');
			for(var i=0;i<idString.length;i++){
				if(idString[i]==id){
					hasCollective = true;
				}
			}
		}
		if(hasCollective==false){
			if(ids==null || ids==""){
				ids=""+id+"";
				names=""+text+"";
			}else{
				ids=ids+","+id+"";
				names=names+","+text+"";
			}
		}
        stringToHmtl();
	}
	function removeCollective(id,text){
		var newIds = "";
		var newNames = "";
		var idString = ids.split(',');
        var nameString = names.split(',');
        for(var i=0;i<idString.length;i++){
            if(idString[i]!=id){
        		if(newIds==null || newIds==""){
        			newIds=""+idString[i]+"";
        			newNames=""+nameString[i]+"";
        		}else{
        			newIds=newIds+","+idString[i]+"";
        			newNames=newNames+","+nameString[i]+"";
        		}
            }
        }
        ids = newIds;
        names = newNames;
        stringToHmtl();
	}
	function stringToHmtl(){
        $('#collectives').html('');
		if(ids!=null && ids!=""){
			console.log(ids);
	        var idString = ids.split(',');
	        var nameString = names.split(',');
	        var oldHtml="";
	        for(var i=0;i<idString.length;i++){
		        if(idString[i]!=null && idString[i]!=''){
					oldHtml = oldHtml + '<div class="col-md-3"><button type="button" class="btn btn-block btn-primary btn-lg" onclick="removeCollective(\''+idString[i]+'\',\''+nameString[i]+'\')">'+nameString[i]+'</button></div>';
		        }
	        }
	        $('#collectives').html(oldHtml);
		}
	}
	function setMajor2(braId){
		$("#teacherTable").bootstrapTable('refresh',{
            url:"teacher_operate!page.action",
            silent: true,
        	query:{
            	'teacher.teaBraId':braId
            }
        });
	}
</script>
</html>

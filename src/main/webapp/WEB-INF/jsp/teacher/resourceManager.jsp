<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<ol class="breadcrumb" id="path">
			        <li id="0"><a href="#" onclick="reloadByPath(this)"><i class="fa fa-home"></i> Home</a></li>
			    </ol>
			</div>
			<div class="col-md-12">
				<div id="toolbar">
				    <button id="newFolder" class="btn btn-primary">
				        <i class="fa fa-folder"></i> 新建文件夹
				    </button>
				    <button id="uploadFiles" class="btn btn-primary">
				        <i class="fa fa-upload"></i> 上传文件
				    </button>
				</div>
				<table id="resourceTable" class="table table-bordered table-striped dataTable" role="grid">
				</table>
			</div>
		</div>
	</section>
	
	<div class="modal" id="resourceModal">
			<div class="modal-dialog">	
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title" id="resourceTitle"></h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="resourceForm"
							action="../resource/resource!update.action">
							<input type="hidden" name="resource.resTeaId" value="${teacher.teaId }" />
							<input type="hidden" id="resourceId" name="resource.resId" />
							<input type="hidden" id="resourceType" name="resource.resType" value = "folder" />
							<div class="box-body">
								<div class="form-group">
									<label class="col-sm-2" id="labFile">文件名</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="resourceName"
											name="resource.resName" placeholder="新文件名">
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" id="resourceSave" class="btn btn-primary">保存</button>
						<button type="button" id="resourceCancel" class="btn btn-default"
							data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal" id="newFolderModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title">新建文件夹</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="newFolderForm"
							action="../resource/resource!save.action">
							<input type="hidden" name="resource.resTeaId" value="${teacher.teaId }" />
							<input type="hidden" name="resource.resState" value="1"/>
							<input type="hidden" name="resource.resParId" id="resourceParId" />
							<input type="hidden" name="resource.resType" id="resourceTypeFolder" value = "folder" />
							<div class="box-body">
								<div class="form-group">
									<label class="col-sm-2">新文件夹名</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="resource.resName" placeholder="新文件夹名">
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" id="newFolderSave" class="btn btn-primary">保存</button>
						<button type="button" id="newFolderCancel" class="btn btn-default"
							data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal" id="uploadFilesModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title">上传文件</h4>	
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="uploadFilesForm" method="post" enctype="multipart/form-data"
							action="../resource/resource!saveFiles.action">
							<input type="hidden" name="resource.resTeaId" value="${teacher.teaId }" />
							<input type="hidden" name="resource.resState" value="1"/>
							<input type="hidden" name="resource.resParId" id="resourceParId2" />
							<div class="box-body">
								<div class="form-group">
									<label class="col-sm-2">选择文件</label>
									<div class="col-sm-10">
										<input type="file" name="fff" multiple/>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" id="uploadFilesSave" class="btn btn-primary">保存</button>
						<button type="button" id="uploadFilesCancel" class="btn btn-default"
							data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal" id="movePathModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title">移动文件</h4>	
					</div>
					<div class="modal-body">
						<div class="box-body">
							<div class="form-group">
								<label class="col-sm-2">选择路劲</label>
								<div class="col-sm-10">
									<div id="movePathTree"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="movePathSave" class="btn btn-primary">保存</button>
						<button type="button" id="movePathCancel" class="btn btn-default"
							data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript">
	var resParId = 0 ;
	$(function(){
		initTable();
		$('#resourceParId').val(resParId);
	})
	function initTable() {  
	    //先销毁表格  
	    $("#resourceTable").bootstrapTable('destroy');  
	    //初始化表格,动态从服务器加载数据  
	    $("#resourceTable").bootstrapTable({
	    	toolbar:"#toolbar",
	        method: "get",  //使用get请求到服务器获取数据  
	        url:"../resource/resource!page.action", 
	        //url:"/STRMS/test.json",
	        striped: true,  //表格显示条纹  
	        //pagination: true, //启动分页 	 
	        //pageSize: 10,  //每页显示的记录数  
	        ///pageNumber:1, //当前第几页  
	        search: true,  //是否启用查询  
	        //showColumns: true,  //显示下拉框勾选要显示的列  
	        //showRefresh: true,  //显示刷新按钮  
	        sidePagination: "server", //表示服务端请求  
	        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
	        //设置为limit可以获取limit, offset, search, sort, order  
	        queryParamsType : "undefined",   
	        queryParams: function queryParams(params) {   //设置查询参数
	            var param = {
 	                'resource.resName':params.searchText,
	                'resource.resParId':resParId,
	                'resource.resTeaId':'${teacher.teaId }'
	            };
	        	return param;
	        },  
	        columns: [{  
	            field: 'resName',
	            title: '文件名'
	        },{  
	            field: 'resSize',
	            title: '文件大小'
	        },{ 
	            field: 'resUploadTime',
	            title: '文件大小'
	        },{ 
	            field: 'resState',
	            title: '文件状态',
	            formatter: stateFormatter
	        },{
	            field: 'resId',
	            title: '操作',
	            align: 'left',
	            events: operateEvents,
	            formatter: operateFormatter
	        }],  
	        onLoadSuccess: function(){  //加载成功时执行
	        },  
	        onLoadError: function(){  //加载失败时执行 
	        },
	        //onClickRow:function(){}
	        onDblClickCell:function(field, value, row){
	        	addPath(row.resId,row.resName);
	        	resParId  = row.resId;
		        $("#resourceTable").bootstrapTable('refresh');
	        }
		});
	}
	
	function operateFormatter(value, row, index) {
	   var s="";
	   s = s + '<a class="rename">重命名</a>';
	   s = s + ' <a class="move">移动 </a>';
	   s = s + ' <a class="remove">删除</a>';
	   if(row.resType!="folder"){
		   if(row.resState=="10"){
			   s = s + ' <a class="canel-download">取消开放下载</a>';
		   }else if(row.resState =="1" || row.resState =="3"){
			   s = s + ' <a class="apply">开放下载</a>';
		   }else if(row.resState =="2"){
			   s = s + ' <a class="canel-apply">取消申请</a>';
		   }
	   }
	   return [s].join('');
	}
	function stateFormatter(value, row, index) {
		var level="";
		if(value =="10"){
			level = "开放下载";
		}else if(value =="1" || value =="3"){
			level = "";
		}else if(value =="2"){
			level = "审核中";
		//}else if(value =="3"){
		//	level = "审核不通过";
		}
		return [level].join('');
	}
	window.operateEvents = {
	   'click .rename': function (e, value, row, index) {
		   renameResource(row.resId,row.resName);
	   },
	   'click .move': function (e, value, row, index) {
		   moveResource(row.resId);
	   },
	   'click .remove': function (e, value, row, index) {
		   updateResource(row.resId,0);
	   },
	   'click .canel-download': function (e, value, row, index) {
		   updateResource(row.resId,1);
	   },
	   'click .apply': function (e, value, row, index) {
		   updateResource(row.resId,2);
	   },
	   'click .canel-apply': function (e, value, row, index) {
		   updateResource(row.resId,1);
	   }
	};
	function renameResource(resId,resName){
		if (resId != null || resId != '') {
			$("#resourceTitle").html("重命名");
			$("#resourceId").val(resId);
			$("#resourceName").val(resName);
		}
		$('#resourceModal').modal('show');
	}
	
	$("#resourceSave").click(function() {
		$("#resourceForm").ajaxSubmit(function(data) {
			if(testData(data)) {
				bootbox.alert("操作成功");
				($("#resourceForm"))[0].reset();
		        $("#resourceTable").bootstrapTable('refresh');
			}
		})
		$('#resourceModal').modal('hide');
	});

	$("#resourceCancel").click(function() {
		($("#resourceForm"))[0].reset();
	});
	
	function moveResource(resId){
		$('#movePathModal').modal('show');
		$.ajax({
			url : '../resource/resource!folderTree.action',
			type : 'POST', //GET
			async : true, //或false,是否异步
			dataType : 'json',
			data:{
				'resource.resTeaId':${teacher.teaId }
			},
			success : function(data, textStatus, jqXHR) {
				var content =  data.content;
				var $tree = $('#movePathTree').treeview({
					data : content,
					backColor: 'green'
				})
			}
		})
	}
	
	function updateResource(resId,state){
		$.ajax({
			url : '../resource/resource!update.action',
			type : 'POST', //GET
			async : true, //或false,是否异步
			data : {
				'resource.resId' : resId,
				'resource.resState' : state
			},	
			dataType : 'json',
			success : function(data, textStatus, jqXHR) {
				if (data.result == "success") {
					bootbox.alert("操作成功");
			        $("#resourceTable").bootstrapTable('refresh');
				} else {
					bootbox.alert("操作失败");
				}
			}
		})
	}
	
	$('#newFolder').click(function(){
		$('#resourceParId').val(resParId);
		$('#newFolderModal').modal('show');
	})
	
	$('#uploadFiles').click(function(){
		$('#resourceParId2').val(resParId);
		$('#uploadFilesModal').modal('show');
	})
	
	

	$("#uploadFilesSave").click(function() {
		$("#uploadFilesForm").ajaxSubmit(function(data) {
			if(testData(data)) {
				bootbox.alert("操作成功");
				($("#uploadFilesForm"))[0].reset();
		        $("#resourceTable").bootstrapTable('refresh');
			}
		})
		$('#uploadFilesModal').modal('hide');
	});

	$("#uploadFilesCancel").click(function() {
		($("#uploadFilesForm"))[0].reset();
	});
	

	$("#newFolderSave").click(function() {
		$("#newFolderForm").ajaxSubmit(function(data) {
			if(testData(data)) {
				bootbox.alert("操作成功");
				($("#newFolderForm"))[0].reset();
		        $("#resourceTable").bootstrapTable('refresh');
			}
		})
		$('#newFolderModal').modal('hide');
	});

	$("#newFolderCancel").click(function() {
		($("#newFolderForm"))[0].reset();
	});
	
	function addPath(id,name){
        //<li id="0"><a href="#" onclick="reloadByPath(this)"><i class="fa fa-home"></i> Home</a></li>
        var oldPath = $("#path").last().childNodes;
        console.log(oldPath);
        alert(oldPath)
	}
	
	function reloadByPath(obj){
		resParId = obj.parentNode.id;
	    $("#resourceTable").bootstrapTable('refresh');
	}
	function test(){}
</script>
</html>

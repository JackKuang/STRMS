<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>系统信息查看</title>
<jsp:include page="/WEB-INF/include/meta.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/css.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/adaptor.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/jquery.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/redirectPage.jsp"></jsp:include>
</head>
<body>
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>属性配置</h1>
	</section>
	<!-- Main content -->
	<section class="content">
		<div class="col-md-3">
			<table class="table table-bordered text-center">
				<tr>
					<td>
						<button type="button" onclick="showBranch();"
							class="btn btn-block btn-primary btn-lg">增加分院</button>
					</td>
				</tr>
				<tr>
					<td>
						<button type="button" onclick="showMajor();"
							class="btn btn-block btn-primary btn-lg">增加专业</button>
					</td>
				</tr>
				<tr>
					<td>
						<button type="button" onclick="showCollective();"
							class="btn btn-block btn-primary btn-lg">增加班级</button>
					</td>
				</tr>
			</table>
		</div>
		<!-- /.col -->
		<div class="col-md-6">
			<div id="treeviewBranch"></div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</section>
	<!-- /.content -->
	<div class="modal" id="branchModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="branchTitle">新增分院</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="branchForm"
						action="branch!save.action">
						<input type="hidden" id="branchId" name="branch.braId" />
						<div class="box-body">
							<div class="form-group">
								<label class="col-sm-2">分院名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="braName"
										name="branch.braName" placeholder="分院名称">
								</div>
							</div>
							<!-- /.box-body -->
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" id="branchSave" class="btn btn-primary">保存</button>
					<button type="button" id="branchCancel" class="btn btn-default"
						data-dismiss="modal">取消</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<div class="modal" id="majorModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="majorTitle">新增专业</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="majorForm"
						action="major!save.action">
						<input type="hidden" id="majorId" name="major.majId" />
						<div class="box-body">
							<div class="form-group">
								<label class="col-sm-2">所属分院</label>
								<div class="col-sm-10">
									<select class="form-control" id="braSelect"
										name="major.majBraId">
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
									<input type="text" class="form-control" placeholder="专业名称"
										id="majName" name="major.majName">
								</div>
							</div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" id="majorSave" class="btn btn-primary">保存</button>
					<button type="button" id="majorCancel" class="btn btn-default"
						data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="collectiveModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="collectiveTitle">新增班级</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="collective!save.action" id="collectiveForm">
						<input type="hidden" id="collectiveId" name="collective.colId" />
						<div class="box-body">
							<div class="form-group">
								<label class="col-sm-2">所属分院</label>
								<div class="col-sm-10">
									<select class="form-control" id="braSelect2" onchange="loadMajorList(this.value)">
										<s:iterator var="branch" value="branchList">
											<option value="<s:property value="#branch.braId" />"
											><s:property
													value="#branch.braName" /></option>
										</s:iterator>
									</select>
								</div>
								<label class="col-sm-2">专业名称</label>
								<div class="col-sm-10">
									<select class="form-control" id="majSelect"
										name="collective.colMajId">
									</select>
								</div>
								<label class="col-sm-2">入学时间</label>
								<div class="col-sm-10">
									<select class="form-control" name="collective.colYear" id="colYear">
										<option>2013</option>
										<option>2014</option>
										<option>2015</option>
										<option>2016</option>
										<option>2017</option>
									</select>
								</div>
								<label class="col-sm-2">班级名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" placeholder="班级名称"
										id="colName" name="collective.colName">
								</div>
							</div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" id="collectiveSave" class="btn btn-primary">保存</button>
					<button type="button" id="collectiveCancel" class="btn btn-default"
						data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$("#branchSave").click(function() {
		$("#branchForm").ajaxSubmit(function(data) {
			if(testData(data)){
				reloadTree();
				bootbox.alert("操作成功");
				($("#branchForm"))[0].reset();
			}
		})
		$('#branchModal').modal('hide');
	});

	$("#majorSave").click(function() {
		$("#majorForm").ajaxSubmit(function(data) {
			if(testData(data)){
				reloadTree();
				bootbox.alert("操作成功");
				($("#majorForm"))[0].reset();
			}
		})
		$('#majorModal').modal('hide');
	});

	$("#collectiveSave").click(function() {
		$("#collectiveForm").ajaxSubmit(function(data) {
			if(testData(data)){
				reloadTree();
				bootbox.alert("操作成功");
				($("#collectiveForm"))[0].reset();
			}
		})
		$('#collectiveModel').modal('hide');
	});
	$("#branchCancel").click(function() {
		($("#branchForm"))[0].reset();
	});
	$("#majorCancel").click(function() {
		($("#majorForm"))[0].reset();
	});
	$("#collectiveCancel").click(function() {
		($("#collectiveForm"))[0].reset();
	});

	function showBranch(braId) {
		if (braId != null) {
			$("#branchTitle").html("修改分院");
			$("#branchId").val(braId);
			$.ajax({
				url : 'branch!getById.action',
				type : 'POST', //GET
				async : true, //或false,是否异步
				dataType : 'json',
				data :{
					'id':braId
				},
				success : function(data, textStatus, jqXHR) {
					if(testData(data)){
						$("#braName").val(data.content.braName);
					}
				}
			})
		} else {
			$("#branchTitle").html("新增分院");
		}
		$('#branchModal').modal('show');
	}

	function showMajor(majId) {
		if (majId != null) {
			$("#majorId").val(majId);
			$("#majorTitle").html("修改分院");
			$.ajax({
				url : 'major!getById.action',
				type : 'POST', //GET
				async : true, //或false,是否异步
				data :{
					'major.majId':majId
				},
				dataType : 'json',
				success : function(data, textStatus, jqXHR) {
					if(testData(data)){
						$("#braSelect").val(data.content.majBraId);
						$("#majName").val(data.content.majName);
					}
				}
			})
		} else {
			$("#majorTitle").html("新增分院");
		}
		$('#majorModal').modal('show');
	}

	function showCollective(colId) {
		if (colId != null) {
			$("#colId").val(colId);
			$("#collectiveTitle").html("修改班级");
			$.ajax({
				url : 'collective!getById.action',
				type : 'POST', //GET
				async : true, //或false,是否异步
				data :{
					'collective.colId':colId
				},
				dataType : 'json',
				success : function(data, textStatus, jqXHR) {
					if(testData(data)){
						$("#braSelect2").val(data.content.colMajBarId);
						loadMajorList(data.content.colMajBarId);
						$("#majSelect").val(data.content.colMajId);
						$("#colYear").val(data.content.colYear);
						$("#colName").val(data.content.colName);
					}
				}
			})
		} else {
			$("#collectiveTitle").html("新增班级");
		}
		$('#collectiveModal').modal('show');
	}

	
	function deleteBranch(braId) {
		bootbox.confirm("确认删除",function(result){
			if(result==true)
				$.ajax({
					url : 'branch!delete.action',
					type : 'POST', //GET
					async : true, //或false,是否异步
					data : {
						'branch.braId' : braId
					},
					dataType : 'json',
					success : function(data, textStatus, jqXHR) {
						if (data.result == "success") {
							bootbox.alert("操作成功");
						} else {
							bootbox.alert("操作失败");
						}
						reloadTree();
					}
				})
		});
	}

	function deleteMajor(majId) {
		bootbox.confirm("确认删除",function(result){
			if(result==true)
				$.ajax({
					url : 'major!delete.action',
					type : 'POST', //GET
					async : true, //或false,是否异步
					data : {
						'branch.braId' : braId
					},
					dataType : 'json',
					success : function(data, textStatus, jqXHR) {
						if (data.result == "success") {
							bootbox.alert("操作成功");
						} else {
							bootbox.alert("操作失败");
						}
						reloadTree();
					}
				})
		});
	}

	function deleteCollective(colId) {
		bootbox.confirm("确认删除",function(result){
			if(result==true)
				$.ajax({
					url : 'collective!delete.action',
					type : 'POST', //GET
					async : true, //或false,是否异步
					data : {
						'branch.braId' : braId
					},
					dataType : 'json',
					success : function(data, textStatus, jqXHR) {
						if (data.result == "success") {
							bootbox.alert("操作成功");
						} else {
							bootbox.alert("操作失败");
						}
						reloadTree();
					}
				})
		});
	}

	
	$(function() {
		reloadTree();
	})
	
	function reloadTree(){
		$.ajax({
			url : 'school!listAll.action',
			type : 'POST', //GET
			async : true, //或false,是否异步
			dataType : 'json',
			success : function(data, textStatus, jqXHR) {
				var content =  formatterData(data.content,1);
				var $tree = $('#treeviewBranch').treeview({
					data : content,
					backColor: 'green'
				})
			}
		})
	}
	
	function formatterData(data,depth){
		var type = ""
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
			if(depth!=3){
				data[i].text = data[i].text +"<div class='box-tools pull-right'><button type='button' onclick='show"+type+"("+data[i].id+")' class='btn btn-box-tool' data-widget='collapse'><i class='fa fa-edit'></i></button>" ;
			}
			if(data[i].nodes==null || data[i].nodes.length==0){
				if(depth!=3){
					data[i].text = data[i].text +"<button type='button' onclick='delete"+type+"("+data[i].id+")' class='btn btn-box-tool' data-widget='collapse'><i class='fa fa-trash-o'></i></button></div>";
				}
				data[i].text = data[i].text ;
				data[i].nodes = null;
			}else{
				if(depth!=3){
					data[i].text = data[i].text +"<button type='button' class='btn btn-box-tool' data-widget='collapse'>&nbsp;&nbsp;&nbsp;&nbsp;</button></div>";
				}
				data[i].nodes =  formatterData(data[i].nodes,depth+1);
			}
		}
		return data;
	}
	function loadMajorList(majBraId){
		$.ajax({
			url : 'major!listByMajBraId.action',
			type : 'POST', //GET
			async : true, //或false,是否异步
			dataType : 'json',
			data : {
				'major.majBraId' : majBraId
			},
			success : function(data, textStatus, jqXHR) {
				if(testData(data)){
					$("#majSelect").empty();
					for(var i=0;i<data.content.length;i++){
					   var option = $("<option>").val(data.content[i].majId).text(data.content[i].majName);
					   $("#majSelect").append(option);
					}
				}
			}
		})
	}
	/* $('#braSelect2').change(function(){
	    var majBraId=$('#braSelect2').val();
	    $.ajax({
			url : 'major!listByMajBraId.action',
			type : 'POST', //GET
			async : true, //或false,是否异步
			dataType : 'json',
			data : {
				'major.majBraId' : majBraId
			},
			success : function(data, textStatus, jqXHR) {
				if(testData(data)){
					$("#majSelect").empty();
					for(var i=0;i<data.content.length;i++){
					   var option = $("<option>").val(data.content[i].majId).text(data.content[i].majName);
					   $("#majSelect").append(option);
					}
				}
			}
		})
	}); */
	
</script>
</html>

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
		<%-- <div class="alert alert-danger alert-dismissible" hidden="true" id="alertInfo">
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">×</button>
			<h4>
				<i class="icon fa fa-ban"></i><span id="alertTitle"></span>
			</h4>
			<span id="alertContent"></span>
		</div> --%>
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
		<div class="col-md-3">
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
								<!-- FIXME -->
							</div>
							<div class="form-group">
								<label class="col-sm-2">专业名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" placeholder="专业名称"
										id="majName" name="major.majName">
								</div>
							</div>
							<!-- /.box-body -->
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" id="majorSave" class="btn btn-primary">保存</button>
					<button type="button" id="majorCancel" class="btn btn-default"
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
					<h4 class="modal-title" id="collectiveTitle">新增班级</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="collective!save.action">
						<input type="hidden" id="collectiveId" name="collective.colId" />
						<div class="box-body">
							<div class="form-group">
								<label class="col-sm-2">所属分院</label>
								<div class="col-sm-10">
									<select class="form-control" id="braSelect2">
										<s:iterator var="branch" value="branchList">
											<option value="<s:property value="#branch.braId" />"
											onclick="loadMajor(<s:property value="#branch.braId" />)"
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
								<label class="col-sm-2">班级名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" placeholder="班级名称"
										id="colName" name="collective.colName">
								</div>
							</div>
							<!-- /.box-body -->
						</div>
					</form>

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
	$("#branchSave").click(function() {
		$("#branchForm").ajaxSubmit(function(data) {
			if(testData(data)){
				reloadTree();
				($("#branchForm"))[0].reset();
			}
		})
		$('#branchModal').modal('hide');
	});

	$("#majorSave").click(function() {
		$("#majorForm").ajaxSubmit(function(data) {
			if(testData(data)){
				reloadTree();
				($("#branchForm"))[0].reset();
			}
		})
		$('#majorModal').modal('hide');
	});

	$("#collectiveSave").click(function() {
		$("#collectiveForm").ajaxSubmit(function(data) {
		})
		$('#collectiveModel').modal('hide');
	});
	

	function showBranch(braId) {
		if (braId != null) {
			$("#braId").val(braId);
			$("#branchTitle").html("修改分院");
		} else {
			$("#branchTitle").html("新增分院");
		}
		$('#branchModal').modal('show');
	}

	function showMajor(majId) {
		if (majId != null) {
			$("#majId").val(majId);
			$("#majorTitle").html("修改分院");
		} else {
			$("#majorTitle").html("新增分院");
		}
		$('#majorModal').modal('show');
	}

	function showCollective(colId) {
		if (colId != null) {
			$("#colId").val(colId);
			$("#collectiveTitle").html("新增班级");
		} else {
			$("#collectiveTitle").html("新增班级");
		}
		$('#collectiveModal').modal('show');
	}

	
	function deleteBranch(braId) {
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
					//删除成功
				} else {
					//删除失败
				}
			}
		})
	}

	function deleteMajor(majId) {
		$.ajax({
			url : 'major!delete.action',
			type : 'POST', //GET
			async : true, //或false,是否异步
			data : {
				'major.majId' : majId
			},
			dataType : 'json',
			success : function(data, textStatus, jqXHR) {
				if (data.result == "success") {
					//删除成功
				} else {
					//删除失败
				}
			}
		})
	}

	function deleteCollective(colId) {
		$.ajax({
			url : 'collective!delete.action',
			type : 'POST', //GET
			async : true, //或false,是否异步
			data : {
				'collective.colId' : colId
			},
			dataType : 'json',
			success : function(data, textStatus, jqXHR) {
				if (data.result == "success") {
					//删除成功
				} else {
					//删除失败
				}
			}
		})
	}

	
	$(function() {
		reloadTree();
		toastr.warning('My name is Inigo Montoya. You killed my father, prepare to die!')
	})
	
	function reloadTree(){
		$.ajax({
			url : 'school!listAll.action',
			type : 'POST', //GET
			async : true, //或false,是否异步
			dataType : 'json',
			success : function(data, textStatus, jqXHR) {
				var $tree = $('#treeviewBranch').treeview({
					data : data.result
				})
			}
		})
	}
	function loadMajor(majBraId){
		$.ajax({
			url : 'major!listBymajBraId.action',
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
	
</script>
</html>

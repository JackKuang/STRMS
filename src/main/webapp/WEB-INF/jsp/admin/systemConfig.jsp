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
		<div class="row">
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
				</table>
			</div>
			<!-- /.col -->
			<div class="col-md-3">
				<s:iterator var="branch" value="branchList">
					<div class="box box-success box-solid">
						<div class="box-header with-border">
							<h3 class="box-title">
								<s:property value="#branch.braName" />
							</h3>
							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool"
									data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
								<button type="button" class="btn btn-box-tool"
									onclick="showBranch(<s:property value="#branch.braId" />)">
									<i class="fa fa-edit"></i>
								</button>
								<button type="button" class="btn btn-box-tool"
									onclick="deleteBranch(<s:property value="#branch.braId" />)">
									<i class="fa fa-close"></i>
								</button>
							</div>
						</div>
						<s:iterator var="major" value="#branch.majors">
							<div class="box-body">
								<s:property value="#major.majName" />
								&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn btn-default btn-sm"
									onclick="showMajor(<s:property value="#major.majId" />)">修改</button>
								&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn btn-default btn-sm"
									onclick="deleteMajor(<s:property value="#major.majId" />)">删除</button>
							</div>
						</s:iterator>
					</div>
				</s:iterator>
				<!-- /.col -->
			</div>
			<!-- /.row -->
	</section>
	<!-- /.content -->
	<div class="modal" id="modalBranch">
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
					<form class="form-horizontal">
						<input type="hidden" id="braId"/>
						<div class="box-body">
							<div class="form-group">
								<label class="col-sm-2">分院名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="braName"
										placeholder="分院名称">
								</div>
							</div>
							<!-- /.box-body -->
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" id="modalBranchSave" class="btn btn-primary">保存</button>
					<button type="button" id="modalBranchCancel"
						class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<div class="modal" id="modalMajor">
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
					<form class="form-horizontal">
						<input type="hidden" id="braName" />
						<div class="box-body">
							<div class="form-group">
								<label class="col-sm-2">所属分院</label>
								<div class="col-sm-10">
									<select class="form-control" id="braSelect">
										<s:iterator var="branch" value="branchList">
											<option value="<s:property value="#branch.braId" />"><s:property value="#branch.braName" /></option>
										</s:iterator>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">专业名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" placeholder="专业名称" id="majName">
								</div>
							</div>
							<!-- /.box-body -->
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" id="modalMajorSave" class="btn btn-primary">保存</button>
					<button type="button" id="modalMajorCancel" class="btn btn-default"
						data-dismiss="modal">取消</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
</body>
<script type="text/javascript">
	$("#modalBranchSave").click(function() {
		$.ajax({
			type : 'POST',
			url : 'branch!save.action',
			data : {
				'branch.braId' : $("#braId").val(),
				'branch.braName' : $("#braName").val()
			},
			dataType : 'json',
			success : function(data, textStatus, jqXHR) {
				console.log(data)
			}
		})
		$('#modalBranch').modal('hide');
	});
	
	$("#modalMajorSave").click(function() {
		$.ajax({
			type : 'POST',
			url : 'major!save.action',
			data : {
				'major.majBraId' : $("#braSelect").val(),
				'major.majId' : $("#majId").val(),
				'major.majName' : $("#majName").val()
			},
			dataType : 'json',
			success : function(data, textStatus, jqXHR) {
				console.log(data)
			}
		})
		$('#modalBranch').modal('hide');
	});
	
	
	function showBranch(braId){
		if(braId!=null){
			$("#braId").val(braId);
			$("#branchTitle").html("修改分院");
		}else{
			$("#branchTitle").html("新增分院");
		}
		$('#modalBranch').modal('show');
	}
	
	function showMajor(majId){
		if(majId!=null){
			$("#majId").val(majId);
			$("#majTitle").html("修改分院");
		}else{
			$("#majTitle").html("新增分院");
		}
		$('#modalMajor').modal('show');
	}
	
	function deleteBranch(braId){
		$.ajax({
		    url:'branch!delete.action',
		    type:'POST', //GET
		    async:true,    //或false,是否异步
		    data:{
		    	'branch.braId':braId
		    },
		    dataType:'json',
		    success:function(data,textStatus,jqXHR){
		    	if(data.result=="success"){
		    		//删除成功
		    	}else{
		    		//删除失败
		    	}
		    }
		})
	}
	function deleteMajor(majId){
		$.ajax({
		    url:'major!delete.action',
		    type:'POST', //GET
		    async:true,    //或false,是否异步
		    data:{
		    	'major.majId':majId
		    },
		    dataType:'json',
		    success:function(data,textStatus,jqXHR){
		    	if(data.result=="success"){
		    		//删除成功
		    	}else{
		    		//删除失败
		    	}
		    }
		})
	}
	$(function(){
		
	})
</script>
</html>

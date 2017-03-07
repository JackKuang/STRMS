<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<h1>系统信息查看</h1>
	</section>

	<!-- Main content -->
	<section class="content">

		<div class="row">
			<div class="col-md-3">
				<table class="table table-bordered text-center">
					<tr>
						<td>
							<button type="button" id="addBranch"
								class="btn btn-block btn-primary btn-lg">增加分院</button>
						</td>
					</tr>
					<tr>
						<td>
							<button type="button" id="addMajor"
								class="btn btn-block btn-primary btn-lg">增加专业</button>
						</td>
					</tr>
				</table>

			</div>
			<!-- /.col -->
			<div class="col-md-3">
				<!-- <table class="table table-bordered text-center">
					<tr>
						<td>
							<div class="box box-success box-solid">
								<div class="box-header with-border">
									<h3 class="box-title">计算机与计算科学学院</h3>
									<div class="box-tools pull-right">
										<button type="button" class="btn btn-box-tool"
											data-widget="collapse">
											<i class="fa fa-minus"></i>
										</button>
									</div>
									/.box-tools
								</div>
								/.box-header
								<div class="box-body">计算机科学与技术</div>
								<div class="box-body">计算机科学与技术</div>
								<div class="box-body">计算机科学与技术</div>
								<div class="box-body">计算机科学与技术</div>
								/.box-body
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="box box-success box-solid">
								<div class="box-header with-border">
									<h3 class="box-title"></h3>
									<div class="box-tools pull-right">
										<button type="button" class="btn btn-box-tool"
											data-widget="collapse">
											<i class="fa fa-minus"></i>
										</button>
									</div>
									/.box-tools
								</div>
								/.box-header
								<div class="box-body">计算机科学与技术</div>
								/.box-body
							</div>
						</td>
					</tr>
				</table> -->

				<div id="tree"></div>
			</div>
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
					<h4 class="modal-title">新增分院</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="box-body">
							<div class="form-group">
								<label class="col-sm-2">分院名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="braName" placeholder="分院名称">
								</div>
							</div>
							<!-- /.box-body -->
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" id="modalBranchSave" class="btn btn-primary">保存</button>
					<button type="button" id="modalBranchCancel" class="btn btn-default" data-dismiss="modal">取消</button>
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
					<h4 class="modal-title">新增专业</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="box-body">
							<div class="form-group">
								<label class="col-sm-2">所属分院</label>
								<div class="col-sm-10">
									<select class="form-control">
										<option>option 1</option>
										<option>option 2</option>
										<option>option 3</option>
										<option>option 4</option>
										<option>option 5</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">专业名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" placeholder="专业名称">
								</div>
							</div>
							<!-- /.box-body -->
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" id="modalMajorCancel" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
					<button type="button" id="modalMajorSave" class="btn btn-primary">保存</button>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
</body>
<script type="text/javascript">
	$("#modalBranchSave").click(function() {
		$.ajax({
			type: 'POST',
			url: 'school!saveBranch.action',
			data: {
				braName:$("#braName").val()
			},
			dataType:'json',
		    success:function(data,textStatus,jqXHR){
		        console.log(data)
		    }
		})	
		$('#modalBranch').modal('hide');
	});
	$("#modalMajorSave").click(function() {
		$('#modalMajor').modal('hide');
	});
	$("#addBranch").click(function() {
		$('#modalBranch').modal('show');
	});
	$("#addMajor").click(function() {
		$('#modalMajor').modal('show');
	});
</script>
</html>

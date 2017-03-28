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
		<h1>教师信息管理</h1>
	</section>

	<!-- Main content -->
	<section class="content">

		<div class="row">
			<div class="col-md-3">
				<table class="table table-bordered text-center">
					<tr>
						<td>
							<button type="button" onclick="showTeacher();"
								class="btn btn-block btn-primary btn-lg">增加教师</button>
						</td>
					</tr>
				</table>
			</div>
			<div class="col-md-12">
			<div class="box">
						<div class="box-header">
							<h3 class="box-title">教师信息</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<table id="teacherTable" class="table table-bordered table-striped dataTable" role="grid">
								<th></th>
							</table>
						</div>
						<!-- /.box-body -->
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
						<h4 class="modal-title" id="teahcherTitle">新增分院</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="teacherForm"
							action="teacher_operate!save.action">
							<input type="hidden" id="teacherId" name="teacher.teaId" />
							<div class="box-body">
								<div class="form-group">
									<label class="col-sm-2">教师编号</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="teaNo"
											name="teacher.teaNo" placeholder="教师编号">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2">教师名称</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="teaName"
											name="teacher.teaName" placeholder="教师名称">
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
										<input type="text" class="form-control" id="teaLevel"
											name="teacher.teaLevel" placeholder="教师职位">
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
</body>
<script type="text/javascript">
	function showTeacher(teaId) {
		if (teaId != null) {
			$("#teacherTitle").html("修改分院");
			$("#teacherId").val(teaId);
			$.ajax({
				url : 'teacher!getById.action',
				type : 'POST', //GET
				async : true, //或false,是否异步
				dataType : 'json',
				data : {
					'teacher.teaId' : teaId
				},
				success : function(data, textStatus, jqXHR) {
					if (testData(data)) {
						$("#braName").val(data.content.braName);
					}
				}
			})
		} else {
			$("#teacherTitle").html("新增分院");
		}
		$('#teacherModal').modal('show');
	}

	$("#teacherSave").click(function() {
		$("#teacherForm").ajaxSubmit(function(data) {
			if (testData(data)) {
				bootbox.alert("操作成功");
				($("#teacherForm"))[0].reset();
			}
		})
		$('#teacherModal').modal('hide');
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
            console.log(params)
             var param = {
                  pageNo: params.pageNumber,    
                  pageSize: params.pageSize,
                  'teacher.teaName':params.searchText
              };
              return param;  
            },  
            columns: [{  
                field: 'teaNo',
                title: '教师编号'
            },{  
                field: 'teaNo',
                title: '姓名'
            },{  
                field: 'teaEmail',
                title: '邮箱'
            },{  
                field: 'teaLevel',
                title: '教师职位'
            },{  
                field: 'teaBraId',
                title: '所属分院'
            }],  
            onLoadSuccess: function(){  //加载成功时执行
            },  
            onLoadError: function(){  //加载失败时执行 
            } 
          });  
      }
</script>
</html>

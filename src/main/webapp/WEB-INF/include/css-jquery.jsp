<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- Tell the browser to be responsive to screen width -->
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet"
	href="<%=basePath%>bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="<%=basePath%>ajax/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="<%=basePath%>ajax/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="<%=basePath%>dist/css/AdminLTE.min.css">
<!-- iCheck -->
<link rel="stylesheet"
	href="<%=basePath%>plugins/iCheck/square/blue.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="<%=basePath%>dist/css/skins/_all-skins.min.css">
<link rel="stylesheet"
	href="<%=basePath%>plugins/toastr/toastr.min.css">
	
<link rel="stylesheet"
	href="<%=basePath%>plugins/bootstrap-select/bootstrap-select.min.css">
<link rel="stylesheet"
	href="<%=basePath%>plugins//bootstrap-table/bootstrap-table.css">

	
	
<!-- jQuery 2.2.3 -->
<script src="<%=basePath%>plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="<%=basePath%>plugins/jQueryForm/jquery.form.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="<%=basePath%>plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="<%=basePath%>dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="<%=basePath%>dist/js/demo.js"></script>
<!-- bootstrap-jquery-plugin插件	 -->
<script src="<%=basePath%>plugins/bootstrap-treeview/bootstrap-treeview.min.js"></script>
<script src="<%=basePath%>plugins/toastr/toastr.min.js"></script>
<script src="<%=basePath%>plugins/bootstrap-select/bootstrap-select.min.js"></script>
<script src="<%=basePath%>plugins/bootbox/bootbox.min.js"></script>

<script src="<%=basePath%>plugins/bootstrap-table/bootstrap-table.js"></script>
<script src="<%=basePath%>plugins/bootstrap-table/bootstrap-table-zh-CN.js"></script>

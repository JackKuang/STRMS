<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
	function redirectPage(flag) {
		//var url = "${sessionScope.userType }+!redirectPage.action?flag="+flag;
		$("#main").load(flag);
	}
</script>
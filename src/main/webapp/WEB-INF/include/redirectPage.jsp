<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
	function testData(data){
		if(data.result=="success"){
			return true;
		}else if(data.result=="01"){
			return false;
		}else{
			return false
		}
	}
	
	/* function alertInfo(type,title,content){
		$("#alertTitle").html(title);
		$("#alertContent").html(content);
		$("#alertInfo").addClass("alert-"+type);
		$("#alertInfo").show();
	} */
	
	
		
</script>
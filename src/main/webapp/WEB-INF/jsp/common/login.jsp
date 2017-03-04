<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${prop.systemName }</title>
<jsp:include page="/WEB-INF/include/meta.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/css.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/adaptor.jsp"></jsp:include>
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<b>${prop.systemName }</b>
		</div>
		<!-- /.login-logo -->
		<c:if test="${wrong==1 }">
			<div class="callout callout-danger">
				<p>错误的用户名或者密码！</p>
			</div>
		</c:if>

		<div class="login-box-body">
			<form action="login!login.action" method="post">
				<div class="form-group has-feedback">
					<input type="text" class="form-control" name="userName"
						placeholder="用户名"> <span
						class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" name="password"
						placeholder="密码"> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<label><input type="radio" name="userType" value="student" class="minimal">学生</label>
					<label><input type="radio" name="userType" value="teacher" class="minimal">教师</label>
					<label><input type="radio" name="userType" value="admin" class="minimal">管理员</label>
				</div>
				<div class="row">
					<div class="col-xs-8"></div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
					</div>
					<!-- /.col -->
				</div>
			</form>
			<!-- /.social-auth-links -->
		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<!-- jQuery 2.2.3 -->
	<script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<!-- iCheck -->
	<script src="plugins/iCheck/icheck.min.js"></script>
	<script>	
		$(function() {
			$("input[name='userType'][value='${userType }']").attr("checked", true);
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' // optional
			});	
		});
	</script>
</body>
</html>

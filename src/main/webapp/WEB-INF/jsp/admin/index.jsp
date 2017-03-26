<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>${admin.admName }</title>
<jsp:include page="/WEB-INF/include/meta.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/css-jquery.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/adaptor.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/redirectPage.jsp"></jsp:include>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<header class="main-header">
			<!-- Logo -->
			<!-- 主页链接  -->
			<a href="admin!index.action" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini">★</span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg">${prop.systemName }</span>
			</a>
			<!-- Header Navbar: style can be found in header.less -->
			<nav class="navbar navbar-static-top">
				<!-- Sidebar toggle button-->
				<!-- FUNCTION 左上角缩小 -->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"> <span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
				</a>

				<!-- FUNCTION	自定义用户菜单  -->
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<!-- Messages: style can be found in dropdown.less-->
						<li class="dropdown messages-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <i
								class="fa fa-envelope-o"></i> <span class="label label-success">4</span>
						</a>
							<ul class="dropdown-menu">
								<li class="header">你有X条消息</li>
								<li>
									<!-- inner menu: contains the actual data -->
									<ul class="menu">
										<li>
											<!-- start message --> <a href="#">
												<div class="pull-left">
													<img src="../dist/img/user2-160x160.jpg" class="img-circle"
														alt="User Image">
												</div>
												<h4>
													丁XX老师 <small><i class="fa fa-clock-o"></i> 1小时之前</small>
												</h4>
												<p>上传了资源xx.avi</p>
										</a>
										</li>
										<!-- end message -->
										<li>
											<!-- start message --> <a href="#">
												<div class="pull-left">
													<img src="../dist/img/user2-160x160.jpg" class="img-circle"
														alt="User Image">
												</div>
												<h4>
													丁XX老师 <small><i class="fa fa-clock-o"></i> 1小时之前</small>
												</h4>
												<p>上传了资源xx.avi</p>
										</a>
										</li>

									</ul>
								</li>
								<li class="footer"><a href="#">查看所有消息</a></li>
							</ul></li>
						<!-- Notifications: style can be found in dropdown.less -->

						<!-- User Account: style can be found in dropdown.less -->
						<li class="dropdown user user-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <img
								src="../dist/img/user2-160x160.jpg" class="user-image"
								alt="User Image"> <span class="hidden-xs">管理员</span></a>
							<ul class="dropdown-menu">
								<!-- User image -->
								<li class="user-header"><img
									src="../dist/img/user2-160x160.jpg" class="img-circle"
									alt="User Image">

									<p>
										管理员 <small>Member since Nov. 2012</small>
									</p></li>

								<!-- Menu Footer-->
								<li class="user-footer">
									<div class="pull-left">
										<a href="#" class="btn btn-default btn-flat">个人信息</a>
									</div>
									<div class="pull-right">
										<a href="#" class="btn btn-default btn-flat">登出</a>
									</div>
								</li>
							</ul></li>
						<!-- Control Sidebar Toggle Button -->
						<li><a></a></li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- Sidebar user panel -->
				<div class="user-panel">
					<div class="pull-left image">
						<img src="../dist/img/user2-160x160.jpg" class="img-circle"
							alt="User Image">
					</div>
					<div class="pull-left info">
						<p>管理员</p>
					</div>
				</div>
				<!-- search form -->
				<!-- /.search form -->
				<!-- sidebar menu: : style can be found in sidebar.less -->
				<ul class="nav sidebar-menu">
					<li class="header">主导航</li>
					<li><a href="#" onclick="redirectPage('systemDetail')"> <i
							class="fa fa-navicon"></i> <span>系统信息查看</span>
					</a></li>
					<li><a href="#" onclick="redirectPage('systemConfig')"> <i
							class="fa fa-wrench"></i> <span>属性配置</span>
					</a></li>
					<li><a href="#" onclick="redirectPage('teacherManager')">
							<i class="fa fa-users"></i> <span>教师管理</span>
					</a></li>
					<li><a href="#" onclick="redirectPage('studentManager')">
							<i class="fa fa-users"></i> <span>学生管理</span>
					</a></li>
				</ul>
			</section>
			<!-- /.sidebar -->
		</aside>


		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Main content -->
			<section>
				<div id="main"></div>
				<!-- /.error-page -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<jsp:include page="/WEB-INF/include/footer.jsp"></jsp:include>
	</div>
	<!-- ./wrapper -->
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('ul.nav > li').click(function(e) {
			e.preventDefault();
			$('ul.nav > li').removeClass('active');
			$(this).addClass('active');
		});
	});
</script>
</html>

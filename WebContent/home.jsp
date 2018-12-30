<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>云盘</title>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/ajax.js"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
<!-- bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://bootswatch.com/4/united/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
	type="text/javascript"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="js/mustache.min.js"></script>
<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	media="all" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/checkbox.css">
<script src="js/mkfoldercheck.js" type="text/javascript"></script>
<script src="js/signupcheck.js" type="text/javascript"></script>
<script src="js/logincheck.js" type="text/javascript"></script>
<script src="js/home.js" type="text/javascript"></script>
</head>

<body>
	<nav class="navbar navbar-expand-sm main-navigation">
		<div class="container">
			<a class="navbar-brand" href="#"> <img src="img/logo.png"
				alt="logo" class="logo"> 在线云盘
			</a>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link nav-current"
					href="Home">首页</a></li>
				<li class="nav-item"><a class="nav-link" href="ListFiles">网盘</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="ShareManage">我的分享</a>
				</li>
				<c:choose>
					<c:when test="${empty name }">
						<!-- 未登录 -->
						<li class="nav-item">
							<button type="button" class="btn btn-primary nav-button"
								data-toggle="modal" data-target="#login">登录</button>
						</li>
						<li class="nav-item">
							<button type="button" class="btn btn-primary nav-button"
								data-toggle="modal" data-target="#signup">注册</button>
						</li>
					</c:when>
					<c:otherwise>
						<!-- 登录成功 -->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbardrop"
							data-toggle="dropdown"> 欢迎您：${name } </a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="DoLogout">退出登录</a>
							</div></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>

	<div class="container">
		<div class="alert alert-primary alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4>云盘系统说明：</h4>
			<h5>&nbsp;&nbsp;1.&nbsp;本系统是一个仿照百度网盘做的一个小型个人云盘，主要功能有登录注册，上传下载文件以及分享文件等。</h5>
			<h5>&nbsp;&nbsp;2.&nbsp;系统前端采用bootstrap框架和Jquery实现基本的布局以及前后交互等。</h5>
			<h5>&nbsp;&nbsp;3.&nbsp;前端上传文件效果实现用的是bootstrap-fileinput组件，感谢高人指点，省去了很多麻烦！
			</h5>
			<h5>&nbsp;&nbsp;3.&nbsp;后端对文件的操作则是用最基本的servlet实现，
				受到网络大牛“孤傲苍狼”的“javaweb学习”总结系列博客第五十 一篇影响颇深，感谢大佬无私分享知识。</h5>

			<p>
				<button type="button" class="btn btn-info" data-dismiss="alert">关闭该说明</button>
			</p>
		</div>

		<div class="topsharetitle">系统状况及留言板：</div>
		<div class="row">
			<div class=" col-md-6">
				<div class=" panel panel-default">
					<div class=" panel-heading"></div>
					<div class=" panel-body">
						<div id="InstituteChart"
							style="width: 500px; height: 460px; min-width: 300px;">
							<script id="template" type="text/x-mustache">
                    {{#isEmpty}}
                        <div class="alert alert-info alert-dismissible pull-left" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                            </button>
                            <strong>系统未使用过哦，赶紧注册使用吧！</strong>
                          </div>
                    {{/isEmpty}}
                    </script>
						</div>
					</div>
				</div>
			</div>
			<div class=" col-md-6">
				<div class=" panel panel-default">
					<div class=" panel-heading"></div>
					<div class=" panel-body">
						<form>
							<div class=" form-group">
								<label for="text">请输入留言内容:</label>
								<textarea id="text" cols="10" rows="4" class=" form-control"
									placeholder="请输入留言内容" value="" required></textarea>
								<button class=" btn btn-primary" type="button"
									style="width: 200px;" onclick="leaveMessage()">留言</button>
							</div>
						</form>
						<div class=" panel panel-default">
							<div class=" panel-heading"></div>
							<div class=" panel-body">
								<ul class=" list-group">
									<li class=" list-group-item">站长：&nbsp;<span
										class="glyphicon glyphicon-user">&nbsp;</span>李嘉坤 魏小奇 陈曦 吕钰双
									</li>
									<li class=" list-group-item">电话：&nbsp;<span
										class="glyphicon glyphicon-bell">&nbsp;</span>17711040973
									</li>
									<li class=" list-group-item">邮箱：&nbsp;<span
										class="glyphicon glyphicon-envelope">&nbsp;</span>1404895946@qq.com
									</li>
									<li class=" list-group-item">QQ：&nbsp;&nbsp;<span
										class="glyphicon glyphicon-user">&nbsp;</span>1404895946
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="topsharetitle">CloudDrive最热分享（被下载次数最多的文件将会显示在下方）：</div>
		<div class="row">
			<c:forEach var="share" items="${shares}">
				<div class="card-container col-sm-4">
					<div class="card">
						<div class="card-body">
							<div class="row">
								<div class="col-xl-3"></div>
								<div class="col-xl-6">
									<img class="home_icon" src="img/icon/${share.type }.png">
								</div>
							</div>
							<div class="row">

								<div class="col-sm-12">
									<div class="row" style="padding-top: 15px">
										<div class="col-sm-3">文件名</div>
										<div class="col-sm-9 ellipsis">
											<a href="Share?key=${share.key }" target="_blank"
												class="fileName" title="${share.fileName}">${share.fileName}</a>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-3">下载数</div>
										<div class="col-sm-9">${share.downloads }</div>
									</div>
									<div class="row">
										<div class="col-sm-3">大小</div>
										<div class="col-sm-9">${share.showSize }</div>
									</div>
									<div class="row">
										<div class="col-sm-3">分享者</div>
										<div class="col-sm-9">${share.user }</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--card-container -->
			</c:forEach>
		</div>

		<div class=" row">
			<div class="col-md" style="text-align: center">
				<p>
					Copyright&nbsp;@&nbsp;2018-12-28&nbsp; <br>
					四川师范大学&nbsp;计算机科学学院&nbsp; <br>
				</p>
			</div>
		</div>
	</div>

	<!-- 登录模态框  -->
	<div class="modal fade" id="login">
		<div class="modal-dialog">
			<div class="modal-content">
				<form class="form-horizontal" id="loginForm" method="post"
					action="DoLogin" role="form">
					<div class="row">
						<div class="col-md-offset-6 col-md-12">
							<span class="heading">登录</span>
							<div class="form-group">
								<input type="text" class="form-control" id="userName"
									name="userName" placeholder="用户名"> <i
									class="fa fa-user"></i>
							</div>
							<div class="form-group">
								<input type="password" class="form-control" id="pwd" name="pwd"
									placeholder="密　码"> <i class="fa fa-lock"></i>
							</div>
							<div class="form-group"></div>
						</div>
					</div>
					<!-- 模态框底部 -->
					<div class="modal-footer">
						<input type="submit" class="btn btn-success " value="登录">
						<button type="button" class="btn btn-success "
							data-dismiss="modal">取消</button>
					</div>
					<c:if test="${not empty message }">
						<div class="alert alert-warning" id="loginError">
							<strong>${message }</strong>
						</div>
					</c:if>
				</form>
			</div>
		</div>
	</div>

	<!-- 注册模态框  -->
	<div class="modal fade" id="signup">
		<div class="modal-dialog">
			<div class="modal-content">
				<form class="form-horizontal" id="signupForm" method="post"
					action="DoRegister" role="form">
					<!-- 模态框主体 -->
					<div class="row">
						<div class="col-md-offset-6 col-md-12">
							<span class="heading">用户注册</span>
							<div class="form-group">
								<input type="text" class="form-control" id="ruserName"
									name="ruserName" placeholder="用户名"> <i
									class="fa fa-user"></i>
							</div>
							<div class="form-group">
								<input type="password" class="form-control" id="rpwd"
									name="rpwd" placeholder="密码"> <i class="fa fa-lock"></i>
								<!--<a href="#" class="fa fa-question-circle"></a>-->
							</div>
							<div class="form-group">
								<input type="password" class="form-control" id="rrepwd"
									name="rrepwd" placeholder="再次输入密码"> <i
									class="fa fa-lock"></i>
							</div>
						</div>
					</div>
					<!-- 模态框底部 -->
					<div class="modal-footer">
						<input type="submit" class="btn btn-info" value="注册">
						<button type="button" class="btn btn-info" data-dismiss="modal">取消</button>
					</div>
					<c:if test="${not empty rmessage }">
						<div class="alert alert-danger" id="registerError">
							<strong>${rmessage }</strong>
						</div>
					</c:if>
				</form>
			</div>
		</div>
	</div>

	<!-- 留言成功模态框 -->
	<div class="modal fade" id="leaveMessageSuccess">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">留言成功</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<h5>感谢您的宝贵意见！</h5>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 留言失败模态框 -->
	<div class="modal fade" id="leaveMessageFailed">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">留言失败</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<script src="js/echars/echarts.min.js"></script>
	<script src="js/echars/westeros.js"></script>
	<script>
		$.getJSON("EcharsData", function(result) {
			console.log(result);
			var templete = $('#template').html();
			Mustache.parse(templete);
			let resultHtml = Mustache.render(templete, result);
			$('#InstituteChart').html(resultHtml);
			if (!result.isEmpty) {
				// echars统计系统数据
				var InstituteChart_ = echarts.init(document
						.getElementById('InstituteChart'), "westeros");
				console.log(InstituteChart_);
				InstituteChart_.showLoading();
				var option = {
					title : {
						text : '系统使用状况:'
					},
					tooltip : {},
					legend : {
						data : [ '访客量' ]
					},
					xAxis : {
						data : [ "用户总数", "文件总数", "分享文件数", "下载次数" ]
					},
					yAxis : {

					},
					series : [ {
						name : '数量',
						type : 'bar',
						data : result.data
					} ]
				};
				InstituteChart_.setOption(option);
				InstituteChart_.hideLoading();
			}
		});

		function leaveMessage() {
			var message = $("#text").val();
			if (message.length <= 0) {
				$("#leaveMessageFailed p").html("<h5>不好意思，留言不能为空哦！</h5>");
				$('#leaveMessageFailed').modal('show')
			} else {
				$.getJSON("LeaveMessage", {
					leaveMessage : message
				}, function(json) {
					if (json.noUser) {
						$("#leaveMessageFailed p").html(
								"<h5>不要意思，登录后才能留言哦！</h5>");
						$('#leaveMessageFailed').modal('show')
					} else {
						$('#leaveMessageSuccess').modal('show')
					}
				});
			}

		}
	</script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>在线云盘</title>
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
<!-- fileinput -->
<link href="css/fileinput.css" media="all" rel="stylesheet"
	type="text/css" />
<script src="js/fileinput.min.js" type="text/javascript"></script>
<script src="js/locales/zh.js" type="text/javascript"></script>
<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	media="all" rel="stylesheet" type="text/css" />
<script src="themes/fa/theme.js" type="text/javascript"></script>
<!-- clipboard -->
<script src="js/clipboard.min.js"></script>
<!--  -->
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/checkbox.css">
<script src="js/checkbox.js" type="text/javascript"></script>
<script src="js/myShare.js" type="text/javascript"></script>
</head>

<body>
	<nav class="navbar navbar-expand-sm main-navigation">
		<div class="container">
			<a class="navbar-brand" href="#"> <img src="img/logo.png"
				alt="logo" class="logo"> 在线云盘
			</a>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="Home">首页</a></li>
				<li class="nav-item"><a class="nav-link" href="ListFiles">网盘</a>
				</li>
				<li class="nav-item"><a class="nav-link nav-current"
					href="ShareManage">我的分享</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> ${name } </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="DoLogout">退出登录</a>
					</div></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-sm-10">
						<h4>我的分享</h4>
					</div>
					<div class="col-sm-2">
						<button type="button" class="btn btn-outline-warning checkShow"
							data-toggle="modal" data-target="#delInfoModal">
							<i class="fa fa-ban fa-lg"></i> 取消分享
						</button>
					</div>
				</div>

				<table class="table table-hover">
					<thead>
						<tr>
							<th>
								<div class="main-checkbox">
									<input type="checkbox" value="None" id="CheckAll" name="check" />
									<label for="CheckAll"></label>
								</div>
							</th>
							<th>文件名</th>
							<th></th>
							<th>下载次数</th>
							<th>大小</th>
							<th>分享时间</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="index" value="0" />
						<c:forEach var="share" items="${shares}">
							<c:set var="index" value="${index+1}" />
							<tr>
								<td>
									<div class="main-checkbox">
										<input type="checkbox" value="None" id="check${index }"
											name="subBox" /> <label for="check${index }"></label>
									</div>
								</td>
								<td><img src="img/icon/${share.type }.png" alt="logo"
									class="fileIcon"> <a href="Share?key=${share.key }"
									target="_blank" class="fileName">${share.fileName}</a>&nbsp&nbsp
								</td>
								<td>
									<div class="param">
										<span class="key">${share.key}</span>
									</div>
								</td>
								<td>${share.downloads }</td>
								<td>${share.showSize }</td>
								<td>${share.shareTime }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- 提示信息 -->
	<div class="modal fade" id="delInfoModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">取消分享</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body">
					<i class="fa fa-info-circle"></i> 确定取消选中文件？
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-danger" id="confirmDeletion">确定</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
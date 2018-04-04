<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/view/common/tags.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style>
table {
	margin: 0 auto;
	text-align: center;
}
table tr td{
	border: 1px solid #ccc;
	padding: 3px 5px;
	min-width: 400px;
}
</style>
</head>
<body style="background-color:#fff;">
	<div class="page-middle">
		<jsp:include page="common/header2.jsp?active=0"></jsp:include>
		<div id="index-content">
			<table>
				<tr>
					<td>账号:</td><td>${user.username}</td>
				</tr>
				<tr>
					<td>昵称:</td><td>${user.nickname}</td>
				</tr>
				<tr>
					<td>创建时间:</td><td>${user.createTime}</td>
				</tr>
				<tr>
					<td>上次登录时间:</td><td>${user.lastLoginTime}</td>
				</tr>
				<tr>
					<td>上次登录IP:</td><td>${user.lastLoginIp}</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
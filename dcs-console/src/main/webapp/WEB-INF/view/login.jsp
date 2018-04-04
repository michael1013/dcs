<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/view/common/tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${ctxStatic}/common/js/common.js"></script>
<style>
body {
	background-color: rgb(122, 183, 255);
}
.form-control{
	width: 100%;
}
#form-div {
	margin: 0 auto;
	width: 600px;
	border: 2px solid #000;
	border-radius: 5px;
	padding: 10px 0;
	box-sizing: border-box;
	background-color: #fff;
}
</style>
</head>
<body>
	<div style="margin-top: 100px; height: 400px;">
		<div style="width: 500px; margin: 0 auto;">
			<img src="${ctxStatic}/common/img/logo.png">
			<form id="loginForm" action="${ctx}/loginForm" method="post">
				<div style="background-color: #fff;padding:30px 40px;border-radius:5px;box-sizing:border-box;">
					<p><input type="text" name="username" class="form-control" placeholder="用户名"/></p>
					<p><input type="password" name="password" class="form-control" placeholder="密码"/></p>
					<p>
						<div class="validecode">
							<img src="${ctx}/vc">
							<input type="text" name="vc" class="fr form-control" placeholder="验证码" style="width:305px;"/>
						</div>
					</p>
					<p><input type="button" class="btn btn-primary" data-loading-text="Loading..." onclick="login()" value="登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录" class="w100p btn btn-default" style="background-color:rgb(122, 183, 255);color:#fff;width:100%;"/></p>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(".validecode>img").click(function() {
			$(this).attr("src", ctx + "/vc?"+Math.random());
		});
		$("input[type=text]").keyup(function(e){
			if (e.keyCode == 13) {
				login();
			}
		});
		function login() {
			var action = $("#loginForm").attr("action");
			var username = $("#loginForm input[name=username]").val();
			var password = $("#loginForm input[name=password]").val();
			var vc = $("#loginForm input[name=vc]").val();
			var data = {"username":username,"password":password,"vc":vc};
			$.post(action,data, function(r){
				if (r == "vc") {
					alert("验证码错误");
				} else if (r == "login") {
					alert("用户名或密码错误");
				} else if (r == "index") {
					window.location.href=ctx+"/index";
				} else {
					alert("未知错误");
				}
			});
		}
	</script>
</body>
</html>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<style>
#index-header{
	color: #f1f1f1;
}
#index-header>div>ul {
	margin-bottom: 0px;
}
#index-header>div>ul>li {
	display: inline-block;
}
#index-header>div>ul>li>a {
	width: 70px;
	display: inline-block;
	text-align:center;
	color: #fff;
}
#index-header>div>ul>li>a:hover {
	background-color: rgb(112, 173, 245);
}
</style>
<div>
	<div id="index-header" class="" style="line-height:100px;padding:0px 40px;background-color:rgb(122, 183, 255);">
		<div class="fl">
			<a href="javascript:void(0);" class="fl dib" style="vertical-align:middle;min-width:90px;height:100px;">
				&nbsp;<img src="${ctxStatic}/common/img/logo.png">
			</a>
			<div class="clear"></div>
		</div>
		<div class="fr" style="font-size:13px;">
			<a href="javascript:void(0);" style="width：auto;color:#fff;">${sessionScope.usersession_key.nickname},欢迎您！</a>
			<a href="${ctx}/logout" style="color:#fff;">退出</a>
		</div>
		<div class="clear"></div>
	</div>
	<div id="index-nav">
		<nav class="navbar navbar-default" role="navigation">
		    <div class="container-fluid">
			    <div class="navbar-header">
			        <span class="navbar-brand">管理台</span>
			    </div>
			    <div id="myexample">
			        <ul class="nav navbar-nav">
			            <li>
			                <a href="${ctx}/index">首页</a>
			            </li>
			            <li>
	                        <a href="${ctx}/user/page">用户管理</a>
	                    </li>
	                    <li class="divider"></li>
	                     <li>
	                        <a href="${ctx}/app/page">应用管理</a>
	                    </li>
	                    <li class="dropdown">
			                <a href="#" class="dropdown-toggle" data-toggle="dropdown">数据统计<b class="caret"></b>
			                </a>
			                <ul class="dropdown-menu">
			                    <li>
			                        <a href="${ctx}/user/data">登录数据</a>
			                    </li>
			                    <li>
			                        <a href="${ctx}/log/recharge/page">充值数据</a>
			                    </li>
			                    <li class="divider"></li>
			                    <li>
			                        <a href="${ctx}/log/recharge/rankPage">排行榜</a>
			                    </li>
			                </ul>
			            </li>
			        </ul>
			    </div>
			</div>
		</nav>
	</div>
</div>
<script type="text/javascript">
// alert(${param.active});
$("#myexample>ul>li:eq(${param.active})").addClass("active");
function go(action){ 
	$.post(ctx+action,{},function(r){
		$("#index-content").html(r);
	});
}
</script>

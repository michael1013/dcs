<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@include file="/WEB-INF/view/common/tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>SDK Console</title>
	<link href="${ctxStatic}/dist/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<style>
	#user-list {
		min-height: 300px;
	}
	#user-list tr td {
		padding: 5px;
	}
	.table-responsive {
		position: relative;
	}
	#table-cover {
		display: none;
		position: absolute;
		width: 100%;
		height: 100%;
		z-index:99;
	}
	.list-group-item-title {
		display: inline-block;
		width: 100px;
		text-align: right;
		margin-right: 20px;
	}
	.list-group-item-content {
		display: inline-block;
		width: 160px;
	}
	.panel {
		margin-bottom: 0px;
	}
	.table-filter {
		border-top: 1px solid #ccc;
		border-bottom: 1px solid #ccc;
		line-height: 50px;
		box-sizing: border-box;
	}
	.form-control {
		width: 180px;
		display: inline-block;
	}
	.input-append {
		width: 180px;
		display: inline-block;
	}
	</style>
</head>
<body>
	<div class="page-middle">
		<jsp:include page="../common/header2.jsp?active=1"></jsp:include>
		<div id="index-content" class="pr">
			<div class="div-cover"></div>
			<div class="table-responsive">
				<div id="table-cover">
					<img src="${ctxStatic}/common/img/loading.gif" style="position: absolute;top: 50%;left: 47%;">
				</div>
				<jsp:include page="../common/filter.jsp?packall=1&server=1&date=1"></jsp:include>
				<div id="user-list">
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript" src="${ctxStatic}/dist/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${ctxStatic}/dist/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	
	<script type="text/javascript">
	$(function(){
	});
	function page(pageNo, pageSize){
		findList({}, pageNo, pageSize);
	}
	function findList(json, pageNo, pageSize){
		pageNo = parseInt(pageNo);
		pageSize = parseInt(pageSize);
		pageNo = isNaN(pageNo) ? 1 : pageNo;
		pageSize = isNaN(pageSize) ? 20 : pageSize;

		$("#table-cover").show();
		var isContractor = 0${param.isContractor};
		var online = parseInt(0${param.online});
		if(isContractor == 1){
			json.isContractor = 1;
		}
		json.gameid = $(".filter-menu-title[f-name=game]").attr("s-id");
		json.serverid = $(".filter-menu-title[f-name=server]").attr("s-id");
		json.userid = $("input[name=userid]").val();
		json.pageNo = pageNo;
		json.pageSize = pageSize;
		json.begin = $("#time-begin").val();
		json.end = $("#time-end").val();
		purl = ctx+"/log/recharge/rankList";
		$.ajax({
			url:purl,
			data: json,
			async:true,
			success:function(r){
				$("#user-list").html(r);
				setTimeout(function(){$("#table-cover").hide();}, 50);
			},
			error:function(r){
				alert("操作异常, 请联系维护人员!");
			}
		});
	}
	</script>
	
</body>
</html>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@include file="/WEB-INF/view/common/tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>SDK Console</title>
	<link href="${ctxStatic}/dist/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<link href="${ctxStatic}/common/css/common.css" rel="stylesheet" media="screen">
	<link href="${ctxStatic}/common/css/filter.css" rel="stylesheet" media="screen">
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
	.icon-plus, .icon-copy, .icon-edit{
		cursor: pointer;
		color: rgb(122, 183, 255);
	}
	.icon-plus{
		display: inline-block;
		line-height: 50px;
	}
	</style>
</head>
<body>
	<div class="page-middle">
		<jsp:include page="../common/header2.jsp?active=5"></jsp:include>
		<div id="index-content" class="pr">
			<div class="div-cover"></div>
			<div class="table-responsive"> 
				<div id="table-cover"></div>
				<div class="table-filter">
					<span>游戏:</span>
					<div class="filter-menu">
						<div class="filter-menu-title" f-name="game" s-id=""><span>游戏(${fn:length(gameList)})</span><b class="caret"></b></div>
						<ul class="filter-menu-ul filter-menu-game">
							<c:forEach items="${gameList}" var="game">
								 <li s-id="${game.gameid}" s-account="${game.account}">${game.gameName}</li>
							</c:forEach>
						</ul>
					</div>
					<span class="btn btn-primary fr" onclick="files()" style="margin-top:7px;">文件管理</span>
				</div>
				<div id="user-list">
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="${ctxStatic}/dist/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${ctxStatic}/dist/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script src="${ctxStatic}/common/js/filter.js"></script>
	<script type="text/javascript">
	$(function(){
		$(".filter-menu-game>li").click(function(){
			var account = $(this).attr("s-account");
			var j = {};
			j.account = account;
			findList(j, 0, 0);
		});
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
		json.orderBy = getOrderBy();
		json.gameid = $(".filter-menu-title[f-name=game]").attr("s-id");
		json.pageNo = pageNo;
		json.pageSize = pageSize;
		json.dateTime = $("#time-date").val();
		purl = ctx+"/option/fileLoadList";
		$.ajax({
			url:purl,
			data: json,
			async:true,
			success:function(r){
				$("#user-list").html(r);
				setTimeout(function(){$("#table-cover").hide();}, 50);
			}
		});
	}
	function getOrderBy(){
		var orderBy = "";
		if ($(".glyphicon-sort-by-attributes").html() != undefined) {
			orderBy = $(".glyphicon-sort-by-attributes").parent().attr("order-by") + " ASC";
		}else if($(".glyphicon-sort-by-attributes-alt").html() != undefined){
			orderBy = $(".glyphicon-sort-by-attributes-alt").parent().attr("order-by") +" DESC";
		}
		return orderBy;
	}
	function files(){
		$.post(ctx+"/option/fileLoadDetail",{},function(r){
			openCover("文件管理", r);
		});
	}
	function addMission(){
		
	}
	</script>
	
	<script type="text/javascript" src="${ctxStatic}/common/js/datetimepicker.js"></script>
</body>
</html>
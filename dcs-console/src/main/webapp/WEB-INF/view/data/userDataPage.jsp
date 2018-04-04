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
		<jsp:include page="../common/header2.jsp?active=4"></jsp:include>
		<div id="index-content" class="pr">
			<ul class="nav nav-tabs">
				<li class="active" s-url="findNew"><a href="###">用户新增</a></li>
				<li s-url="findActive"><a href="###">用户激活</a></li>
				<li s-url="findActive"><a href="###">用户活跃</a></li>
				<li s-url="findStay"><a href="###">用户留存</a></li>
			</ul>
			<script>
			$(function(){
				$(".nav-tabs>li").click(function(){
					$(".nav-tabs>li").removeClass("active");
					$(this).addClass("active");
					page();
				});
			});
			</script>
			<div class="div-cover"></div>
			<div class="table-responsive">
				<div id="table-cover">
					<img src="${ctxStatic}/common/img/loading.gif" style="position: absolute;top: 50%;left: 47%;">
				</div>
				<jsp:include page="../common/filter.jsp?server=1&date=1"></jsp:include>
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
		json.orderBy = getOrderBy();
		json.gameid = $(".filter-menu-title[f-name=game]").attr("s-id");
		if (json.gameid) {
			
		}else{
			$("#table-cover").hide();
			return;
		}
		json.serverid = $(".filter-menu-title[f-name=server]").attr("s-id");
		json.pageNo = pageNo;
		json.pageSize = pageSize;
		json.begin = $("#time-begin").val();
		json.end = $("#time-end").val();
		purl = ctx+"/data/" + $(".nav-tabs .active").attr("s-url");
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
	function getOrderBy(){
		var orderBy = "";
		if ($(".glyphicon-sort-by-attributes").html() != undefined) {
			orderBy = $(".glyphicon-sort-by-attributes").parent().attr("order-by") + " ASC";
		}else if($(".glyphicon-sort-by-attributes-alt").html() != undefined){
			orderBy = $(".glyphicon-sort-by-attributes-alt").parent().attr("order-by") +" DESC";
		}
		return orderBy;
	}
	</script>
	
	<script type="text/javascript">
	$(function(){
		$('.form_date').datetimepicker({
	        language:  'zh-CN',
	        format: 'yyyy-mm-dd',
	        initialDate: new Date(),
	        weekStart: 1,
	        todayBtn:  "linked",
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
	    });
	});
	</script>
	
</body>
</html>
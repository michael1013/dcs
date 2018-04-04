<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@include file="/WEB-INF/view/common/tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>SDK Console</title>
	<link href="${ctxStatic}/dist/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<script>var ctxName = "user";</script>
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
	.glyphicon-edit, .glyphicon-ok, .glyphicon-remove, .icon-ok, .icon-undo, .glyphicon-plus{
		cursor : pointer;
	}
	</style>
</head>
<body>
	<div class="page-middle">
		<jsp:include page="../common/header.jsp?active=1"></jsp:include>
		<div id="index-content" class="pr">
			<div class="div-cover"></div>
			<div class="table-responsive">
				<div id="table-cover">
					<img src="${ctxStatic}/common/img/loading.gif" style="position: absolute;top: 50%;left: 47%;">
				</div>
				<div class="table-filter">
					<div class="filter-menu fr" id="filter-list">
						<span><input type="text" name="userid" class="form-control" placeholder="userid/username"></span>
						<span><input type="button" onclick="page()" value="搜索" class="btn btn-primary"></span>
					</div>
					<div class="clear"></div>
				</div>
				<div id="user-list">
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="${ctxStatic}/dist/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${ctxStatic}/dist/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	
	<script type="text/javascript">
	$(function(){
		page(1, 5);
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
		json.orderBy = getOrderBy();
		json.pageNo = pageNo;
		json.pageSize = pageSize;
		json.id = $("#filter-list").find("input[name=id]").val();
		purl = ctx+"/"+ctxName+"/list";
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
	function detail(id){
		$.post(ctx+"/"+ctxName+"/get",{"userid":id},function(r){
			var title = "ID:"+id;
		    openCover(title, r);
		})
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
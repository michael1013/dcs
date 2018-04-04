<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@include file="/WEB-INF/view/common/tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>SDK Console</title>
	<link href="${ctxStatic}/dist/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<script src="${ctxStatic}/dist/js/echarts.min.js"></script>
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
		<jsp:include page="../common/header.jsp?active=3"></jsp:include>
		<div id="index-content" class="pr">
			<div class="div-cover"></div>
			<div class="table-responsive">
				<div id="table-cover">
					<img src="${ctxStatic}/common/img/loading.gif" style="position: absolute;top: 50%;left: 47%;">
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
		_new();
	});
	function _new(){
		$.post(ctx+"/"+ctxName+"/new",function(r){
			$("#user-list").html(r);
		});
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
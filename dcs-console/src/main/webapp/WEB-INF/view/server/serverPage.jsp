<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@include file="/WEB-INF/view/common/tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>SDK Console</title>
	<link href="${ctxStatic}/dist/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<link href="${ctxStatic}/common/css/filter.css" rel="stylesheet" media="screen">
	<style>
	.icon-plus, .icon-copy, .icon-edit{
		cursor: pointer;
		color: rgb(122, 183, 255);
	}
	.icon-plus{
		display: inline-block;
		line-height: 50px;
	}
	.open-cover-span {
		display: inline-block;
	}
	.open-cover-input {
		margin: auto 2px;
		width: 175px;
	}
	</style>
</head>
<body>
	<div class="page-middle">
		<jsp:include page="../common/header2.jsp?active=3"></jsp:include>
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
								 <li s-id="${game.gameid}">${game.gameName}</li>
							</c:forEach>
						</ul>
					</div>
					<%-- 
					<span class="icon-plus fr" title="新增" onclick="addServer('新增')"></span>
					--%>
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
		json.pageNo = pageNo;
		json.pageSize = pageSize;
		json.begin = $("#time-begin").val();
		json.end = $("#time-end").val();
		purl = ctx+"/server/list";
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
	$(".filter-menu-game>li").click(function(){
		var gameid = $(this).attr("s-id");
		var j = {};
		j.gameid = gameid;
		findList(j, 0, 0);
	});
	function addServer(title, op) {
		var $1 = document.createElement("div");
		$1.id = "add-server-div";
		
		var $99 = document.createElement("input");
		$99.setAttribute("type", "button");
		$99.setAttribute("value", "提交");
		$99.setAttribute("onClick", "submitServer(this)");
		$99.className = "btn btn-primary fr";
		var $099 = document.createElement("p");
		$099.setAttribute("style","height:30px");
		$099.appendChild($99);
		$1.appendChild($099);
		$1.appendChild(serverHTML(op, 0));
		openCover(title, $1);
	}
	function addCopy(o){
		var length = $("#add-server-div>p").length;
		$("#add-server-div").append(serverHTML({}, length - 1));
	}
	function submitServer(){
		var op = {};
		$("#add-server-div>p").find("input:text").each(function(){
			op[$(this).attr("name")] = $(this).val();
		});
		$.post(ctx+"/server/add",op,function(r){
			alert(r);
		});
	}
	var serverHTML = function(op, count){
		count = count || 0;
		op = op || {};
		var gameid = op.gameid || "";
		var gameName = op.gameName || "";
		var serverid = op.serverid || ""
		var serverName = op.serverName || "";
		var openTime = op.openTime || "";
		
		var $20 = document.createElement("span");
		$20.className = "open-cover-span";
		$($20).html("游戏名:");
		var $21 = document.createElement("input");
		$21.setAttribute("type", "text");
		$21.setAttribute("name", "gameName");
		$21.setAttribute("value", gameName);
		$21.className = "open-cover-input form-control";
		
		var $22 = document.createElement("span");
		$22.className = "open-cover-span";
		$($22).html("游戏ID:");
		var $23 = document.createElement("input");
		$23.setAttribute("type", "text");
		$23.setAttribute("name", "gameid");
		$23.setAttribute("value", gameid);
		$23.className = "open-cover-input form-control";
		
		var $24 = document.createElement("span");
		$24.className = "open-cover-span";
		$($24).html("服务器名:");
		var $25 = document.createElement("input");
		$25.setAttribute("type", "text");
		$25.setAttribute("name", "serverName");
		$25.setAttribute("value", serverName);
		$25.className = "open-cover-input form-control";
		
		var $26 = document.createElement("span");
		$26.className = "open-cover-span";
		$($26).html("服务器ID:");
		var $27 = document.createElement("input");
		$27.setAttribute("type", "text");
		$27.setAttribute("name", "serverid");
		$27.setAttribute("value", serverid);
		$27.className = "open-cover-input form-control";
		
		var $28 = document.createElement("span");
		$28.className = "open-cover-span";
		$($28).html("开服时间:");
		var $29 = CommonUI.dateInput(false,"openTime",openTime);
		
		
		var $01 = document.createElement("p");
		
		if (op.sid) {
			var $30 = document.createElement("input");
			$30.setAttribute("type", "hidden");
			$30.setAttribute("name", "id");
			$30.setAttribute("value", op.sid);
			$30.className = "open-cover-input form-control";
			$01.appendChild($30);
		}
		$01.appendChild($20);
		$01.appendChild($21);
		$01.appendChild($22);
		$01.appendChild($23);
		$01.appendChild($24);
		$01.appendChild($25);
		$01.appendChild($26);
		$01.appendChild($27);
		$01.appendChild($28);
		$01.appendChild($29);
		
// 		var $99 = document.createElement("input");
// 		$99.setAttribute("type", "button");
// 		$99.setAttribute("value", "复制新增");
// 		$99.setAttribute("onClick", "addCopy(this)");
// 		$99.className = "btn btn-primary fr";
// 		$01.appendChild($99);
		
		return $01;
	}
	</script>
	
	<script type="text/javascript" src="${ctxStatic}/common/js/datetimepicker.js"></script>
</body>
</html>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE div PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/common/css/filter.css" rel="stylesheet" media="screen">
<div class="table-filter">
	<c:if test="${param.server == 1}">
		<span>游戏:</span>
		<div class="filter-menu">
			<div class="filter-menu-title" f-name="gamelist" s-id=""><span>游戏(${fn:length(gameList)})</span><b class="caret"></b></div>
			<ul class="filter-menu-ul filter-menu-gamelist">
				<c:set var="gamelist" value=""></c:set>
				<c:forEach items="${gameList}" var="game">
					<c:if test="${!fn:contains(gamelist, game.gameName)}">
						<li s-id="${game.gameid}">${game.gameName}</li>
					</c:if>
					<c:set var="gamelist" value="${gamelist},${game.gameName}"></c:set>
				</c:forEach>
				<c:if test="${param.all == 1}">
					<li onclick='$(".filter-menu-title[f-name=game]").attr("s-id", "total");$(".filter-menu-title[f-name=server]").attr("s-id","");page(1, 20);'>全部</li>
				</c:if>
			</ul>
		</div>
		<span>包:</span>
		<div class="filter-menu" id="pack-list">
			<div class="filter-menu-title" f-name="game" g-id=""><span>包</span><b class="caret"></b></div>
			<ul class="filter-menu-ul filter-menu-game">
				<c:set var="gameids"></c:set>
				<c:forEach items="${gameList}" var="game">
					 <li g-name="${game.gameName}" s-id="${game.gameid}" style="display:none;">${game.packName}</li>
					 <c:set var="gameids" value="${gameids},${game.gameid}"></c:set>
				</c:forEach>
			</ul>
		</div>
		<span>服务器:</span>
		<div class="filter-menu" id="server-list">
			<div class="filter-menu-title" f-name="server" s-id=""><span>服务器</span><b class="caret"></b></div>
			<ul class="filter-menu-ul filter-menu-server">
				<c:forEach items="${serverList}" var="server">
					 <li g-id="${server.gameid}" s-id="${server.serverid}" style="display:none;">${server.serverName}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<c:choose>
		<c:when test="${param.date == 'm' }">
			<span>时间:</span>
			<div class="input-append date" data-date="05-2017" data-date-format="mm-yyyy">
			    <input class="form-control span2 form_month" size="16" type="text" readonly id="time-month">
			    <span class="add-on"><i class=""></i></span>
			</div>
			<input type="hidden" id="time-ismonth" value="true">
			<input type="hidden" id="time-begin" value="">
			<input type="hidden" id="time-end" value="">
			<script>$(function(){
				$("#time-month").val(getMonthDate());
				setTimeout(function(){
					$("#time-begin").val(getMonthDateBegin());
				}, 500);
			})</script>
		</c:when>
		<c:otherwise>
			<span>时间:</span>
			<div class="input-append date" data-date="01-01-2017" data-date-format="dd-mm-yyyy">
			    <input class="form-control span2 form_date" size="16" type="text" readonly id="time-begin">
			    <span class="add-on"><i class=""></i></span>
			</div>
			-
			<div class="input-append date" data-date="01-01-2012" data-date-format="dd-mm-yyyy">
			    <input class="form-control span2 form_date" size="16" type="text" readonly id="time-end">
			    <span class="add-on"><i class=""></i></span>
			</div>
		</c:otherwise>
	</c:choose>
<%-- 
	<c:if test="${param.user == 1}">
		<span>用户:</span>
		<input type="text" class="form-control" name="userid">
	</c:if>
 --%>

	<c:if test="${empty param.searchBtn}">
		<span><input type="button" onclick="page()" value="搜索" class="btn btn-primary"></span>
	</c:if>
</div>
<script src="${ctxStatic}/common/js/filter.js"></script>
<script src="${ctxStatic}/common/js/common.js"></script>
<c:choose>
	<c:when test="${param.packall == 1}">
		<script>
		function loadAll(gameName, ids){
			if($(".filter-menu-game>li:contains(全部)[g-name="+gameName+"]").length > 0){
				
			}else{
				$(".filter-menu-game").append("<li g-name='"+gameName+"' s-id='"+ids+"' onclick='loadPackUlAll(this)'>全部</li>");
			}
		}
		</script>
	</c:when>
	<c:otherwise>
		<script>
		function loadAll(gameName, ids){
			
		}
		</script>
	</c:otherwise>
</c:choose>
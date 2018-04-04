<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<table class="table">
	<caption>KPI数据</caption>
	<thead>
		<tr class="user-table-th">
			<th>月份</th>
			<th>应用名称</th>
			<th>月流水额</th>
			<th>月付费用户数</th>
			<th>月付费渗透率</th>
			<th>当月ARPPU</th>
			<th>新增用户数</th>
			<th>次月留存数</th>
			<th>前月新增用户<br>次月留存率</th> 
			<th>当月DAU</th>
			<th>次月留存数</th>
			<th>前月活跃用户<br>次月留存率</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${dateList}" var="date">
			<c:set var="data" value="${dataMap[date]}"/>
			<tr>
				<td>${fn:substring(date, 0, 7)}</td>
				<td>${data.gameName}-${data.serverName}</td>
				<td>${data.totalRecharge}</td>
				<td>${data.rechargeCount}</td>
				<td>${data.permeability}</td>
				<td>${data.arppu}</td>
				<td>${data.newCount}</td>
				<td>${data.newStay}</td>
				<td>${data.newStayRat}</td>
				<td>${data.activeCount}</td>
				<td>${data.activeStay}</td>
				<td>${data.activeStayRat}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<script type="text/javascript">
$(function(){
	$(".user-table-th th").click(function(){
		if ($(this).attr("order-by") == undefined){
			return;
		}
		$o = $(this).children(".glyphicon");
		if ($o.hasClass("glyphicon-sort-by-attributes")) {
			$(this).parent().children("th").children(".glyphicon").removeClass("glyphicon-sort-by-attributes");
			$(this).parent().children("th").children(".glyphicon").removeClass("glyphicon-sort-by-attributes-alt");
			$o.addClass("glyphicon-sort-by-attributes-alt");
		}else{
			$(this).parent().children("th").children(".glyphicon").removeClass("glyphicon-sort-by-attributes");
			$(this).parent().children("th").children(".glyphicon").removeClass("glyphicon-sort-by-attributes-alt");
			$o.addClass("glyphicon-sort-by-attributes");
		}
		findList({}, 1, 10);
	});
	$("tr>td:eq(2),tr>td:eq(4),tr>td:eq(5),tr>td:eq(6),tr>td:eq(8),tr>td:eq(9),tr>td:eq(10),tr>td:eq(11)").each(function(){
		var v = parseFloat($(this).html());
		if (v) {
			$(this).html(v.toFixed(2));
		}
	});
});
</script>
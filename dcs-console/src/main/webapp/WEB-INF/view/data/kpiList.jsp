<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/view/common/tags.jsp"%>
<table class="table">
	<caption>KPI数据</caption>
	<thead>
		<tr class="user-table-th">
			<th>日期</th>
			<th>应用名称</th>
			<th>日流水额</th>
			<th>日付费用户数</th>
			<th>日付费渗透率</th> 
			<th>当日ARPPU</th>
			<th>新增用户数</th>
			<th>次日留存数</th>
			<th>前日新增用户<br>次日留存率</th>
			<th>当日DAU</th>
			<th>次日留存数</th>
			<th>前日活跃用户<br>次日留存率</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${dateList}" var="date">
			<c:set var="data" value="${dataMap[date]}"/>
			<tr>
				<td>${date}</td>
				<td>${data.serverName}</td>
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
	$("tr").each(function(){
		var v2 = parseFloat($(this).children("td:eq(2)").html()).toFixed(4);
		var v4 = parseFloat($(this).children("td:eq(4)").html()).toFixed(4);
		var v5 = parseFloat($(this).children("td:eq(5)").html()).toFixed(4);
		var v8 = parseFloat($(this).children("td:eq(8)").html()).toFixed(4);
		var v9 = parseFloat($(this).children("td:eq(9)").html()).toFixed(4);
		var v10 = parseFloat($(this).children("td:eq(10)").html()).toFixed(4);
		var v11 = parseFloat($(this).children("td:eq(11)").html()).toFixed(4); 
		$(this).children("td:eq(2)").html(v2);
		$(this).children("td:eq(4)").html(v4);
		$(this).children("td:eq(5)").html(v5);
		$(this).children("td:eq(8)").html(v8);
		$(this).children("td:eq(9)").html(v9);
		$(this).children("td:eq(10)").html(v10);
		$(this).children("td:eq(11)").html(v11);
		
		$("td:contains('Infinity')").html("∞");
		$("td:contains('NaN')").html("-");
	});
});
</script>
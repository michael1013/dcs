<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table">
	<c:set var="total" value="0"/>
	<caption>付费分析<span class="fr">交易总额: <span id="recharge-data" style="color:orange;font-size:20px;">0</span></span></caption>
	<thead>
		<tr class="user-table-th">
			<th>日期</th>
			<th>交易用户数</th>
			<th>交易额</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${dateList}" var="date">
			<c:set var="data" value="${dataMap[date]}"/>
			<tr>
				<td>${date}</td>
				<td>${data.count}</td>
				<td>${data.total}<c:set var="total" value="${total + data.total}"/></td>
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
	$("#recharge-data").html(parseFloat(${total}).toFixed(2));
	
	$(".table tbody tr").each(function(){
		var v1 = parseFloat($(this).children("td:eq(1)").html()) || 0;
		var v2 = parseFloat($(this).children("td:eq(2)").html()) || 0;
		v2 = v2.toFixed(2) || 0;
		$(this).children("td:eq(1)").html(v1);
		$(this).children("td:eq(2)").html(v2);
	});
});
</script>

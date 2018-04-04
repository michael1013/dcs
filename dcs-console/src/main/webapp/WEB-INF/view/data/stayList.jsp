<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table">
	<caption>用户留存数</caption>
	<thead>
		<tr class="user-table-th">
			<th>日期</th>
			<c:forEach items="${dateList}" var="date" varStatus="st">
				<th>${st.index}天</th>
			</c:forEach>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${dateList}" var="date">
			<tr>
				<td>${date}</td>
				<c:forEach items="${dataMap[date]}" var="map" varStatus="st">
					<c:set var="mapkey" value="date${st.index}"></c:set>
					<td>${dataMap[date][mapkey]}</td>
				</c:forEach>
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
});
</script>
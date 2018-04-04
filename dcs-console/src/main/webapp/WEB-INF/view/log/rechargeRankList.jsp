<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table">
	<caption>充值信息</caption>
	<thead>
		<tr class="user-table-th">
			<th>用户ID</th>
			<th>服务器ID</th>
			<th>金额</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.list}" var="rank">
			<tr>
				<td>${rank.userid}</td>
				<td>${rank.serverid}</td>
				<td>${rank.amount}</td>
				<td></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="../common/pagination.jsp?f=findList&pn=${page.pageNo}&ps=${page.pageSize}&t=${page.total}"></jsp:include>
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
	
	$("tbody>tr").each(function(){
		$o = $(this).children("td:eq(2)");
		var p = parseFloat($o.html()).toFixed(2);
		$o.html(p);
	});
});
</script>
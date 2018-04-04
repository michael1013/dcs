<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table">
	<caption>货币消耗信息</caption>
	<thead>
		<tr class="user-table-th">
			<th>用户ID</th>
			<th>用户名</th>
			<th>服务器ID</th>
			<th>消耗类型</th>
			<th>货币名称</th>
			<th>消耗量</th>
			<th>余额</th>
			<th>记录时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.list}" var="coin">
			<tr>
				<td>${coin.userid}</td>
				<td>${coin.username}</td>
				<td>${coin.serverid}</td>
				<td>${coin.type}</td>
				<td>${coin.name}</td>
				<td>${coin.consume}</td>
				<td>${coin.amount}</td>
				<td>${coin.createTime}</td>
				<td></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="../common/pagination.jsp?pn=${page.pageNo}&ps=${page.pageSize}&t=${page.total}"></jsp:include>
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
	});
});
</script>
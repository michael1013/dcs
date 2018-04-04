<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table">
	<caption>道具消耗信息</caption>
	<thead>
		<tr class="user-table-th">
			<th>用户ID</th>
			<th>用户名</th>
			<th>服务器ID</th>
			<th>类型</th>
			<th>道具名称</th>
			<th>价格</th>
			<th>消耗量</th>
			<th>剩余量</th>
			<th>记录时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.list}" var="prop">
			<tr>
				<td>${prop.userid}</td>
				<td>${prop.username}</td>
				<td>${prop.serverid}</td>
				<td>${prop.type}</td>
				<td>${prop.name}</td>
				<td>${prop.price}</td>
				<td>${prop.consume}</td>
				<td>${prop.amount}</td>
				<td>${prop.createTime}</td>
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
});
</script>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table">
	<caption>登录信息</caption>
	<thead>
		<tr class="user-table-th">
			<th>用户ID</th>
			<th>用户名</th>
			<th>昵称</th>
			<th>IP</th>
			<th>服务器ID</th>
			<th>登录时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.list}" var="login">
			<tr>
				<td>${login.userid}</td>
				<td>${login.username}</td>
				<td decode="true">${login.nickname}</td>
				<td>${login.ip}</td>
				<td>${login.serverid}</td>
				<td>${login.createTime}</td>
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
	$("td[decode]").each(function(){
		$(this).html(decodeURIComponent($(this).html()));
	});
});
</script>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table">
	<caption>用户列表</caption>
	<thead>
		<tr class="user-table-th">
			<th>用户ID</th>
			<th>用户名</th>
			<th>手机号</th>
			<th>登录时间</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>${user.userid}</td>
				<td>${user.username}</td>
				<td>${user.mobile}</td>
				<td>${user.lastLoginTime}</td>
				<td>${user.createTime}</td>
				<td><span class="glyphicon glyphicon-edit" onclick="detail(${user.userid})"></span></td>
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
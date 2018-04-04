<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table">
	<caption>充值信息</caption>
	<thead>
		<tr class="user-table-th">
			<th>ID</th>
			<th>服务器ID</th>
			<th>用户ID</th>
			<th>用户名</th>	
			<th>渠道ID</th>
			<th>订单ID</th>
			<th>IP</th>
			<th>金额</th>
			<th>币种</th>
			<th>记录时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.list}" var="recharge">
			<tr>
				<td>${recharge.id}</td>
				<td>${recharge.serverid}</td>
				<td>${recharge.userid}</td>
				<td>${recharge.username}</td>
				<td>${recharge.channel}</td>
				<td>${recharge.orderid}</td>
				<td>${recharge.ip}</td>
				<td>${recharge.amount}</td>
				<td>${recharge.currency}</td>
				<td>${recharge.createTime}</td>
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
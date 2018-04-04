<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table">
	<caption>充值记录</caption>
	<thead>
		<tr class="user-table-th">
			<th>transID</th>
			<th>订单ID</th>
			<th>充值渠道</th>
			<th>bundleID</th>
			<th>产品ID</th>
			<th>产品名称</th>
			<th>价格/货币</th>
			<th>沙盒</th>
			<th>发货成功</th>
			<th>充值时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.list}" var="applePay">
			<tr>
				<td>${applePay.transid}</td>
				<td>${applePay.orderid}</td>
				<td>${applePay.channel}</td>
				<td>${applePay.bundleid}</td>
				<td>${applePay.productid}</td>
				<td>${applePay.productname}</td>
				<td>${applePay.price}/${applePay.currency}</td>
				<td>${applePay.sandbox}</td>
				<td>${applePay.deliveryFlag}</td>
				<td>${applePay.createat}</td>
				<td></td>
			</tr>
		</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
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
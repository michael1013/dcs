<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table">
	<caption>服务器信息</caption>
	<thead>
		<tr class="user-table-th">
			<th order-by="id">ID<span class="glyphicon glyphicon-sort-by-attributes"></span></th>
			<th order-by="gameid">游戏ID<span class="glyphicon"></span></th>
			<th order-by="game_name">游戏名称<span class="glyphicon"></span></th>
			<th order-by="serverid">服务器ID<span class="glyphicon"></span></th>
			<th order-by="server_name">服务器名称<span class="glyphicon"></span></th>
			<th order-by="open_time">开服时间<span class="glyphicon"></span></th>
			<th order-by="create_time">记录时间<span class="glyphicon"></span></th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.list}" var="prop">
			<tr>
				<td>${prop.id}</td>
				<td>${prop.gameid}</td>
				<td>${prop.gameName}</td>
				<td>${prop.serverid}</td>
				<td>${prop.serverName}</td>
				<td>${prop.openTime}</td>
				<td>${prop.createTime}</td>
				<td s-id="${prop.id}">
					<span class="icon-copy" title="复制新增"></span>
					<span class="icon-edit" title="编辑"></span>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="../common/pagination.jsp?f=findList&pn=${page.pageNo}&ps=${page.pageSize}&t=${page.total}"></jsp:include>
<script type="text/javascript">
$(function(){
	$(".icon-copy").click(function(){
		var gameid = $(this).parent().parent().children("td:eq(1)").html();
		var gameName = $(this).parent().parent().children("td:eq(2)").html();
		var serverid = $(this).parent().parent().children("td:eq(3)").html();
		var serverName = $(this).parent().parent().children("td:eq(4)").html();
		var openTime = $(this).parent().parent().children("td:eq(5)").html();
		var option = {};
		option.gameid = gameid;
		option.gameName = gameName;
		option.serverid = serverid;
		option.serverName = serverName;
		option.openTime = openTime;
		addServer("复制新增", option);
	});
	
	$(".icon-edit").click(function(){
		var sid = $(this).parent().attr("s-id");
		var gameid = $(this).parent().parent().children("td:eq(1)").html();
		var gameName = $(this).parent().parent().children("td:eq(2)").html();
		var serverid = $(this).parent().parent().children("td:eq(3)").html();
		var serverName = $(this).parent().parent().children("td:eq(4)").html();
		var openTime = $(this).parent().parent().children("td:eq(5)").html();
		var option = {};
		option.sid = sid;
		option.gameid = gameid;
		option.gameName = gameName;
		option.serverid = serverid;
		option.serverName = serverName;
		option.openTime = openTime;
		addServer("编辑", option);
	});
});
</script>
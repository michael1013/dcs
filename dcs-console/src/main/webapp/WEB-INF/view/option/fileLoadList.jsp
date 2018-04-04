<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
table {
	font-size: 12px;
}
</style>
<table class="table">
	<caption>文件读取信息</caption>
	<thead>
		<tr class="user-table-th">
			<th>ID</th>
			<th>路径</th>
			<th>用户</th>
			<th>文件日期</th>
			<th>MD5</th>
			<th>记录时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.list}" var="his">
			<tr>
				<td>${his.id}</td>
				<td>${his.path}</td>
				<td>${his.account}</td>
				<td>${his.dateTime}</td>
				<td>${his.md5}</td>
				<td>${his.createTime}</td>
				<td s-id="${his.id}">
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="../common/pagination.jsp?f=findList&pn=${page.pageNo}&ps=${page.pageSize}&t=${page.total}"></jsp:include>
<script type="text/javascript">
</script>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--
<table class="table">
	<caption>登录记录</caption>
	<thead>
		<tr class="user-table-th">
			<th>用户ID</th>
			<th>用户名</th>
			<th>手机号</th>
			<th>登录时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.list}" var="login">
			<tr>
				<td>${login.userid}</td>
				<td>${login.username}</td>
				<td>${login.mobile}</td>
				<td>${login.createTime}</td>
				<td></td>
			</tr>
		</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
	</tbody>
</table>
<jsp:include page="../common/pagination.jsp?f=findList&pn=${page.pageNo}&ps=${page.pageSize}&t=${page.total}"></jsp:include>
 --%>
 
<div id="loginlog-echarts" style="width:100%;height:400px;"></div>
<input id="login-time" type="hidden" value='[<c:forEach items="${page.list}" var="login">["${login.createTime}", 1],</c:forEach>[]]'>
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
<script>
function getVirtulData(year) {
    year = year || '2018';
    var date = +echarts.number.parseDate(year + '-01-01');
    var end = +echarts.number.parseDate((+year + 1) + '-01-01');
    var dayTime = 3600 * 24 * 1000;
    var data = [];
    for (var time = date; time < end; time += dayTime) {
        data.push([
            echarts.format.formatTime('yyyy-MM-dd HH:dd:mm.S', time),
            Math.floor(Math.random() * 2)
        ]);
    }
    return data;
}

option = {
    tooltip: {
        position: 'top'
    },
    visualMap: {
        min: 0,
        max: 1,
        calculable: true,
        orient: 'horizontal',
        left: 'center',
        top: 'top'
    },

    calendar: [
    {
        range: '2018',
        cellSize: ['auto', 20]
    }],

    series: [{
        type: 'heatmap',
        coordinateSystem: 'calendar',
        calendarIndex: 0,
        data: eval($("#login-time").val())
    }]

};

var myChart = echarts.init(document.getElementById('loginlog-echarts'));
myChart.setOption(option);
</script>


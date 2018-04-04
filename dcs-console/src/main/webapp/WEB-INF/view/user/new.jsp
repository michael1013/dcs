<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="new-user-echarts" style="width:100%;height:400px;"></div>
<input id="new-user-count" type="hidden" value='[<c:forEach items="${listMap}" var="map">["${map.date}", ${map.count}],</c:forEach>[]]'>

<script>
function getVirtulData(year) {
    year = year || '2017';
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
        range: '2017',
        cellSize: ['auto', 20]
    }],

    series: [{
        type: 'heatmap',
        coordinateSystem: 'calendar',
        calendarIndex: 0,
        data: eval($("#new-user-count").val())
    }]

};

var newUserChart = echarts.init(document.getElementById('new-user-echarts'));
newUserChart.setOption(option);
</script>
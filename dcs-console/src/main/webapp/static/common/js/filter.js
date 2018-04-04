$(function(){
	$("#time-month").val(getMonthDate());
	$("#time-begin").val(getDate(new Date(), 0, 0, -7));
	$("#time-end").val(getDateNow());
	
	$(".filter-menu").hover(function(){
		$(this).children(".filter-menu-ul").show();
	},function(){
		$(this).children(".filter-menu-ul").hide();
	});
	$(".filter-menu-ul>li").click(function(){
		loadUlist(this);
	});
	$(".filter-menu-gamelist>li").click(function(){
		var gameName = $(this).html();
		var ids = "";
		$(".filter-menu-game>li").each(function(){
			if ($(this).attr("g-name") == gameName) {
				$(this).show();
				ids += $.trim($(this).attr("s-id")) + ",";
			}else{
				$(this).hide();
			}
		});
		loadAll(gameName, ids);
		var $o = $("#pack-list>.filter-menu-title>span:eq(0)");
		$o.html("包");
		$o.attr("s-id", "");
		var $o = $("#server-list>.filter-menu-title>span:eq(0)");
		$o.html("服务器");
		$o.attr("s-id", "");
		$(".filter-menu-server>li").hide();
	});
	$(".filter-menu-game>li").click(function(){
		var gameid = $(this).attr("s-id");
		$(".filter-menu-server>li").each(function(){
			if ($(this).attr("g-id") == gameid) {
				$(this).show();
			}else{
				$(this).hide();
			}
		});
		var $o = $("#server-list>.filter-menu-title>span:eq(0)");
		$o.html("服务器");
		$o.attr("s-id", "");
	});
	$(".filter-menu-server>li").click(function(){
		if ("false" != $(this).parent().attr("s-go")) {
			findList({}, 1, 20);
		}
	});
	$(function(){
		$('.form_date').datetimepicker({
	        language:  'zh-CN',
	        format: 'yyyy-mm-dd',
	        initialDate: new Date(),
	        weekStart: 1,
	        todayBtn:  "linked",
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
	    });
		$('.form_month').datetimepicker({
	        language:  'zh-CN',
	        format: 'yyyy-mm',
	        initialDate: new Date(),
	        weekStart: 1,
	        todayBtn:  "linked",
			autoclose: 1,
			startView: 3,
			minView: 3,
			forceParse: 0
	    });
		$("#time-month").change(function(){
			$("#time-begin").val(getMonthDateBegin());
			$("#time-end").val(getMonthDateEnd());
		});
	});
});
function loadUlist(obj){
	var $o = $(obj).parent().parent().children(".filter-menu-title"); 
	if ($(obj).attr("s-id")=="all") {
		$o.attr("s-id", "");
		$o.children("span:eq(0)").html($(obj).attr("s-txt"));
	}else{
		$o.children("span:eq(0)").html($(obj).html());
		$o.attr("s-id",$(obj).attr("s-id"));
	}
	$(obj).parent().toggle();
}
function loadPackUlAll(obj){
	loadUlist(obj);
	findList({}, 1, 20);
}
function getMonthDateBegin(){
	var d = $("#time-month").val();
	return d + "-01 00:00:00";
}
function getMonthDateEnd(){
	var d = $("#time-month").val();
	var y = d.substring(0, d.indexOf("-"));
	var m = d.substring(d.indexOf("-") + 1);
	m = parseInt(m);
	m++;
	if (m == 12) {
		y++;
		m=1;
	}
	m = m > 9 ? m : "0" + m;
	return y + "-" + m + "-" + "01 00:00:00";
}
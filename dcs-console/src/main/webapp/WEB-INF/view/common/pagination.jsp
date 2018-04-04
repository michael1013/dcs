<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setAttribute("rannum", (int)(Math.random()*10000000)); %>
<style>
.pagination{
	margin: 5px auto;
}
.pagination>li>a{
	padding-left:2px;
	padding-right:2px;
}
.pagination>li>a{
	width: 50px;
	text-align: center;
}
</style>
<div id="pagination_${rannum}" style="width: 650px;margin:0 auto;">
	<ul class="pagination">
		<li>
			<a href="javascript:void(0);">&laquo;</a>
		</li>
		<li class="active">
			<a href="javascript:void(0);">1</a>
		</li>
		<li>
			<a href="javascript:void(0);">&raquo;</a>
		</li>
	</ul>
	<input type="hidden" value="${param.pn}" class="page-no">
	<input type="hidden" value="${param.ps}" class="page-size">
	<input type="hidden" value="${param.t}" class="page-total">
</div>
<script>
$(function(){
	init();
});
function init(){
	$o = $("#pagination_${rannum}");
	var pn = $o.find(".page-no").val();
	var ps = $o.find(".page-size").val();
	var t = $o.find(".page-total").val();
	var tpn = parseInt(t % ps == 0 ? t/ps : t/ps + 1);
	var ptxt = paging(pn, ps, tpn);
	$o.find(".pagination").html(ptxt);
	var c = $o.find(".pagination").children().length;
	$o.find(".pagination").parent().width(c*50+"px");
}
function paging(pageNo, pageSize, totalPage){
	$o = $("#pagination_${rannum}");
	pageSize = pageSize < 1 ? 1 : pageSize;
	if (pageNo > totalPage) {
		loading = false;
		return;
	}
	if(pageNo == 0){
		pageNo = parseInt($o.find(".pageNo").val());
	}
	pageNo = pageNo < 1 ? 1 : pageNo;
	pageNo = pageNo > totalPage ? totalPage : pageNo;
	
	pageNo = parseInt(pageNo);
	$o.find(".pageNo").val(pageNo);
	
	var start = 1;
	var end = totalPage;
	if(pageNo > totalPage - 5){
		start = totalPage -8;
	}else if (pageNo < 5){
		end = totalPage > 9 ? 9 : totalPage;
	}else{
		start = pageNo - 4;
		end = pageNo + 4;
	}
	start = start < 1 ? 1 : start;
	end = end > totalPage ? totalPage : end;
	var pagination = "";
	pagination += new PageItem(1, pageSize, 1, false).txt();
	pagination += new PageItem(pageNo>10?pageNo-10:1, pageSize, "&laquo;", "").txt();
	pagination += new PageItem(pageNo>1?pageNo-1:1, pageSize, "&lt;", "").txt();
	for (var i = start; i <=end ; i++) {
		pagination += new PageItem(i, pageSize, i, i == pageNo?"active":"").txt();
	}
	pagination += new PageItem(pageNo>totalPage-1?totalPage:pageNo+1, pageSize, "&gt;", "").txt();
	pagination += new PageItem(pageNo>totalPage-10?totalPage:pageNo+10, pageSize, "&raquo;", "").txt();
	pagination += new PageItem(totalPage, pageSize, totalPage, false).txt();
	pagination += "<li class=\"page-goto-span\"><a href=\"###\"><input type=\"text\" class=\"page-to\" style=\"width:30px;height:20px;\"></a></li>";
	pagination += "<li><a href=\"###\" class=\"page-goto\" onclick=\"page(pageNo(), "+pageSize+")\">goto</a></li>";
	$("#pagination_${rannum}").children(".pagination").html(pagination);
	loading = false;
}
function PageItem(pageNo, pageSize, item, active){
	this.pageNo = pageNo || 1;
	this.active = active || "";
	this.txt = function(){
		var t = "<li class=\""+active+"\"><a href=\"javascript:void(0);\"";
		if (active != "active") {
			t += " onclick=\"page("+pageNo+","+pageSize+")\"";
		}
		t += ">" + item + "</a></li>";
		return t;
	}
}
function pageNo(){
	var $o = $("#pagination_${rannum}");
	var pn = parseInt($o.find('.page-to').val());
	var ps = $o.find(".page-size").val();
	var t = $o.find(".page-total").val();
	var tpn = parseInt(t % ps == 0 ? t/ps : t/ps + 1);
	pn = pn < 1 ? 1 : pn;
	pn = pn > tpn ? tpn : pn;
	return pn;
}
</script>
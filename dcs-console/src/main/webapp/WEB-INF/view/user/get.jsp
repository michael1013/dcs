<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/WEB-INF/view/common/tags.jsp"%>
<script src="${ctxStatic}/dist/js/echarts.min.js"></script>
<style>
.list-group-item-content>img{
	max-height: 80px;
	max-width: 140px;
}
p{
	margin-bottom: 0px;
}
c{
	cursor: pointer;
}
.list-group-item .glyphicon-ok, .list-group-item .glyphicon-edit, .list-group-item .glyphicon-remove, .list-group-item .glyphicon-repeat {
	padding-left: 6px;
	padding-right: 4px;
	padding-top: 4px;
	padding-bottom: 5px;
	border: 1px solid;
	border-radius: 5px;
}
</style>
<script>var ctxName = "user";</script>
<input type="hidden" value="${user.userid}" id="entity-id">
<ul class="list-group">
	<li class="list-group-item">
		<div class="fl dib" style="width:165px">
			<span class="list-group-item-title fl"></span>
			<span class="list-group-item-content fl">
			</span>
		</div>
		<div class="fl dib" style="width:98%">
			<ul class="list-group fl ml10 w100p" style="margin-bottom:0;" id="edit-entityinfo">
				<li class="list-group-item">
					<span class="list-group-item-title">id:</span>
					<span class="list-group-item-content">${user.userid}</span>
					<span class="list-group-item-title">用户名:</span>
					<span class="list-group-item-content">${user.username}</span>
					<span class="list-group-item-title">手机号:</span>
					<span class="list-group-item-content">${user.mobile}</span>
				</li>
				<li class="list-group-item">
					<span class="list-group-item-title">昵称:</span>
					<span class="list-group-item-content">${user.nickname}</span>
					<span class="list-group-item-title">创建时间:</span>
					<span class="list-group-item-content">${user.createTime}</span>
					<span class="list-group-item-title">登录时间:</span>
					<span class="list-group-item-content">${user.updateTime}</span>
				</li>
				<li class="list-group-item">
					<span class="list-group-item-title">操作:</span>
					<span class="list-group-item-content" style="width:auto;">
						<%-- 
						<span class="glyphicon glyphicon-edit" onclick="edit(this)">修改</span>
						 --%>
					</span>
				</li>
			</ul>
			<ul class="list-group fl ml10 mt20 w100p" style="margin-bottom:0;" id="edit-pwd">
				<li class="list-group-item">
					<p style="text-align:center;"><img src="${ctxStatic}/common/img/loading.gif"></p>
				</li>
			</ul>
			
			<ul class="list-group fl ml10 mt20 w100p" style="margin-bottom:0;">
				<li class="list-group-item" id="edit-loginlog">
					<p style="text-align:center;"><input type="button" value="登录记录查询" class="btn btn-default" onclick="loadLoginLog(1,0)"></p>
				</li>
			</ul>
			
			<ul class="list-group fl ml10 mt20 w100p" style="margin-bottom:0;">
				<li class="list-group-item" id="edit-applepay">
					<p style="text-align:center;"><input type="button" value="充值记录查询" class="btn btn-default" onclick="loadApplePay(1,2)"></p>
				</li>
			</ul>
		</div>
		<div class="clear"></div>
	</li>
</ul>

<script>
function edit($this){
	var $p = $($this).parent().parent().parent();
	
	$("#edit-entityinfo span[edit-name]").each(function(){
		var txt = $(this).html();
		if ($(this).children("a").length > 0) {
			txt = $(this).children("a").html();
		}
		var name = $(this).attr("edit-name");
		$input = CommonUI.element("input", {
			type : "text",
			name : name,
			value : txt,
			style : "width: 100%;"
		});
		$(this).html($input);
	});
	
	$("#edit-entityinfo span[edit-select-name]").each(function(){
		var txt = $(this).html();
		var name = $(this).attr("edit-select-name");
		$select = CommonUI.element("select", {
			name : name,
			value : txt,
		});
		var options = $(this).attr("edit-select-values").split(",");
		for(var i=0;i<options.length;i++){
			var p = {
				value : options[i]
			};
			if (txt === options[i]) {
				p.selected = "selected";
			}
			$option = CommonUI.element("option", p);
			$option.append(options[i]);
			$select.append($option);
		}
		$(this).html($select);
	});
	
	var $spanEditImg = CommonUI.element("span", {
		className : "glyphicon glyphicon-edit",
		onClick : "editImg(this)"
	});
	$p.find(".edit-img").append($spanEditImg);
	
	var $spanOk = CommonUI.element("span", {
		className : "glyphicon glyphicon-ok",
		onClick : "submitEdit()"
	});
	$($spanOk).html("提交");
	var $spanRemove = CommonUI.element("span", {
		className : "ml5 glyphicon glyphicon-remove",
		onClick : "remove()"
	});
	$($spanRemove).html("删除");
	var $spanRetreat = CommonUI.element("span", {
		className : "ml5 glyphicon glyphicon-repeat",
		onClick : "retreat()"
	});
	$($spanRetreat).html("撤销");
	var $span = CommonUI.element("span", {});
	$span.appendChild($spanOk);
	$span.appendChild($spanRemove);
	$span.appendChild($spanRetreat);
	
	$($this).parent().html($span);
}
function submitEdit(){
	var entity  = {};
	entity.id = $("#entity-id").val();
	entity.img = $(".edit-img>img").attr("src");

	$("#edit-entityinfo span[edit-name] input").each(function(){
		var key = $(this).parent().attr("edit-name");
		var val = $(this).val();
		entity[key] = val;
	});
	$("#edit-entityinfo span[edit-select-name] select").each(function(){
		var key = $(this).parent().attr("edit-select-name");
		var val = $(this).val();
		entity[key] = val;
	});

	if (entity.length < 1) {
		alert("参数不能都为空");
		return;
	}
	
	var xurl = entity.id ? "/update" : "/insert";
	
	$.post(ctx+"/"+ctxName+xurl,entity,function(r){
		alert(r);
		retreat();
	});
}
function retreat(){
	var id = $("#entity-id").val();
	if (id) {
		detail(id);
	}else{
		location.reload();
	}
}

function remove(){
	if(confirm("确认是否要删除!")){
		var id = $("#entity-id").val();
		$.post(ctx+"/" + ctxName + "/delete", { "id" : id }, function(r){
			alert(r);
			location.reload();
		});
	}
}

function load2(){
	var userid = $("#entity-id").val();
	$.post(ctx+"/userpassword/get",{"userid":userid},function(r){
		$("#edit-pwd").html(r);
	});
}

function page(pageNo, pageSize){
	$o = $(event.target).parent().parent().parent().parent();
	if ($o.attr("id")=="edit-loginlog"){
		loadLoginLog(pageNo, pageSize);
	}else if ($o.attr("id")=="edit-applepay"){
		loadApplePay(pageNo, pageSize);
	}else {
		findList({}, pageNo, pageSize);
	}
}

function loadLoginLog(pageNo, pageSize){
	login = {};
	login.userid = $("#entity-id").val();
	login.pageNo = pageNo;
	login.pageSize = pageSize;
	$.post(ctx+"/loginLog/list", login ,function(r){
		$("#edit-loginlog").html(r);
	});
}

function loadApplePay(pageNo, pageSize){
	login = {};
	login.userid = $("#entity-id").val();
	login.pageNo = pageNo;
	login.pageSize = pageSize;
	$.post(ctx+"/applePay/list", login ,function(r){
		$("#edit-applepay").html(r);
	});
}
</script>
<script>
$(function(){
	load2();
});
</script>
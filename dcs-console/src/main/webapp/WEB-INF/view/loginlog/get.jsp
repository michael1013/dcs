<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<li class="list-group-item">
	<span class="list-group-item-title">密码ID:</span>
	<span class="list-group-item-content">${password.id}</span>
	<span class="list-group-item-title">密文:</span>
	<span class="list-group-item-content">${password.pwd}</span>
	<span class="list-group-item-title">修改时间:</span>
	<span class="list-group-item-content">${password.updateTime}</span>
	<span class="list-group-item-title">操作:</span>
	<span class="list-group-item-content" style="width:auto;">
		<span class="glyphicon glyphicon-edit" onclick="reset(${password.userid})">重置密码</span>
	</span>
</li>
<script>
function reset(userid){
	$p = CommonUI.element("p", {
		style : ""
	});
	$input = CommonUI.element("input", {
		type : "text",
		name : name,
		id : "new-pwd"
	});
	$button = CommonUI.element("input", {
		type : "button",
		className : "ml5",
		name : name,
		value : "提交修改",
		onClick : "resetSub(this, "+userid+")"
	});
	$p = CommonUI.element("p", {
		className : "mt20",
		style : "text-align:center;"
	});
	$p.append("新密码:");
	$p.appendChild($input);
	$p.appendChild($button);
	
	$div = CommonUI.element("div", {
		className : "panel-default",
		style : "display:block;margin:0px auto;margin-top:200px;width:400px;height:110px;background:#fff;border:1px solid #ddd"
	});
	$divCover = CommonUI.element("div", {
		style : "position:absolute;top:0px;left:0px;width:100%;height:100%;background:rgba(0,0,0,0.5);z-index:101;"
	});
	
	$head = CommonUI.element("div", {
		className : "panel-heading"
	});
	
	$title = CommonUI.element("h3", {
		className : "panel-title"
	});
	$title.append("userID:"+userid);
	$titleClose = CommonUI.element("span", {
		className : "glyphicon glyphicon-remove fr",
		onClick : "$(this).parent().parent().parent().parent().remove()"
	});
	$title.appendChild($titleClose);
	$head.appendChild($title);
	
	$div.appendChild($head);
	$div.appendChild($p);
	$divCover.appendChild($div);
	
	$("body").append($divCover);
}
function resetSub(obj, userid){
	$(this).parent().find("input").attr("disabled","disabled");
	var pwd = $("#new-pwd").val();
	$.ajax({
		url:ctx+"/password/update",
		data:{"userid":userid,"pwd":pwd},
		success:function(r){
			alert(r);
			$(obj).parent().parent().parent().remove();
			load2();
		},
		error:function(r){
			alert("操作异常, 请联系维护人员!");
		}
	});
}
</script>
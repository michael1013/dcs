<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="/WEB-INF/view/common/tags.jsp"%>
<style>
a{
	cursor: pointer;
}
</style>
<c:forEach items="${fileList}" var="file">
	<div>
		<p class="file-root">
			<c:choose>
				<c:when test="${file.directory}">
					<img src="${ctx}/img/dir-icon.png">
				</c:when>
				<c:otherwise>
					<img src="${ctx}/img/file-icon.png">
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${file.directory}">
					<a href="###" onclick="load(this, '${fn:replace(file.path, '\\', '/')}')">${file.name}</a>
				</c:when>
				<c:otherwise>
					<a href="###">${file.name}</a>
				</c:otherwise>
			</c:choose>
		</p>
	</div>
</c:forEach>
<script>
function load(o, path){
	if($(o).parent().children(".file-core").length == 0){
		path = encodeURIComponent(path);
		$.post(ctx+"/option/file",{"path":path},function(r){
			var list = eval("("+r+")");
			for (var i = 0; i < list.length; i++) {
				$(o).parent().append(p(list[i]));
			}
		});
	}else{
		$(o).parent().children(".file-core").slideToggle();
	}
}
function p(op){
	if (op) {
		var path = replaceAll(op.path,"\\","/");
		var p = document.createElement("p");
		p.className = "file-core";
		p.setAttribute("style", "padding-left:15px;");
		var a = document.createElement("a");
		
		if (op.directory) {
			a.setAttribute("onclick", "load(this, '"+path+"')");
		}
		a.setAttribute("src", "###");
		a.append(op.name);
			var img = document.createElement("img");
		if (op.directory) {
			img.setAttribute("src", ctx+"/img/dir-icon.png");
		}else{
			img.setAttribute("src", ctx+"/img/file-icon.png");
		}
		
		p.appendChild(img);
		p.appendChild(a);
		
		if (op.directory) {}else{
			var a2 = document.createElement("a");
			a2.style = "margin-left:5px;";
			a2.setAttribute("src", "###");
			a2.setAttribute("onclick", "download('"+path+"')");
			a2.append("下载");
			p.appendChild(a2);
			
			
			var a3 = document.createElement("a");
			a3.style = "margin-left:5px;";
			a3.setAttribute("src", "###");
			a3.setAttribute("onclick", "loadTask('"+path+"')");
			a3.append("读取数据");
			p.appendChild(a3);
		}
		
		return p;
	}
}
function download(path){
	
}
function loadTask(path){
	$.post(ctx+"/option/loadTask",{"path":path},function(r){
		alert(r);
	});
}
</script>
<script>
</script>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/view/common/tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>Livow Console</title>
	<style>
	.glyphicon{
		color: rgb(122, 183, 255);
	}
	.glyphicon-ok{
		color: #0c0;
		cursor: pointer;
	}
	.glyphicon-remove {
		color: #f00;
		cursor: pointer;
	}
	.glyphicon-plus{
		cursor: pointer;
	}
	.glyphicon-repeat{
		cursor: pointer;
	}
	.gift-table-th th{
		cursor:pointer;
	}
	.table-responsive {
		position: relative;
	}
	#table-cover {
		position: absolute;
		width: 100%;
		height: 100%;
		z-index:99;
	}
	#gift-list tr td input{
		width: 80px;
	}
	</style>
</head>
<body style="background-color: #fff;">
	<div class="page-middle">
		<jsp:include page="../common/header2.jsp?active=2"></jsp:include>
		<div id="index-content" class="pr">
			<div class="table-responsive">
				<table class="table">
					<caption>礼物列表</caption>
					<thead>
						<tr class="user-table-th">
							<th style="width:80px;">ID</th>
							<th style="width:180px;">名称en</th>
							<th style="width:180px;">名称cn</th>
							<th style="width:80px;">价格</th>
							<th style="width:80px;" title="按照数字大小升序排列">排序</th>
							<th style="width:240px;">图片</th>
							<th style="width:80px;">隐藏</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="gift-list">
					</tbody>
				</table>
				<div class="dn">
					<form id="img-import" method="post" enctype="multipart/form-data">
						<input type="file" name="file" id="img-file">
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var addContent = 
		"<tr>"+
		"	<td></td>"+
		"	<td input-name=\"nameEn\"></td>"+
		"	<td input-name=\"nameCn\"></td>"+
		"	<td input-name=\"price\"></td>"+ 
		"	<td input-name=\"sort\"></td>"+
		"	<td><img></td>"+
		"	<td input-name=\"delFlag\"></td>"+
		"	<td><span class=\"glyphicon glyphicon-plus\" onclick=\"updateStatus(0,this)\"></td>" +
		"</tr>";
	$(function(){
		findGift({},1,0);
	});
	function findGift(json, pageNo, pageSize){
		$("#table-cover").show();
		json.pageNo = pageNo;
		json.pageSize = pageSize;
		$.ajax({
			url:ctx+"/gift/findAll",
			data: json,
			async:true,
			success:function(r){
				var j = eval(r);
				var content = "";
				for (var i=0;i<j.length;i++) {
					var gift = j[i];
					var item = "<tr>"+showStatus(gift)+"</tr>";
					content += item;
				}
				content += addContent;
				$("#gift-list").html(content);
				setTimeout(function(){$("#table-cover").hide();}, 50);
			}
		});
	}
	function updateSub(id, obj){
		var $o = $(obj).parent().parent();
		var form = new FormData();
		form.append("file", $("#img-file")[0].files[0]);
		form.append("id", id);
		$o.children("td").children("input:text").each(function(){
			var k = $(this).attr("name");
			var v = $(this).val();
			form.append(k, v);
		});
		$.ajax({
			url:ctx+"/gift/update", 
			data: form, 
			type:"POST",
			cache: false,
			processData: false,
	    	contentType: false
		}).done(function(r) {
			var gift = eval(r);
			var item = showStatus(gift);
			if (0 == id) {
				item += addContent;
			}
			$o.html(item); 
			location.reload();
		}).fail(function(r) {
			alert("failed");
		});
	}
	function removeGift(id){
		if (confirm("确认要删除？")) {
			$.post(ctx+"/gift/remove",{"id":id},function(r){
				location.reload();
			});
			setTimeout(function(){location.reload();}, 500);
		}
	}
	function updateStatus(id, obj){
		var $o = $(obj).parent().parent();
		var old = $o.html();
		var btn = "<span class=\"glyphicon glyphicon-ok\" onclick=\"updateSub("+id+",this)\"></span>&nbsp;<span class=\"glyphicon glyphicon-repeat\" onclick=\"location.reload()\"></span>&nbsp;<span class=\"glyphicon glyphicon-remove\" onclick=\"removeGift("+id+")\"></span>";
		$o.children("td").each(function(){
			var name = $(this).attr("input-name");
			if (name != undefined) {
				var h = "<input type=\"text\" value=\""+$(this).html()+"\" name=\""+name+"\">";
				$(this).html(h);
			}
		});
		var img = $o.children("td").children("img").parent("td").html();
		img += "-&gt;<img id=\"img-preview\" style=\"height:50px;\"><input type=\"button\" value=\"上传图片\" onclick=\"$('#img-file').click();\">";
		$o.children("td").children("img").parent("td").html(img);
		$(obj).parent().html(btn);
	}
	function showStatus(gift){
		var item =
			"	<td>"+gift.id+"</td>"+
			"	<td input-name=\"nameEn\">"+gift.nameEn+"</td>"+
			"	<td input-name=\"nameCn\">"+gift.nameCn+"</td>"+
			"	<td input-name=\"price\">"+gift.price+"</td>"+ 
			"	<td input-name=\"sort\">"+gift.sort+"</td>"+
			"	<td><img height=\"50\" src=\""+gift.icon+"\"></td>"+
			"	<td input-name=\"delFlag\">"+gift.delFlag+"</td>"+
			"	<td><span class=\"glyphicon glyphicon-edit\" onclick=\"updateStatus("+gift.id+",this)\"></td>"+
			"";
		return item;
	}
	$(function(){
		<%-- 图片上传预览 --%>
		$('#img-file').change(function(){
			var file = document.getElementById('img-file').files[0];
			if (file) {
				var reader = new FileReader();
				reader.readAsDataURL(file);
				reader.onload = function(){
					$("#img-preview").attr("src", this.result);
				}
			}
		});
	});
	</script>
</body>
</html>
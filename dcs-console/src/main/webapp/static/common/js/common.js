CommonUI = {};
CommonUI.element = function(tag, option, event) {
	var e = document.createElement(tag);
	for(var key in option){
		e[key] = option[key];
	}
	/*
	if (option.src) {
		e.src = option.src;
	}
	if (option.selected) {
		e.selected = option.selected;
	}
	if (option.value) {
		e.value = option.value;
	}
	if (option.checked) {
		e.checked = option.checked;
	}
	if (option.title) {
		e.title = option.title;
	}
	if (option.href) {
		e.href = option.href;
	}
	if (option.name) {
		e.name = option.name;
	}
	if (option.type) {
		e.type = option.type;
	}
	if (option.className) {
		e.className = option.className;
	}
	if (option.style) {
		e.setAttribute("style", option.style);
	}
	if (option.id) {
		e.id = option.id;
	}
	if(option.enctype){
		e.enctype = option.enctype;
	}
	if(option.action){
		e.action = option.action;
	}
	if(option.method){
		e.method = option.method;
	}*/
	if (option.onClick) {
		e.setAttribute("onClick", option.onClick);
	}
	if (event) {
		var $event = eval(event);
		for ( var key in $event) {
			if (eval($event[key])) {
				e.addEventListener(key, $event[key]);
			}
		}
	}
	return e;
}
CommonUI.isDOM = function(obj) {
	return !!(obj && typeof window !== 'undefined' && (obj === window || obj.nodeType));
}
CommonUI.dateInput = function(id, name, value) {
	var input = document.createElement("input");
	input.className = "form-control span2 form_date";
	input.setAttribute("type", "text");
	input.setAttribute("size", "16");
	input.setAttribute("readonly", "");
	if (id)
		input.id = id;
	if (name)
		input.setAttribute("name", name);
	if (value)
		input.setAttribute("value", value);

	var span = document.createElement("span");
	span.className = "add-on";

	var script = document.createElement("script");
	script.setAttribute("src", ctxStatic + "/common/js/datetimepicker.js");

	var div = document.createElement("div");
	div.className = "input-append date";
	div.setAttribute("data-date-format", "dd-mm-yyyy");
	div.setAttribute("data-date", "12-02-2012");
	div.appendChild(input);
	div.appendChild(span);
	div.appendChild(script);
	return div;
}
function replaceAll(s, s1, s2) {
	do {
		s = s.replace(s1, s2);
	} while (s.indexOf(s1) > -1);
	return s;
}
function openCover(title, content) {
	var h = "<div class=\"panel panel-default pa w100p min-h100p\" style=\"z-index:99\"><div class=\"panel-heading\"><h3 class=\"panel-title\" id=\"detail-title\">"
			+ title
			+ "<span class=\"glyphicon glyphicon-remove fr\" onclick=\"closeCover(this)\"></span></h3></div><div class=\"panel-body\">"
			+ content + "</div></div>";
	var $4 = CommonUI.element("span", {
		className : "glyphicon glyphicon-remove fr",
		onClick : "closeCover(this)"
	});
	var $3 = CommonUI.element("h3", {
		className : "panel-title",
		id : "detail-title"
	});
	var $21 = CommonUI.element("div", {
		className : "panel-heading"
	});
	var $22 = CommonUI.element("div", {
		className : [ "panel-body" ]
	});
	var $1 = CommonUI.element("div", {
		className : "panel panel-default pa w100p min-h100p",
		style : "z-index:99"
	});
	$1.appendChild($21);
	$1.appendChild($22);
	$21.appendChild($3);
	$3.append(title);
	$3.appendChild($4);
	$($22).html(content);

	$(".div-cover").html($1);
	$(".div-cover").show();
}
function closeCover(obj) {
	$o = $(obj).parent().parent().parent();
	$o.parent().hide();
	$o.html("");
}
function getPagination(obj, fu, pageNo, pageSize, total) {
	pageNo = parseInt(pageNo);
	pageSize = parseInt(pageSize);
	total = parseInt(total);

	var totalPage = Math.ceil(total / pageSize);
	pageNo = pageNo < 1 ? 1 : pageNo;
	pageNo = pageNo > totalPage ? totalPage : pageNo;
	var prev = pageNo <= 1 ? 1 : pageNo - 1;
	var next = pageNo >= totalPage ? totalPage : pageNo + 1;
	var content = "<li><a href=\"javascript:void(0);\" onclick=\""
			+ fu
			+ "("
			+ prev
			+ ", "
			+ pageSize
			+ ")\">&lt;</a></li>"
			+ "<li class=\"active\"><a href=\"javascript:void(0);\" style=\"width:47px;\">"
			+ pageNo + "/" + totalPage + "</a></li>"
			+ "<li><a href=\"javascript:void(0);\" onclick=\"" + fu + "("
			+ next + ", " + pageSize + ")\">&gt;</a></li>" + "";
	obj.html(content);
}
function getDateNow() {
	var d = new Date();
	var y = d.getYear() + 1900;
	var m = d.getMonth() + 1;
	m = m > 9 ? m : "0" + m;
	var d = d.getDate();
	d = d > 9 ? d : "0" + d;
	var now = y + "-" + m + "-" + d;
	return now;
}
function getDate(date, yx, mx, dx) {
	date.setYear(date.getYear() + 1900 + yx);
	date.setMonth(date.getMonth() + mx)
	date.setDate(date.getDate() + dx);

	var y = date.getYear() + 1900;
	var m = date.getMonth() + 1;
	m = m > 9 ? m : "0" + m;
	var d = date.getDate();
	d = d > 9 ? d : "0" + d;
	var datex = y + "-" + m + "-" + d;
	return datex;
}
function getMonthDate(){
	var d = new Date();
	var y = d.getYear() + 1900;
	var m = d.getMonth() + 1;
	m = m > 9 ? m : "0" + m;
	var now = y + "-" + m;
	return now;
}
function editImg($this){
	var $inputFile = CommonUI.element("input", {
		className : "edit-upload-img",
		type : "file" ,
		name : "file" 
	});
	var $input = CommonUI.element("input", {
		className : "edit-upload-linkid",
		type : "hidden" ,
		name : "linkid" 
	});
	var $form =  CommonUI.element("form", {
		className : "edit-upload-form",
		action : ctx+"/common/img/upload",
		enctype : "multipart/form-data",
		method : "post",
		style : "display:none;"
	});
	$form.appendChild($inputFile);
	//$form.appendChild($input);
	
	if ($($this).parent().has(".edit-upload-form").length < 1) {
		$($this).parent().append($form);
	}
	
 	$(".edit-upload-img").click();
	var interval = setInterval(function(){
		var path = $(".edit-upload-img").val();
		if (path.length > 0){
			clearInterval(interval);
			$($this).parent().children("img").attr("src", ctxStatic + "/common/img/loading.gif");
			$.ajax({
			    url: ctx + "/common/img/upload",
			    type: 'POST',
			    cache: false,
			    data: new FormData($('.edit-upload-form')[0]),
			    processData: false,
			    contentType: false
			}).done(function(r) {
				$($this).parent().children("img").attr("src", r);
				$(".edit-upload-img").attr("update-img-url", r);
				$('.edit-upload-form').remove();
			}).fail(function(r) {
				$('.edit-upload-form').remove();
			});
		}
	}, 1000);
}
$(function(){
	$("img").click(function(){
		var src = $(this).attr("src");
		$("body").append("<div style=\"position:absolute;background:rgba(0,0,0,0.5);top:0px;left:0px;width:100%;height:150%;z-index:999;\" onclick=\"$(this).remove();\"><p style=\"text-align:center;margin-top:100px;\"><img src="+src+"></p></div>");
	});
});
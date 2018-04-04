<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE div PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/common/css/filter.css" rel="stylesheet" media="screen">
<div class="table-filter">
	<div class="fr">
		<c:if test="${param.user == 1}">
			<span>用户:</span>
			<input type="text" class="form-control" name="userid" >
		</c:if>
		
		<c:choose>
			<c:when test="${param.date == 'm' }">
				<span>时间:</span>
				<div class="input-append date" data-date="05-2017" data-date-format="mm-yyyy">
				    <input class="form-control span2 form_month" size="16" type="text" readonly id="time-month">
				    <span class="add-on"><i class=""></i></span>
				</div>
				<input type="hidden" id="time-ismonth" value="true">
				<input type="hidden" id="time-begin" value="">
				<input type="hidden" id="time-end" value="">
				<script>$(function(){
					$("#time-month").val(getMonthDate());
					setTimeout(function(){
						$("#time-begin").val(getMonthDateBegin());
					}, 500);
				})</script>
			</c:when>
			<c:otherwise>
				<span>时间:</span>
				<div class="input-append date" data-date="01-01-2017" data-date-format="dd-mm-yyyy">
				    <input class="form-control span2 form_date" size="16" type="text" readonly id="time-begin">
				    <span class="add-on"><i class=""></i></span>
				</div>
				-
				<div class="input-append date" data-date="01-01-2012" data-date-format="dd-mm-yyyy">
				    <input class="form-control span2 form_date" size="16" type="text" readonly id="time-end">
				    <span class="add-on"><i class=""></i></span>
				</div>
			</c:otherwise>
		</c:choose>
	
	
		<c:if test="${empty param.searchBtn}">
			<span><input type="button" onclick="page()" value="搜索" class="btn btn-primary"></span>
		</c:if>
	</div>
	
	<div class="clear"></div>
</div>
<script src="${ctxStatic}/common/js/filter.js"></script>
<script src="${ctxStatic}/common/js/common.js"></script>

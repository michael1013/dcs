<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@page import="java.util.List"%>
<%@page import="cn.gnetop.dcs.dao.schema.CurrencyRate"%>
<%@page import="cn.gnetop.dcs.console.system.linster.BeanUtils"%>
<%@page import="cn.gnetop.dcs.dao.CurrencyRateDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	CurrencyRateDao dao = BeanUtils.getBean(CurrencyRateDao.class);
	List<CurrencyRate> rateList = dao.findAll();
	request.setAttribute("rateList", rateList);
%>
<p>汇率:
<c:forEach items="${rateList}" var="rate">${rate.currency}:${fn:substring(rate.rate, 0, 6)},</c:forEach>
</p>
<% request.setAttribute("ctx", this.getServletContext().getContextPath()); %>
<% request.setAttribute("ctxStatic", this.getServletContext().getContextPath() + "/static"); %>
<!DOCTYPE script PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Livow Console</title>
		<link href="${ctxStatic}/dist/css/bootstrap.min.css" rel="stylesheet">
		<link href="${ctxStatic}/dist/css/font-awesome.min.css" rel="stylesheet">
		<link href="${ctxStatic}/common/css/common.css" rel="stylesheet">
		
		<script src="${ctxStatic}/jquery/js/jquery.min.js"></script>
		<script src="${ctxStatic}/dist/js/bootstrap.min.js"></script>
		<script src="${ctxStatic}/common/js/common.js"></script>
		
		<script>
		var ctx = "${ctx}";
		var ctxStatic = "${ctxStatic}";
		</script>
	</head>
</html>
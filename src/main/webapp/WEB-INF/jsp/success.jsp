<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>成功</title>
	<jsp:include page="/WEB-INF/include/include.jsp"></jsp:include>
	<script type="text/javascript" src="/js/addstock.js"></script>
</head>
<body class="skin-blue sidebar-mini">
	<div class="wrapper">
		<jsp:include page="/WEB-INF/include/header.jsp"/>

		<jsp:include page="/WEB-INF/include/left.jsp"/>
		
		<div class="content-wrapper" style="min-height: 387px;">
		<section class="content">
		<div class="row">
			<div class="col-md-12">
			<div class="box box-success">
				<div class="box-body" align="center">
					<h1>操作成功</h1>
					<h2>${successText }操作成功</h2>
				</div>
			</div>
			</div>
		</div>
		</section>
		</div>
		<jsp:include page="/WEB-INF/include/footer.jsp" />
	</div>
	
</body>
</html>
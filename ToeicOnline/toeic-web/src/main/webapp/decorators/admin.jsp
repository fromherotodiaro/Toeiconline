<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><dec:title default="Admin Page" /></title>
<dec:head />

<!-- bootstrap & fontawesome -->
<link rel="stylesheet"
	href="<c:url value="/template/admin/css/bootstrap.min.css"/>" />

<link rel="stylesheet"
	href="<c:url value="/template/admin/font-awesome/4.5.0/css/font-awesome.min.css"/>" />
<!-- page specific plugin styles -->
<!-- text fonts -->
<link rel="stylesheet"
	href="<c:url value="/template/admin/css/fonts.googleapis.com.css"/>" />
<!-- ace styles -->
<link rel="stylesheet"
	href="<c:url value="/template/admin/css/ace.min.css"/>"
	class="ace-main-stylesheet" id="main-ace-style" />
<!-- ace settings handler -->
<script src="<c:url value="/template/admin/js/ace-extra.min.js"/>"></script>
</head>
<body class="no-skin">
	<%@ include file="/common/admin/header.jsp"%>
	<%-- <%@ include file="/common/admin/menu.jsp"%> --%>
	<dec:body />
	<%-- <%@ include file="/common/admin/footer.jsp"%> --%>


	<script src="<c:url value="/template/admin/js/bootstrap.min.js"/>"></script>
	<!-- page specific plugin scripts -->
	<script src="<c:url value="/template/admin/js/jquery-ui.custom.min.js"/>"></script>
	<script src="<c:url value="/template/admin/js/jquery.ui.touch-punch.min.js"/>"></script>
	<script src="<c:url value="/template/admin/js/jquery.easypiechart.min.js"/>"></script>
	<script src="<c:url value="/template/admin/js/jquery.sparkline.index.min.js"/>"></script>
	<script src="<c:url value="/template/admin/js/jquery.flot.min.js"/>"></script>
	<script src="<c:url value="/template/admin/js/jquery.flot.pie.min.js"/>"></script>
	<script src="<c:url value="/template/admin/js/jquery.flot.resize.min.js"/>"></script>
	<!-- ace scripts -->
	<script src="<c:url value="/template/admin/js/ace-elements.min.js"/>"></script>
	<script src="<c:url value="/template/admin/js/ace.min.js"/>"></script>
</body>
</html>
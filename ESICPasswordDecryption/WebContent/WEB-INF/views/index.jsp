<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ESIC</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validation.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css" type="text/css" />
</head>
<body>
	<form:form name="frm" modelAttribute="userdetails" method="post">
		<p>Username : <form:input path="username" />&nbsp;<input type="button" value="Submit" onclick="JavaScript:fnSubmit()" /></p>
	</form:form>
	<div id="result"></div>
</body>
</html>
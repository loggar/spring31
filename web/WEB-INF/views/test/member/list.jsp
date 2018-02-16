<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>test</title>
<style>
.formErrMsg {
	border: 1px solid red;
}
</style>
</head>
<body>
	<div class="container">
		<c:forEach var="member" items="${memberList}">
			${member.id } ${member.identi } ${member.name } ${member.password }
		</c:forEach>
	</div>
</body>
</html>
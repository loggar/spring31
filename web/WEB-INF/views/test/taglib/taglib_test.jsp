<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>
</head>
<body>
<div class="container">
    <p>
        <c:forEach var="member" items="${memberList}">
            ${member.id }
            ${member.identi }
            ${member.name }
            ${member.password }
        </c:forEach>
    <p>
    <p>
        <s:eval expression="member.toString()" />
    </p>
    <p>
        <s:eval expression='new java.text.DecimalFormat("###,##0.00").format(123)' /><br />
        <s:eval expression="memberDetail.point" /><br />
        <s:eval expression="memberDetail.date" /><br />
        <s:eval expression="memberDetail.money" /><br />
    </p>
    <p>LOCALE : 
        ${currentLocale }
    </p>
    <p>
        <s:message code="duplicateIdenti" /><br />
        <s:message code="hello" arguments="${member.name }" />
    </p>
    
</div>
</body>
</html>
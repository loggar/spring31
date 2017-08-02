<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>test</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/jquery-1.12.2.min.js"></script>
</head>
<body>
	<div class="container">
		<f:form modelAttribute="member" action="${pageContext.request.contextPath }/test/member/add" method="post">
			<fieldset>
				<legend> Member Infomation </legend>
				<p>
					<f:label path="identi">아이디:</f:label>
					<f:input path="identi" size="20" maxlength="100" />
					<input id="identiCheckBtn" type="button" value="check" />
					<f:errors cssClass="formErrMsg" path="identi" />
					<span id="spanIdentiCheckResult"></span>
				</p>
				<p>
					<f:label path="name">Name:</f:label>
					<f:input path="name" size="12" maxlength="100" />
					<f:errors cssClass="formErrMsg" path="name" />
				</p>
				<p>
					<f:label path="">Password:</f:label>
					<f:password path="password" showPassword="true" size="12" maxlength="12" />
					<f:errors cssClass="formErrMsg" path="password" />
				</p>
				<p>
					<input type="submit" value="Submit" />
				</p>
			</fieldset>
		</f:form>
	</div>
	<script type="text/javascript">
		(function($) {
			$('#identiCheckBtn').click(function(e) {
				$.getJSON("${pageContext.request.contextPath }/test/member/countByIdenti.json", {
					"testIdenti" : $("#identi").val()
				}, function(data) {
					console.log(data);
					if (data.countByIdnti > 0) {
						$("#spanIdentiCheckResult").text("NOT OK");
					} else {
						$("#spanIdentiCheckResult").text("OK");
					}
				})
			});
		})(jQuery);
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>test</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/jquery-1.12.2.min.js"></script>
</head>
<body>
	<div class="container">
		<input name="files" type="file" multiple> <input id="btnUpload" type="button" value="Upload">
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			var formData = new FormData();
			for (var i = 0; i < 5; i++) {
				formData.append("breaks", i);
			}
			for (var [key, value] of formData.entries()) { 
				console.log(key, value);
			}
			$.ajax({
				url : "${pageContext.request.contextPath }/test/formdata/test1",
				data : formData,
				type : 'POST',
				contentType : false,
				processData : false,
				success : function(response) {
					console.log(response);
				}
			});
			$.ajax({
				url : "${pageContext.request.contextPath }/test/formdata/test2",
				data : formData,
				type : 'POST',
				contentType : false,
				processData : false,
				success : function(response) {
					console.log(response);
				}
			});
		});
	</script>
</body>
</html>
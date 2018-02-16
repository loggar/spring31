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
			$("input#btnUpload").click(function() {
				console.log($("input[name=files]")[0]);
				console.log($("input[name=files]")[0].files);
				var formData = new FormData();
				$.each($("input[name=files]")[0].files, function(i, file) {
					formData.append("files_01", file);
				});
				$.ajax({
					url : "${pageContext.request.contextPath }/test/multiple_file_upload/upload_1",
					type : 'POST',
					data : formData,
					contentType : false,
					processData : false
				}).done(function(response) {
					console.log("response arrived");
					console.log(response);
				}).fail(function(response, textStatus, jqXHR) {
					console.log("request fail");
					console.log(response);
					console.log(textStatus);
					console.log(jqXHR);
				});
			});
		});
	</script>
</body>
</html>
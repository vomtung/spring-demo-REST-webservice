<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored ="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {

		$('.clickme').click(function() {
			$.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/result/helloajax.html',
				success : function(data) {
					$('.result').html(data);
				}
			});
		});

		$('.sum').click(function() {
			$.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/index/sum/1/2.html',
				success : function(data) {
					$('.result').html(data);
				}
			});
		});

		$('.json').click(function() {
			$.ajax({
				type : 'GET',
				headers: { 
			        Accept : "application/json; charset=utf-8",
			        "Content-Type": "application/json; charset=utf-8"
			    },
				url : '${pageContext.request.contextPath}/index/json.html',
				success : function(data) {
					$('.result').html(data.id + ' - ' + data.name);
				}
			});
		});

		$('.listjson').click(function() {
			$.ajax({
				type : 'GET',
				headers: { 
			        Accept : "application/json; charset=utf-8",
			        "Content-Type": "application/json; charset=utf-8"
			    },
				url : '${pageContext.request.contextPath}/index/listjson.html',
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					var result = "";
					for(var i=0; i<obj.length;i++)
						result += "<br>" + obj[i].id + ' - ' + obj[i].name;
					$('.result').html(result);
				}
			});
		});

	});
</script>
</head>
<body>
<input type="button" class="clickme" value="Click me">
	<input type="button" class="sum" value="Sum">
	<input type="button" class="json" value="JSON">
	<input type="button" class="listjson" value="List JSON">
	<div class="result"></div>
</body>
</html>

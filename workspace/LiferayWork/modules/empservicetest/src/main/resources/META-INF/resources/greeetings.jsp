<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/init.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<portlet:renderURL  var="homeUrl">
	<portlet:param name="mvcPath" value="/META-INF/resources/view.jsp"/>
</portlet:renderURL>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>${msg}</p><br>
	
	<a href="${homeUrl}">Home</a>
	
</body>
</html>
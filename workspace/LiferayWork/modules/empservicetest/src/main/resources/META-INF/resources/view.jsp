<%@ include file="/init.jsp" %>

<portlet:actionURL name="employeeSubmitMethod" var="employeeSubmit" />
<portlet:renderURL  var="employeeDeleteMethod" />

<h4>Employee Form</h4>

<form action="${employeeSubmit}" method="post">
	Name: <input type="text" name="<portlet:namespace/>name"><br>
	Email: <input type="text" name="<portlet:namespace/>email"><br>
	Mobile: <input type="text" name="<portlet:namespace/>mobile"><br>
	<input type="submit" value="ADD">
	<br><br>
</form>
<form action="${employeeDelete}" method="post">
	Name: <input type="text" name="<portlet:namespace/>name"><br>
	Email: <input type="text" name="<portlet:namespace/>email"><br>
	Mobile: <input type="text" name="<portlet:namespace/>mobile"><br>

	<input type="submit" value="DELETE">

	<br><br>
</form>
<%-- <a href="${redirectPage}">Greetings</a> --%>
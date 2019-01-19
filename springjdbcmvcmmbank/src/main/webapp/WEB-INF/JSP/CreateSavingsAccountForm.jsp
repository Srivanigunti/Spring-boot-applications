<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	.error{
			color: red
			}
</style>
</head>
<body>
	<spring:form action="createAccount" modelAttribute="account"> 
		<center>
			<h1>CREATE A NEW SAVINGS ACCOUNT</h1>
			Name :<br>
			<spring:input path="bankAccount.accountHolderName"/><br><spring:errors path="bankAccount.accountHolderName" cssClass="error"/><br>
			<br> AccountBalance :<br>
			<spring:input path="bankAccount.accountBalance"/><br><spring:errors path="bankAccount.accountBalance" cssClass="error"/><br>
			<br> Salaried : <spring:radiobutton path="salary" value="yes"/>YES
			<spring:radiobutton path="salary" value="no"/>NO<br>
			<br> <input type="submit" name="submit" value="Submit">
			<input type="reset" name="reset" value="Reset">
	</spring:form>
</body>
</html>
<%@ page isELIgnored="false" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form>
		<table>
			<tr>
				<th>Account Number</th>
				<th>Holder Name</th>
				<th>Account Balance</th>
				<th>Salary</th>
				<th>Over Draft Limit</th>
				<th>Type Of Account</th>
			</tr>
			<c:if test="${accounts!=null}">
				<c:forEach var="account" items="${accounts}">
					<tr>
						<td>${account.bankAccount.accountNumber}</td>
						<td>${account.bankAccount.accountHolderName }</td>
						<td>${account.bankAccount.accountBalance}</td>
						<td>${"N/A"}</td>
						<td>${account.odlimit}</td>
						<td>${"Current"}</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</form>
</body>
</html>
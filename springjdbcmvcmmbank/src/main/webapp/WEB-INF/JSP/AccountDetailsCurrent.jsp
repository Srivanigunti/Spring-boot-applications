<%@ page isELIgnored="false" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<th><a href="sortByNumberCurrent">Account Number</a></th>
				<th><a href="sortByNameCurrent">Holder Name</a></th>
				<th><a href="sortByBalanceCurrent">Account Balance</a></th>
				<th>Salary</th>
				<th><a href="sortByOdLimitCurrent">Over Draft Limit</a></th>
				<th>Type Of Account</th>
			</tr>
			<%-- <jstl:if test="${account!=null}">
			<tr>
				<td>${account.bankAccount.accountNumber}</td>
				<td>${account.bankAccount.accountHolderName }</td>
				<td>${account.bankAccount.accountBalance}</td>
				<td>${account.salary==true?"Yes":"No"}</td>
				<td>${"N/A"}</td>
				<td>${"Savings"}</td>
			</tr>
		</jstl:if> --%>
			<%-- 	<c:out value="${accounts}"/> --%>

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
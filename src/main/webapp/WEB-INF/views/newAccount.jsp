<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Create Account</title>
</head>
<body>
	<h3>Create Account</h3>
	
	<sf:form method="POST" action="${pageContext.request.contextPath}/createAccount" commandName="user">
		<table>
			<tr>
				<td>Username:</td>
				<td><sf:input path="username" type="text" name="username"/></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><sf:input path="email" type="text" name="email"/></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><sf:input path="password" type="text" name='password' /></td>
			</tr>
			<tr>
				<td>Confirm Password:</td>
				<td><input type="text" name="confirm_password" /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Login" /></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>
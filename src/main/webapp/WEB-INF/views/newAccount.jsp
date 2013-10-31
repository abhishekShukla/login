<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/jquery.js"></script>
<script type="text/javascript">
	
	function onLoad(){
		$("#password").keyup(checkPasswordsMatch);
		$("#confirm_password").keyup(checkPasswordsMatch);
		
		$("#newAccount").submit(canSubmit);
	}
	
	function canSubmit(){
		
		var password = $("#password").val();
		var confirm_password = $("#confirm_password").val();
		
		if(password == confirm_password){
			return true;
		} else {
			return false;
		}
		
	}
	
	function checkPasswordsMatch(){
		
		var password = $("#password").val();
		var confirm_password = $("#confirm_password").val();
		
		if (password.length > 8 || confirm_password.length > 8){
			
			if(password == confirm_password){
				$("#match_password").text("Passwords match");
				$("#match_password").addClass("valid");
				$("#match_password").removeClass("error");
			} else {
				$("#match_password").text("Passwords do not match");
				$("#match_password").addClass("error");
				$("#match_password").removeClass("valid");
			}
			
			return;
		} 
	}
	
	
	
	$(document).ready(onLoad);

</script>

<title>Create Account</title>
</head>
<body>
	<h3>Create Account</h3>
	
	<sf:form method="POST" id="newAccount" action="${pageContext.request.contextPath}/createAccount" commandName="user">
		<table class="formtable">
			<tr>
				<td class="label">Username:</td>
				<td><sf:input class="control" path="username" type="text" name="username"/>
				<div class="error">
				<sf:errors path="username"></sf:errors>
				</div>
				</td>
			</tr>
			<tr>
				<td class="label">Email:</td>
				<td><sf:input class="control" path="email" type="text" name="email"/>
				<div class="error">
				<sf:errors path="email"></sf:errors>
				</div>
				</td>
			</tr>
			<tr>
				<td class="label">Password:</td>
				<td><sf:input class="control" id = "password" path="password" type="password" name='password' />
				<div class="error">
				<sf:errors path="password"></sf:errors>
				</div>
				</td>
			</tr>
			<tr>
				<td class="label">Confirm Password:</td>
				<td><input class="control" id = "confirm_password" type="password" name="confirm_password" />
				<div id="match_password">
				</td>
			</tr>
			<tr>
				<td class="label" colspan='2'><input name="submit" type="submit"
					value="Create Account" /></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link
	href="${pageContext.request.contextPath}/resources/css/createAccount.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/javascript/jquery.js"></script>
<script type="text/javascript">
	function onLoad() {
		$("#password").keyup(checkPasswordsMatch);
		$("#confirm_password").keyup(checkPasswordsMatch);

		$("#newAccount").submit(canSubmit);
	}

	function canSubmit() {

		var password = $("#password").val();
		var confirm_password = $("#confirm_password").val();

		if (password == confirm_password) {
			return true;
		} else {
			return false;
		}

	}

	function checkPasswordsMatch() {

		var password = $("#password").val();
		var confirm_password = $("#confirm_password").val();

		if (password.length > 0 || confirm_password.length > 0) {

			if (password == confirm_password) {
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
<body class="body">

	<sf:form method="POST" id="newAccount"
		action="${pageContext.request.contextPath}/createAccount"
		commandName="user" class="form">

		<div class="header">
			<h1>Create Account</h1>
		</div>

		<div class="content">
			<sf:input class="input" path="username" type="text" name="username" value = "username"/>			
			<div class="error">
				<sf:errors path="username" class="error"></sf:errors>
			</div>

			<sf:input class="input" path="email" type="text" name="email" value = "email"/>			
			<div class="error">
				<sf:errors path="email" class="error"></sf:errors>
			</div>


			<sf:input class="input" id="password" path="password" type="password"
				name='password' value="password"/>
			<div class="error">
				<sf:errors path="password" class="error"></sf:errors>
			</div>

			<input class="input" id="confirm_password" type="password"
				name="confirm_password" value="password"/>
			<div id="match_password"></div>
			<div class="footer">
				<input name="submit" type="submit" value="Create Account"
					class="login" />
			</div>
	</sf:form>
</body>
</html>
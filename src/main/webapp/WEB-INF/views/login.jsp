<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
	uri="http://www.springframework.org/spring-social/facebook/tags"
	prefix="facebook"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/login.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/javascript/jquery.js"></script>
<title>Login Page</title>
<facebook:init appId="@facebookProvider.appId" />
</head>
<body class="body" onload='document.f.j_username.focus();'>


	<div class="form">

		<div class="header">
			<h1>Login</h1>
		</div>

		<div class="content">

			<form name='f'
				action='${pageContext.request.contextPath}/j_spring_security_check'
				method='POST'>

				<input type='text' name='j_username' value='username' class="input" />

				<br /> <input type='password' name='j_password' value='password'
					class="input" />

				<c:if test="${param.error != null}">
					<br />
					<br />

					<span class="login_error"> Login Failed. Check Username or
						Password </span>


				</c:if>
				<p>
					Remember me: <input type='checkbox'
						name='_spring_security_remember_me' checked='checked' />
				</p>
				<input name="submit" type="submit" value="Login" class="login" />
			</form>
			<div class="footer">
				<p>
					<a href="<c:url value="/newAccount"/>">Not a member? Register.</a>
				</p>

				<form id="fb_signin" action="<c:url value="/signin/facebook"/>"
					method="POST">
					<div id="fb-root"></div>
					<p>
						<input name="submit" type="image"
							src="${pageContext.request.contextPath}/resources/images/fb-login.png"
							class="fb-login" />
					</p>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
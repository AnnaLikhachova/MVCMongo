<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta charset="UTF-8" />
	<link href="<c:url value="/WEB-INF/resources/css/main.css" />" rel="stylesheet">
	<link href="<c:url value="/WEB-INF/resources/css/util.css" />" rel="stylesheet">
</head>
<body>
<div class="limiter">
	<div class="container-login100" style="background-image: url('<c:url value="/WEB-INF/resources/images/bg-01.jpg" />');">
		<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
			<c:url var="loginUrl" value="/login" />
			<form:form modelAttribute="userAttr" class="login100-form validate-form" action="${loginUrl}" method="post">
				<c:if test="${param.error != null}">
					<div class="alert alert-danger">
						<p>Invalid username or password.</p>
					</div>
				</c:if>
				<c:if test="${param.logout != null}">
					<div class="alert alert-success">
						<p>You have been logged out successfully.</p>
					</div>
				</c:if>

					<span class="login100-form-title p-b-49">
						Login
					</span>

				<form:hidden path="id" />
				<div class="wrap-input100 validate-input m-b-23" data-validate = "Username is required">
					<span class="label-input100">Username</span>
					<form:input id="user_email" cssClass="input100" path="email" name="email" placeholder="Type your username" />
					<span class="focus-input100" data-symbol="&#xf206;"></span>
				</div>

				<div class="wrap-input100 validate-input" data-validate="Password is required">
					<span class="label-input100">Password</span>
					<form:input id="user_password" type="password" cssClass="input100" path="password" name="password" placeholder="Type your password" />
					<span class="focus-input100" data-symbol="&#xf190;"></span>
				</div>

				<div class="text-right p-t-8 p-b-31">
					<a href="#">
						Forgot password?
					</a>
				</div>

				<div class="container-login100-form-btn">
					<div class="wrap-login100-form-btn">
						<div class="login100-form-bgbtn"></div>
						<button class="login100-form-btn" id="loginBtn" type="submit">
							Login
						</button>
					</div>
				</div>

				<div class="txt1 text-center p-t-54 p-b-20">
						<span>
							Or Sign Up Using
						</span>
				</div>

				<div class="flex-c-m">
					<a href="#" class="login100-social-item bg1">
						<i class="fa fa-facebook"></i>
					</a>

					<a href="#" class="login100-social-item bg2">
						<i class="fa fa-twitter"></i>
					</a>

					<a href="#" class="login100-social-item bg3">
						<i class="fa fa-google"></i>
					</a>
				</div>

				<div class="flex-col-c p-t-155">
						<span class="txt1 p-b-17">
							Or Sign Up Using
						</span>

					<a href="#" class="txt2">
						Sign Up
					</a>
				</div>
			</form:form>
		</div>
	</div>
</div>

<%--<div class="login-section">
	<div class="login">
		<h2 class="login-title">Garant</h2>
	</div>
	<div class="login-form">
		<h2 class="login-subtitle">Вход</h2>
		<c:url var="loginUrl" value="/login" />
		<form action="${loginUrl}" method="post">
			<c:if test="${param.error != null}">
				<div class="alert alert-danger">
					<p>Invalid username or password.</p>
				</div>
			</c:if>
			<c:if test="${param.logout != null}">
				<div class="alert alert-success">
					<p>You have been logged out successfully.</p>
				</div>
			</c:if>

			<div class="field">
				<div class="input">
					<input class="login-input-login" type="text" id="username"
						name="ssoId" placeholder="Логин" />
				</div>
			</div>

			<div class="field">
				<div class="input">
					<input class="login-input-password" type="password"
						placeholder="Пароль" id="password" name="password" />
				</div>
			</div>

			<div class="field">
				<div class="btn-login-section">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="submit" class="btn-login"
						value="Авторизоваться" />
				</div>

			</div>
		</form>
		<a href="registration" class="login-title-bottom">регистрация</a>
	</div>
	</div>--%>
</body>
</html>

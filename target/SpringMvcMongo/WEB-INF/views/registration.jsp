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
<link href="<c:url value='/static/css/main.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/util.css' />" rel="stylesheet"></link>

</head>
<body>

<div class="limiter">
		<div class="container-login100" style="background-image: url('img/bg-01.jpg');">
			<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
			<form:form modelAttribute="user" method="post" class="login100-form validate-form">
				<form:input type="hidden" path="id" id="id"/>
					<span class="login100-form-title p-b-49">
						Registration
					</span>

					<div class="wrap-input100 validate-input m-b-23" data-validate = "Username is reauired">
						<span class="label-input100">Username</span>
						<input class="input100" type="text" path="name" id="name" placeholder="Type your username">
						<span class="focus-input100" data-symbol="&#xf206;"></span>
					</div>
					
					<div class="wrap-input100 validate-input" data-validate="Password is required">
						<span class="label-input100">Email</span>
						<input class="input100" type="text" path="email" id="email" placeholder="Type your email">
						<span class="focus-input100" data-symbol="&#xf190;"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate="Password is required">
						<span class="label-input100">Password</span>
						<input class="input100" type="password" path="password" id="password" placeholder="Type your password">
						<span class="focus-input100" data-symbol="&#xf190;"></span>
					</div>
					
					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button class="login100-form-btn">
								<input type="submit" class="login100-form-btn" value="Register" />
							</button>
						</div>
					</div>
					
						<div class="flex-col-c p-t-155">
						<span class="txt1 p-b-17">
							Already have account
						</span>
							<a href="login" class="login100-form-btn">Login</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<div id="dropDownSelect1"></div>

    <div class="registration-elements">
	<div class="registration">
		<h2 class="registration-title">Garant</h2>
	 </div>
	<div class="registration-form">
		<h2 class="registration-subtitle">Вход</h2>
			<form:form modelAttribute="user" method="post" >
				<form:input type="hidden" path="id" id="id"/>
			<div class="field">		
				<div class="input">
					<form:input class="registration-input-login" type="text"  path="ssoId" id="ssoId"   placeholder="логин" />
					<form:errors path="ssoId"></form:errors>
				</div>	
			</div>		    
			<div class="field">
				<div class="input">
					<form:input class="registration-input-password" type="password" placeholder="пароль" path="password" id="password" />
					<form:errors path="password"></form:errors>
				</div>
			</div>
				<div class="input">
					<input class="registration-input-password" type="password" name="confirmPassword" placeholder="повторите пароль"
						id="confirmPassword" />
						<form:errors path="confirmPassword"></form:errors>
				</div>
				<form:select type="hidden" path="userProfiles" items="${roles}"  itemValue="id" itemLabel="type"/>
                        
			<div class="field">
				<div class="btn-registration-section">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<input type="submit" class="btn-registration" value="Регистрация" />
				</div>
			</div>
		</form:form>
	</div>
  </div>
</body>
</html>

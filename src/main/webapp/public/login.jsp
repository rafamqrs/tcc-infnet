<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="logo">

	<a href="#"><img src="<c:url value='/resources/img/logo.png'/>"
		alt="" /></a>
	<h3 style="color: silver;">
		<b><spring:message code='project.name' /></b>
	</h3>
</section>

<section class="container" ng-controller="loginController">
	<section class="row">
		<span style="float: right"><spring:message
				code='sample.languages' /> <a href="?lang=en">EN</a> | <a
			href="?lang=pt">PT</a> </span>

		<form method="post" action="j_spring_security_check" role="login">
			<div class="alert alert-danger"
				ng-class="{'': displayLoginError == true, 'none': displayLoginError == false}">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">X</button>
				<div style="text-align: right;">
					<spring:message code="login.error" />
				</div>
			</div>
			<div>
				<label><spring:message code='sample.username' /></label> <input
					type="text" name="j_username" id="j_username" required
					class="form-control" />
			</div>

			<div>
				<label><spring:message code='sample.password' /></label> <input
					type="password" name="j_password" id="j_password" required
					class="form-control" />
			</div>

			<section>
				<button type="submit" name="go" class="btn btn-block">
					<spring:message code='login' />
				</button>
				<p>
					<a href="#myModal" data-toggle="modal"><spring:message
							code='sample.forgetPass' /> </a>
				</p>
			</section>
		</form>
	</section>
</section>

<!-- MODAL ESQUECI A SENHA -->

<div class="modal hide" id="myModal">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">x</button>
		<h3>
			<spring:message code="titulo.recupsenha" />
		</h3>
	</div>
	<div class="modal-body">
		<form method="post" name="login_form" ng-controller="loginController">
			<p>
				<input type="text" class="span3" ng-model="emailRecuperar"
					placeholder="<spring:message code='plch.forgotpass'/>">
			</p>
			<p>

				<input type="submit" class="btn btn-inverse"
					ng-click="recuperarSenha()"
					value='<spring:message code="botao.recuperar"/>' />
			</p>
		</form>
	</div>
</div>

<script src="<c:url value='/resources/js/pages/login.js' />"></script>
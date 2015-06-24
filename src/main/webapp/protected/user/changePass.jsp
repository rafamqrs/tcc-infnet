<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel panel-primary">
	<div class="panel-heading">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="panel-title" id="userLa">
			<span class="glyphicon glyphicon-info-sign"></span>
			<spring:message code="user.mudarsenha" />
		</h4>
	</div>
	<form class="form-horizontal" name="atualizarSenhaForm"
		ng-controller="userController">
		<div class="alert alert-danger-alt alert-dismissable"
			ng-show="dangerMsg">
			<span class="glyphicon glyphicon-certificate"></span>
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">X</button>
			<strong><spring:message code="error.generic.text" /></strong>
		</div>
		<div class="alert alert-success-alt alert-dismissable"
			ng-show="infoMsg">
			<span class="glyphicon glyphicon-certificate"></span>
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">X</button>
			<strong><spring:message code="message.update.success" /></strong>
		</div>

		<div class="control-group">
			<label class="control-label" for="inputType"><spring:message
					code="sample.password" /></label>
			<div class="controls">
				<input type="password" id="password" ng-model="user.password"
					placeholder="<spring:message code="sample.password"/>">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputType"><spring:message
					code="sample.newpassword" /></label>
			<div class="controls">
				<input type="password" id="newpassword" ng-model="user.newpassword"
					placeholder="<spring:message code="sample.newpassword"/>">
			</div>

		</div>
		<div class="control-group">
			<div class="controls">
				<input type="submit" class="btn btn-inverse"
					ng-click="atualizarSenha(atualizarSenhaForm);"
					value='<spring:message code="update"/>' />
			</div>
		</div>
	</form>

	<script src="<c:url value="/resources/js/pages/usuario/usuario.js" />"></script>
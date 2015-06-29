<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!-- MODAL CRIAR -->
<div class="modal fade" id="addPagamentoModal" tabindex="-1"
	role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="panel-title" id="contactLabel">
					<span class="glyphicon glyphicon-info-sign"></span>
					<spring:message code="header.pagamento" />
				</h4>
			</div>
			<div class="alert alert-success-alt alert-dismissable"
				ng-show="infoMsg">
				<span class="glyphicon glyphicon-certificate"></span>
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">X</button>
				<strong><spring:message code="message.create.success" /></strong>
			</div>

			<form name="newPagamentoForm" accept-charset="utf-8">
				<div class="modal-body" style="padding: 5px;">
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-6"
							style="padding-bottom: 10px;">
							<input class="form-control" ng-model="pagamento.formaPagamento"
								placeholder="<spring:message code="pagamento.formapagamento" />"
								type="text" novalidate />
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6"
							style="padding-bottom: 10px;">
								<label><input type="checkbox" ng-model="pagamento.ativo" ><spring:message code="pagamento.ativo" /></label>
						</div>
					</div>
				</div>
				<div class="panel-footer" style="margin-bottom: -14px;">
					<input type="submit" class="btn btn-success"
						value="<spring:message code="create"/>"
						ng-click="createPagamento(newPagamentoForm);" />
					<!--<span class="glyphicon glyphicon-ok"></span>-->
					<input type="reset" class="btn btn-danger"
						value="<spring:message code="reset"/>" />
					<!--<span class="glyphicon glyphicon-remove"></span>-->
					<button style="float: right;" type="button"
						class="btn btn-default btn-close" data-dismiss="modal">
						<spring:message code="close" />
					</button>
				</div>
		</div>
	</div>
</div>

<!-- DELETAR -->
<div class="modal fade" id="deletar" tabindex="-1" role="dialog"
	aria-labelledby="contactLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="panel-title" id="contactLabel">
					<span class="glyphicon glyphicon-info-sign"></span>
					<spring:message code="delete.confirm" />
				</h4>
			</div>
			<div class="alert alert-success-alt alert-dismissable"
				ng-show="infoMsg">
				<span class="glyphicon glyphicon-certificate"></span>
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">X</button>
				<strong><spring:message code="message.update.success" /></strong>
			</div>

			<form name="deleteUserForm">
				<div class="modal-body" style="padding: 5px;">
					<div class="row">
						<center>
						<spring:message code="delete.confirm" />
						<br />
						:&nbsp;{{name}}
						</center> 
					</div>
				</div>
				<div class="panel-footer" style="margin-bottom: -14px;">
					<input type="submit" class="btn btn-danger"
						value="<spring:message code="delete"/>" ng-click="deletar();" />
					<button style="float: right;" type="button"
						class="btn btn-default btn-close" data-dismiss="modal">
						<spring:message code="close" />
					</button>
				</div>
			</form>
		</div>
	</div>
</div>


<!-- MODAL UPDATE -->
<div class="modal fade" id="updatePagamentoModal" tabindex="-1"
	role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="panel-title" id="contactLabel">
					<span class="glyphicon glyphicon-info-sign"></span>
					<spring:message code="header.pagamento" />
				</h4>
			</div>
			<div class="alert alert-success-alt alert-dismissable"
				ng-show="infoMsg">
				<span class="glyphicon glyphicon-certificate"></span>
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">X</button>
				<strong><spring:message code="message.create.success" /></strong>
			</div>

			<form name="newPagamentoForm" accept-charset="utf-8">
				<div class="modal-body" style="padding: 5px;">
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-6"
							style="padding-bottom: 10px;">
							<input class="form-control" ng-model="pagamento.formaPagamento"
								placeholder="<spring:message code="pagamento.formapagamento" />"
								type="text" novalidate />
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6"
							style="padding-bottom: 10px;">
								<label><input type="checkbox" ng-model="pagamento.ativo" ><spring:message code="pagamento.ativo" /></label>
						</div>
					</div>
				</div>
				<div class="panel-footer" style="margin-bottom: -14px;">
					<input type="submit" class="btn btn-success"
						value="<spring:message code="create"/>"
						ng-click="updatePagamento(newPagamentoForm);" />
					<!--<span class="glyphicon glyphicon-ok"></span>-->
					<input type="reset" class="btn btn-danger"
						value="<spring:message code="reset"/>" />
					<!--<span class="glyphicon glyphicon-remove"></span>-->
					<button style="float: right;" type="button"
						class="btn btn-default btn-close" data-dismiss="modal">
						<spring:message code="close" />
					</button>
				</div>
		</div>
	</div>
</div>


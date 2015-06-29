<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!-- MODAL CRIAR -->
<div class="modal fade" id="addFinanceiroModal" tabindex="-1"
	role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="panel-title" id="contactLabel">
					<span class="glyphicon glyphicon-info-sign"></span>
					<spring:message code="financeiro" />
				</h4>
			</div>
			<div class="alert alert-success-alt alert-dismissable"
				ng-show="infoMsg">
				<span class="glyphicon glyphicon-certificate"></span>
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">X</button>
				<strong><spring:message code="message.create.success" /></strong>
			</div>

			<form name="newFinanceiroForm" accept-charset="utf-8">
				<div class="modal-body" style="padding: 5px;">
					<div class="row">
						<div class="col-sm-9">
							<label for="status"><spring:message
									code="user.formaPagamento" /></label> <select class="form-control"
								ng-model="financeiro.idPagamento"
								ng-options="p.idPagamento as p.formaPagamento for p in listaPagamentos">
								<option value=""><spring:message code="selecione" /></option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-9">
							<label for="status"><spring:message
									code="financeiro.customer" /></label> <select class="form-control"
								ng-model="financeiro.idUsuario"
								ng-options="p.id as p.name for p in listaUsuarios">
								<option value=""><spring:message code="selecione" /></option>
							</select>
						</div>
					</div>

					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-6"
							style="padding-bottom: 10px;">
							<label for="status"><spring:message
									code="financeiro.datavencimento" /></label> <input
								class="form-control" type="date"
								ng-model="financeiro.dataVencimento"
								placeholder="<spring:message code='data'/>" novalidate />
						</div>
					</div>
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-6"
							style="padding-bottom: 10px;">
							<label for="status"><spring:message
									code="financeiro.datapagamento" /></label> <input class="form-control"
								type="date" ng-model="financeiro.dataPagamento"
								placeholder="<spring:message code='data'/>" novalidate />
						</div>
					</div>
				</div>
				<div class="panel-footer" style="margin-bottom: -14px;">
					<input type="submit" class="btn btn-success"
						value="<spring:message code="create"/>"
						ng-click="createFinanceiro(newFinanceiroForm);" />
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
							<br /> :&nbsp;{{name}}
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

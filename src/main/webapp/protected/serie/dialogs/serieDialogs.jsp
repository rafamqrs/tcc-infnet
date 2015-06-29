<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<!-- CRIAR MODAL -->
<div class="modal fade" id="addSerieModal" tabindex="-1" role="dialog"
	aria-labelledby="contactLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="panel-title" id="contactLabel">
					<span class="glyphicon glyphicon-info-sign"></span>
					<spring:message code="header.serie" />
				</h4>
			</div>
			<div class="alert alert-success-alt alert-dismissable"
				ng-show="infoMsg">
				<span class="glyphicon glyphicon-certificate"></span>
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">X</button>
				<strong><spring:message code="message.create.success" /></strong>
			</div>


			<form name="newSerieForm" accept-charset="utf-8">
				<div class="modal-body" style="padding: 5px;">
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-6"
							style="padding-bottom: 10px;">
							<input class="form-control" ng-model="serie.nome"
								placeholder="<spring:message code="serie.nome" />" type="text"
								novalidate />
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6"
							style="padding-bottom: 10px;">
							<input class="form-control" ng-model="serie.tipoSerie"
								placeholder="<spring:message code="serie.type" />" type="text"
								novalidate />
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6">
							<div class="form-group multiple-form-group">
								<label>Multiple selects</label>

								<div class="form-group input-group">
									<select class="form-control" ng-model="serie.idExercicios"
										ng-options="p.idExercicio as p.nome for p in listaPagamentos"
										multiple="multiple" integer>
										<option value=""><spring:message code="selecione" /></option>
									</select> <span class="input-group-btn"><button type="button"
											class="btn btn-default btn-add">+</button></span>
								</div>
							</div>

						</div>
					</div>
					<div class="panel-footer" style="margin-bottom: -14px;">
						<input type="submit" class="btn btn-success"
							value="<spring:message code="create"/>"
							ng-click="createSerie(newSerieForm);" /> <input type="reset"
							class="btn btn-danger" value="<spring:message code="reset"/>" />
						<button style="float: right;" type="button"
							class="btn btn-default btn-close" data-dismiss="modal">
							<spring:message code="close" />
						</button>
					</div>
				</div>
		</div>
	</div>

	<!-- ATUALIZAR -->

	<!-- DELETE -->

	<div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true"
		id="deleteSerieModal" aria-labelledby="searchUserModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<h3 id="deleteUserModalLabel" class="displayInLine">
				<spring:message code="delete" />
				&nbsp;
				<spring:message code="user" />
			</h3>
			<form name="deleteContactForm" novalidate>
				<p>
					<spring:message code="delete.confirm" />
					:&nbsp;{{serie.nome}}?
				</p>
				<input type="submit" class="btn btn-inverse"
					ng-click="deleteSerie();" value='<spring:message code="delete"/>' />
				<button class="btn btn-inverse" data-dismiss="modal"
					ng-click="exit('#deleteUserModal');" aria-hidden="true">
					<spring:message code="cancel" />
				</button>
			</form>
			<span class="alert alert-error dialogErrorMessage"
				ng-show="errorOnSubmit"> <spring:message code="request.error" />
			</span> <span class="alert alert-error dialogErrorMessage"
				ng-show="errorIllegalAccess"> <spring:message
					code="request.illegal.access" />
			</span>
		</div>

	</div>
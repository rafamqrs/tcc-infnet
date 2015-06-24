<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="modal fade" id="addExercicioModal" tabindex="-1"
	role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">X</button>
				<h4 class="panel-title" id="contactLabel">
					<span class="glyphicon glyphicon-info-sign"></span>
					<spring:message code="header.exercices" />
				</h4>
			</div>
			<div class="alert alert-success-alt alert-dismissable"
				ng-show="infoMsg">
				<span class="glyphicon glyphicon-certificate"></span>
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">X</button>
				<strong><spring:message code="message.update.success" /></strong>
			</div>

			<form name="newExercicioForm">
				<div class="modal-body" style="padding: 5px;">
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-6"
							style="padding-bottom: 10px;">
							<input class="form-control" ng-model="exercicio.nome"
								placeholder="<spring:message code="exc.nome" />" type="text"
								novalidate />
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6"
							style="padding-bottom: 10px;">
							<input class="form-control" ng-model="exercicio.tipoMuscular"
								placeholder="<spring:message code="exc.tipomuscular" />"
								type="text" novalidate />
						</div>
					</div>
					<spring:message code="exc.qtdrepeticao" />
					/
					<spring:message code="exc.qtdexercicio" />
					:
					<div class="row">

						<div class="col-lg-2 col-md-12 col-sm-12"
							style="padding-bottom: 10px;">
							<input class="form-control" ng-model="exercicio.qtdRepeticao"
								type="number" novalidate />
						</div>
						<div class="col-lg-2 col-md-12 col-sm-12"
							style="padding-bottom: 10px;">
							<input class="form-control" ng-model="exercicio.qtdExercicio"
								type="number" novalidate />
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3 col-md-12 col-sm-12"
							style="padding-bottom: 10px;">
							<input class="form-control" ng-model="exercicio.peso"
								placeholder="<spring:message code="exc.peso" />" type="number"
								novalidate />
						</div>
					</div>
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12">
							<textarea style="resize: vertical;" class="form-control"
								ng-model="exercicio.descricao"
								placeholder="<spring:message code="exc.descricao" />" rows="6"
								name="comment" required></textarea>
						</div>
					</div>
				</div>
				<div class="panel-footer" style="margin-bottom: -14px;">
					<input type="submit" class="btn btn-success"
						value="<spring:message code="create"/>"
						ng-click="createExercicio(newExercicioForm);" />
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


<div class="modal fade" id="deletarExe" tabindex="-1" role="dialog"
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

			<form name="deleteExercicioForm">
				<div class="modal-body" style="padding: 5px;">
					<div class="row">
						<center>
						<spring:message code="delete.confirm" />
						<br />
						&nbsp;{{name}}
						</center> 
					</div>
				</div>
				<div class="panel-footer" style="margin-bottom: -14px;">
					<input type="submit" class="btn btn-danger"
						value="<spring:message code="delete"/>" ng-click="deleteExercicio();" />
					<button style="float: right;" type="button"
						class="btn btn-default btn-close" data-dismiss="modal">
						<spring:message code="close" />
					</button>
				</div>
			</form>
		</div>
	</div>
</div>

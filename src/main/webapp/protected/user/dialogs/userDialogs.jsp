<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog"
	aria-labelledby="contactLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="panel-title" id="contactLabel">
					<span class="glyphicon glyphicon-info-sign"></span>
					<spring:message code="header.user" />
				</h4>
			</div>
			<div class="alert alert-success-alt alert-dismissable"
				ng-show="infoMsg">
				<span class="glyphicon glyphicon-certificate"></span>
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">X</button>
				<strong><spring:message code="message.create.success" /></strong>
			</div>

			<form name="newUserForm" accept-charset="utf-8">
				<div class="modal-body" style="padding: 5px;">
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-6"
							style="padding-bottom: 10px;">
							<input class="form-control" ng-model="user.name"
								placeholder="<spring:message code="user.name" />" type="text"
								novalidate />
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6"
							style="padding-bottom: 10px;">
							<input class="form-control" ng-model="user.email"
								placeholder="<spring:message code="contacts.email" />"
								type="text" novalidate />
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12"
							style="padding-bottom: 10px;">
							<input class="form-control" ng-model="user.endereco"
								placeholder="<spring:message code="user.endereco" />"
								type="text" novalidate />
						</div>
					</div>
					<c:if test="${pageContext.response.locale == 'pt'}">
					
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12"
								style="padding-bottom: 10px;">
								<input class="form-control" ng-model="user.cpf"
									placeholder="<spring:message code="user.cpf" />"
									type="text" novalidate />
							</div>
						</div>
					</c:if>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12"
							style="padding-bottom: 10px;">
							<input class="form-control" type="date" id="dataNascimento"
								name="input" ng-model="user.dataNascimento"
								placeholder="<spring:message code='data'/>" min="1900-01-01"
								novalidate />
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12"
							style="padding-bottom: 10px;">
							<input class="form-control" ng-model="user.telefone"
								placeholder="<spring:message code="sample.phone" />" type="text"
								novalidate />
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12"
							style="padding-bottom: 10px;">
							<input class="form-control" ng-model="user.cep"
								placeholder="<spring:message code="user.cep" />" type="text"
								novalidate maxlength="8"/>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-9">
							<label for="status"><spring:message code="user.role" /></label>
							<select class="form-control" id="regra" ng-model="user.role"
								novalidate>
								<sec:authorize ifAnyGranted="ROLE_ADMIN">
									<option value="ROLE_ADMIN"><spring:message
											code="user.admin" /></option>
								</sec:authorize>
								<option value="ROLE_USER"><spring:message
										code="user.user" /></option>
								<option value="ROLE_EMPLOYEE"><spring:message
										code="user.employee" /></option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-9">
							<label for="status"><spring:message code="user.ativo" /></label>
							<select class="form-control" id="regra" ng-model="user.enabled"
								novalidate>
								<option value="true"><spring:message code="user.true" /></option>
								<option value="false"><spring:message code="user.false" /></option>
							</select>
						</div>
					</div>
				</div>
				<div class="panel-footer" style="margin-bottom: -14px;">
					<input type="submit" class="btn btn-success"
						value="<spring:message code="create"/>"
						ng-click="createUser(newUserForm);" /> <input type="reset"
						class="btn btn-danger" value="<spring:message code="reset"/>" />
					<button style="float: right;" type="button"
						class="btn btn-default btn-close" data-dismiss="modal">
						<spring:message code="close" />
					</button>
				</div>
		</div>
	</div>
</div>

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
						value="<spring:message code="delete"/>" ng-click="deleteUser();" />
					<button style="float: right;" type="button"
						class="btn btn-default btn-close" data-dismiss="modal">
						<spring:message code="close" />
					</button>
				</div>
			</form>
		</div>
	</div>
</div>


<div id="updateUserModal"
	class="modal hide fade in centering insertAndUpdateDialogs"
	role="dialog" aria-labelledby="updateUserModalLabel" aria-hidden="true">
	<div class="modal-header">
		<h3 id="updateUserModalLabel" class="displayInLine">
			<spring:message code="update" />
			&nbsp;
			<spring:message code="user" />
		</h3>
	</div>
	<div class="modal-body">
		<form name="updateContactForm" novalidate>
			<input type="hidden" required ng-model="contact.id" name="id"
				value="{{contact.id}}" />

			<div class="pull-left">
				<div>
					<div class="input-append">
						<label>* <spring:message code="contacts.name" />:
						</label>
					</div>
					<div class="input-append">
						<input type="text" autofocus required ng-model="contact.name"
							name="name"
							placeholder="<spring:message code='contact'/>&nbsp;<spring:message code='contacts.name'/> " />
					</div>
					<div class="input-append">
						<label> <span class="alert alert-error"
							ng-show="displayValidationError && updateContactForm.name.$error.required">
								<spring:message code="required" />
						</span>
						</label>
					</div>
				</div>
				<div>
					<div class="input-append">
						<label>* <spring:message code="contacts.email" />:
						</label>
					</div>
					<div class="input-append">
						<input type="text" required ng-model="contact.email" name="email"
							placeholder="<spring:message code='sample.email'/> " />
					</div>
					<div class="input-append">
						<label> <span class="alert alert-error"
							ng-show="displayValidationError && updateContactForm.email.$error.required">
								<spring:message code="required" />
						</span>
						</label>
					</div>
				</div>
				<div>
					<div class="input-append">
						<label>* <spring:message code="contacts.phone" />:
						</label>
					</div>
					<div class="input-append">
						<input type="text" required ng-model="contact.phoneNumber"
							name="phoneNumber"
							placeholder="<spring:message code='sample.phone'/> " />
					</div>
					<div class="input-append">
						<label> <span class="alert alert-error"
							ng-show="displayValidationError && updateContactForm.phoneNumber.$error.required">
								<spring:message code="required" />
						</span>
						</label>
					</div>
				</div>
				<input type="submit" class="btn btn-inverse"
					ng-click="updateContact(updateContactForm);"
					value='<spring:message code="update"/>' />
				<button class="btn btn-inverse" data-dismiss="modal"
					ng-click="exit('#updateUserModal');" aria-hidden="true">
					<spring:message code="cancel" />
				</button>
			</div>
		</form>
	</div>
	<span class="alert alert-error dialogErrorMessage"
		ng-show="errorOnSubmit"> <spring:message code="request.error" />
	</span>
</div>






<div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true"
	id="deleteUserModal" aria-labelledby="searchUserModalLabel"
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
				:&nbsp;{{user.name}}?
			</p>
			<input type="submit" class="btn btn-inverse"
				ng-click="deleteContact();" value='<spring:message code="delete"/>' />
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

<div id="searchUserModal" class="modal hide fade" role="dialog"
	aria-labelledby="searchUserModalLabel" aria-hidden="true">
	<div class="modal-header">
		<h3 id="searchUserModalLabel" class="displayInLine">
			<spring:message code="search" />
		</h3>
	</div>
	<div class="modal-dialog">
		<form name="searchContactForm" novalidate>
			<label><spring:message code="search.for" /></label>

			<div>
				<div class="input-append">
					<input type="text" autofocus required ng-model="searchFor"
						name="searchFor"
						placeholder="<spring:message code='contact'/>&nbsp;<spring:message code='contacts.name'/> " />
				</div>
				<div class="input-append displayInLine">
					<label class="displayInLine"> <span
						class="alert alert-error"
						ng-show="displayValidationError && searchContactForm.searchFor.$error.required">
							<spring:message code="required" />
					</span>
					</label>
				</div>
			</div>
			<input type="submit" class="btn btn-inverse"
				ng-click="searchContact(searchContactForm, false);"
				value='<spring:message code="search"/>' />
			<button class="btn btn-inverse" data-dismiss="modal"
				ng-click="exit('#searchUserModal');" aria-hidden="true">
				<spring:message code="cancel" />
			</button>
		</form>
	</div>
	<span class="alert alert-error dialogErrorMessage"
		ng-show="errorOnSubmit"> <spring:message code="request.error" />
	</span>
</div>

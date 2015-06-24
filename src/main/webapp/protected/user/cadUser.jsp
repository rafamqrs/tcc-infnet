<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row-fluid" ng-controller="userController">
	<h2>
		<p class="text-center">
			<spring:message code='user.header' />
			<a href="#searchContactsModal" id="contactsHeaderButton"
				role="button"
				ng-class="{'': displaySearchButton == true, 'none': displaySearchButton == false}"
				title="<spring:message code="search"/>&nbsp;<spring:message code="contact"/>"
				class="btn btn-inverse" data-toggle="modal"> <i
				class="icon-search"></i>
			</a>
		</p>
	</h2>
	<h4>
		<div ng-class="{'': state == 'list', 'none': state != 'list'}">
			<p class="text-center">
				<spring:message code="message.total.records.found" />
				:&nbsp;{{page.totalContacts}}
			</p>
		</div>
	</h4>

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

	<div>
		<div id="loadingModal" class="modal hide fade in centering"
			role="dialog" aria-labelledby="deleteContactsModalLabel"
			aria-hidden="true">
			<div id="divLoadingIcon" class="text-center">
				<div class="icon-align-center loading"></div>
			</div>
		</div>

		<div
			ng-class="{'alert badge-inverse': displaySearchMessage == true, 'none': displaySearchMessage == false}">
			<h4>
				<p class="messageToUser">
					<i class="icon-info-sign"></i>&nbsp;{{page.searchMessage}}
				</p>
			</h4>
			<a href="#" role="button" ng-click="resetSearch();"
				ng-class="{'': displaySearchMessage == true, 'none': displaySearchMessage == false}"
				title="<spring:message code='search.reset'/>"
				class="btn btn-inverse" data-toggle="modal"> <i
				class="icon-remove"></i> <spring:message code="search.reset" />
			</a>
		</div>

		<div
			ng-class="{'alert badge-inverse': displayMessageToUser == true, 'none': displayMessageToUser == false}">
			<h4 class="displayInLine">
				<p class="messageToUser displayInLine">
					<i class="icon-info-sign"></i>&nbsp;{{page.actionMessage}}
				</p>
			</h4>
		</div>

		<div
			ng-class="{'alert alert-block alert-error': state == 'error', 'none': state != 'error'}">
			<h4>
				<i class="icon-info-sign"></i>
				<spring:message code="error.generic.header" />
			</h4>
			<br />

			<p>
				<spring:message code="error.generic.text" />
			</p>
		</div>

		<div
			ng-class="{'alert alert-info': state == 'noresult', 'none': state != 'noresult'}">
			<h4>
				<i class="icon-info-sign"></i>
				<spring:message code="user.emptyData" />
			</h4>
			<br />

			<p>
				<spring:message code="user.emptyData.text" />
			</p>
		</div>

		<div id="gridContainer"
			ng-class="{'': state == 'list', 'none': state != 'list'}">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th scope="col"><spring:message code="contacts.name" /></th>
						<th scope="col"><spring:message code="contacts.email" /></th>
						<th scope="col"><spring:message code="contacts.phone" /></th>
						<th scope="col"><spring:message code="user.datanascimento" /></th>
						<th scope="col"><spring:message code="user.ativo" /></th>
						<th scope="col"><spring:message code="user.cep" /></th>
						<th scope="col"><spring:message code="acao" /></th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="user in page.source">
						<td class="tdContactsCentered">{{user.name}}</td>
						<td class="tdContactsCentered">{{user.email}}</td>
						<td class="tdContactsCentered">{{user.telefone}}</td>
						<c:if test="${pageContext.response.locale == 'en'}">
							<td class="tdContactsCentered">{{user.dataNascimento |
								date:'yyyy-MM-dd'}}</td>
						</c:if>
						<c:if test="${pageContext.response.locale == 'pt'}">
							<td class="tdContactsCentered">{{user.dataNascimento |
								date:'dd-MM-yyyy'}}</td>
						</c:if>
						<td class="tdContactsCentered">{{user.enabled}}</td>
						<td class="tdContactsCentered">{{user.cep}}</td>
						<td class="width15">
							<div class="text-center">
								<input type="hidden" value="{{contact.id}}" /> <a
									href="#updateContactsModal"
									ng-click="selectedContact(contact);" role="button"
									title="<spring:message code="update"/>&nbsp;<spring:message code="contact"/>"
									class="btn btn-inverse" data-toggle="modal"> <i
									class="glyphicon glyphicon-pencil"></i>
								</a> <a href="#deletar"
									ng-click="selectedUser(user.idUsuario, user.name);"
									role="button"
									title="<spring:message code="delete"/>&nbsp;<spring:message code="contact"/>"
									class="glyphicon glyphicon-inverse" data-toggle="modal"> <i
									class="glyphicon glyphicon-minus"></i>
								</a>
							</div>
						</td>
					</tr>
				</tbody>
			</table>

			<div class="text-center">
				<button href="#" class="btn btn-inverse"
					ng-class="{'btn-inverse': page.currentPage != 0, 'disabled': page.currentPage == 0}"
					ng-disabled="page.currentPage == 0" ng-click="changePage(0)"
					title='<spring:message code="pagination.first"/>'>
					<spring:message code="pagination.first" />
				</button>
				<button href="#" class="btn btn-inverse"
					ng-class="{'btn-inverse': page.currentPage != 0, 'disabled': page.currentPage == 0}"
					ng-disabled="page.currentPage == 0" class="btn btn-inverse"
					ng-click="changePage(page.currentPage - 1)"
					title='<spring:message code="pagination.back"/>'>&lt;</button>
				<span>{{page.currentPage + 1}} <spring:message
						code="pagination.of" /> {{page.pagesCount}}
				</span>
				<button href="#" class="btn btn-inverse"
					ng-class="{'btn-inverse': page.pagesCount - 1 != page.currentPage, 'disabled': page.pagesCount - 1 == page.currentPage}"
					ng-click="changePage(page.currentPage + 1)"
					ng-disabled="page.pagesCount - 1 == page.currentPage"
					title='<spring:message code="pagination.next"/>'>&gt;</button>
				<button href="#" class="btn btn-inverse"
					ng-class="{'btn-inverse': page.pagesCount - 1 != page.currentPage, 'disabled': page.pagesCount - 1 == page.currentPage}"
					ng-disabled="page.pagesCount - 1 == page.currentPage"
					ng-click="changePage(page.pagesCount - 1)"
					title='<spring:message code="pagination.last"/>'>
					<spring:message code="pagination.last" />
				</button>
			</div>
		</div>
		<div
			ng-class="{'text-center': displayCreateContactButton == true, 'none': displayCreateContactButton == false}">
			<br /> <a href="#addUserModal" role="button"
				ng-click="resetContact();"
				title="<spring:message code='create'/>&nbsp;<spring:message code='user'/>"
				class="btn btn-success" data-toggle="modal"> <i
				class="glyphicon glyphicon-plus"></i> &nbsp;&nbsp;<spring:message
					code="create" />&nbsp;<spring:message code="user" />
			</a>
		</div>

		<jsp:include page="dialogs/userDialogs.jsp" />

	</div>
</div>

<script src="<c:url value="/resources/js/pages/usuario/usuario.js" />"></script>
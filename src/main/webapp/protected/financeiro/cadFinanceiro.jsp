<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="row-fluid" ng-controller="pagamentoController">
	<h2>
		<p class="text-center">
			<spring:message code='financeiro' />
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
				<spring:message code="financeiro.emptyData" />
			</h4>
			<br />

			<p>
				<spring:message code="pagamento.emptyData.text" />
			</p>
		</div>
		<div class="alert alert-danger alert-dismissable" ng-show="dangerMsg">
			<span class="glyphicon glyphicon-certificate"></span>
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">X</button>
			<strong><spring:message code="error.generic.text" /></strong>
		</div>
		<div class="alert alert-success alert-dismissable" ng-show="infoMsg">
			<span class="glyphicon glyphicon-certificate"></span>
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">X</button>
			<strong><spring:message code="message.create.success" /></strong>
		</div>

		<div id="gridContainer"
			ng-class="{'': state == 'list', 'none': state != 'list'}">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th scope="col"><spring:message code="financeiro.customer" /></th>
						<th scope="col"><spring:message
								code="financeiro.tipopagamento" /></th>
						<th scope="col"><spring:message
								code="financeiro.datavencimento" /></th>
						<th scope="col"><spring:message code="financeiro.mes" /></th>
						<th scope="col"><spring:message
								code="financeiro.diasematraso" /></th>
						<th scope="col"><spring:message code="acao" /></th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="financeiro in page.source">
						<td class="tdContactsCentered">{{financeiro.usuario.name}}</td>
						<td class="tdContactsCentered">{{financeiro.pagamento.formaPagamento}}</td>
						<td class="tdContactsCentered">{{financeiro.dataVencimento}}</td>
						<td class="tdContactsCentered">{{financeiro.quantidadeDiasEmAtraso}}</td>
						<td class="tdContactsCentered">{{financeiro.dataPagamento}}</td>
						<td class="width15">
							<div class="text-center">
								<input type="hidden" value="{{pagamento.idPagamento}}" /> <a
									href="#updatePagamentoModal" ng-click="selecionar(pagamento);"
									role="button"
									title="<spring:message code="update"/>&nbsp;<spring:message code="contact"/>"
									class="btn btn-inverse" data-toggle="modal"> <i
									class="glyphicon glyphicon-pencil"></i>
								</a> <a href="#deletar"
									ng-click="selectedPagamento(pagamento.idPagamento, pagamento.formaPagamento);"
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
		<sec:authorize ifAnyGranted="ROLE_ADMIN">
			<div
				ng-class="{'text-center': displayCreateContactButton == true, 'none': displayCreateContactButton == false}">
				<br /> <a href="#addFinanceiroModal" role="button"
					ng-click="resetFinanceiro();"
					title="<spring:message code='create'/>&nbsp;<spring:message code='pagamento'/>"
					class="btn btn-success" data-toggle="modal"> <i
					class="glyphicon glyphicon-plus"></i> &nbsp;&nbsp;<spring:message
						code="create" />
				</a>
			</div>
		</sec:authorize>
		<jsp:include page="dialogs/financeiroDialogs.jsp" />

	</div>
</div>

<script src="<c:url value="/resources/js/pages/financeiro.js" />"></script>
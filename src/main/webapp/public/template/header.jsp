<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-default" ng-controller="LocationController">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><spring:message
					code="project.name" /></a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li
					ng-class="{'active': activeURL == 'home', '': activeURL != 'home'}"><a
					href="<c:url value="/"/>"><spring:message code="header.home" /></a></li>

				<sec:authorize ifAnyGranted="ROLE_ADMIN">
					<li class="dropdown"
						ng-class="{'gray': activeURL == 'pagamento', '': activeURL != 'pagamento'}"><a
						class="dropdown-toggle" data-toggle="dropdown" href="#"><spring:message
								code="header.adm" /> <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value='/protected/pagamento'/>"><spring:message
										code="header.pagamento" /></a></li>
							<li><a href="<c:url value='/protected/financeiro'/>"><spring:message
										code="financeiro" /></a></li>
					
						</ul>
					</li>
					<li
						ng-class="{'gray': activeURL == 'user', '': activeURL != 'user'}"><a
						title='<spring:message code="header.user"/>'
						href="<c:url value='/protected/user'/>"><p>
								<spring:message code="header.user" />
							</p></a></li>
					<li class="dropdown"
						ng-class="{'gray': activeURL == 'serie', '': activeURL != 'serie'}"><a
						class="dropdown-toggle" data-toggle="dropdown" href="#"><spring:message
								code="header.serie" /> <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value='/protected/serie'/>"><spring:message
										code="header.serie" /></a></li>
							<li><a href="<c:url value='/protected/exercicio'/>"><spring:message
										code="header.exercices" /></a></li>
							<li><a href="<c:url value='/protected/avaliacao'/>"><spring:message
										code="header.avaliacao" /></a></li>
						</ul></li>

				</sec:authorize>

			</ul>
			<ul class="nav navbar-nav navbar-right">

				<span style="float: right"> <spring:message
						code='sample.languages' /> <a href="?lang=en">EN</a> | <a
					href="?lang=pt">PT</a></span>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>${user.name }
						<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value='/protected/user/preference'/>"><span
								class="glyphicon glyphicon-cog"></span>Preferences</a></li>
						<li><a href="<c:url value='/protected/user/alterarSenha'/>"><span
								class="glyphicon glyphicon-edit"></span> <spring:message
									code="user.mudarsenha" /></a></li>
						<sec:authorize ifAnyGranted="ROLE_USER">
							<li><a href="<c:url value='/protected/user/contact'/>"><span
									class="glyphicon glyphicon-cog"></span> <spring:message
										code="user.contact" /></a></li>
						</sec:authorize>
						<li class="divider"></li>
						<li><a href="<c:url value='/logout' />"><span
								class="glyphicon glyphicon-log-out"></span> <spring:message
									code="header.logout" /></a></li>
					</ul></li>

			</ul>
		</div>
	</div>
</nav>


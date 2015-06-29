<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html lang="pt-BR" id="ng-app" ng-app="">
<head>
<title><spring:message code="project.title" /></title>
<link href="<c:url value='/resources/css/project_style.css'  />"
	rel="stylesheet" />
<link href="<c:url value='/resources/css/login_style.css'  />"
	rel="stylesheet" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<style>
[ng\:cloak], [ng-cloak], .ng-cloak {
	display: none !important;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/css/jasny-bootstrap.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js"></script>
<script src="<c:url value='/resources/js/angular.min.js' />"></script>
<script src="<c:url value='/resources/js/angular-payment.js' />"></script>
<script
	src="<c:url value='/resources/js/angularjs-dropdown-multiselect.min.js' />"></script>
<script
	src="<c:url value='/resources/js/util.js' />"></script>
<script type="text/javascript">
	(function($) {
		$(function() {

			var addFormGroup = function(event) {
				event.preventDefault();

				var $formGroup = $(this).closest('.form-group');
				var $multipleFormGroup = $formGroup
						.closest('.multiple-form-group');
				var $formGroupClone = $formGroup.clone();

				$(this)
						.toggleClass(
								'btn-default btn-add btn-danger btn-remove')
						.html('–');

				$formGroupClone.find('input').val('');
				$formGroupClone.insertAfter($formGroup);

				var $lastFormGroupLast = $multipleFormGroup
						.find('.form-group:last');
				if ($multipleFormGroup.data('max') <= countFormGroup($multipleFormGroup)) {
					$lastFormGroupLast.find('.btn-add').attr('disabled', true);
				}
			};

			var removeFormGroup = function(event) {
				event.preventDefault();

				var $formGroup = $(this).closest('.form-group');
				var $multipleFormGroup = $formGroup
						.closest('.multiple-form-group');

				var $lastFormGroupLast = $multipleFormGroup
						.find('.form-group:last');
				if ($multipleFormGroup.data('max') >= countFormGroup($multipleFormGroup)) {
					$lastFormGroupLast.find('.btn-add').attr('disabled', false);
				}

				$formGroup.remove();
			};

			var countFormGroup = function($form) {
				return $form.find('.form-group').length;
			};

			$(document).on('click', '.btn-add', addFormGroup);
			$(document).on('click', '.btn-remove', removeFormGroup);

		});
	})(jQuery);
</script>
</head>
<body>
	<div class="container">
		<tiles:insertAttribute name="header" />

		<tiles:insertAttribute name="body" />
	</div>

	<!--[if IE]>
            <script src="<c:url value='/resources/js/bootstrap.min.ie.js' />"></script>
        <![endif]-->
	<!--[if !IE]><!-->

	<!--<![endif]-->

	<tiles:insertAttribute name="footer" />
</body>
</html>
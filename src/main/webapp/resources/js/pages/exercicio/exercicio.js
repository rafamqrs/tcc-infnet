function exerciciosController($scope, $http) {
	
	$scope.pageToGet = 0;

	$scope.state = 'busy';

	$scope.lastAction = '';

	$scope.url = "/academiaDigital/protected/exercicio/";

	$scope.errorOnSubmit = false;
	$scope.errorIllegalAccess = false;
	$scope.displayMessageToUser = false;
	$scope.displayValidationError = false;
	$scope.displaySearchMessage = false;
	$scope.displaySearchButton = false;
	$scope.displayCreateContactButton = false;
	$scope.infoMsg = false;
	$scope.dangerMsg = false;

	$scope.exercicio = {}

	$scope.searchFor = ""

	$scope.getContactList = function() {
		var url = $scope.url;
		$scope.lastAction = 'list';

		$scope.startDialogAjaxRequest();

		var config = {
			params : {
				page : $scope.pageToGet
			}
		};

		$http.get(url, config).success(function(data) {
			$scope.finishAjaxCallOnSuccess(data, null, false);
		}).error(function() {
			$scope.state = 'error';
			$scope.displayCreateContactButton = false;
		});
	}

	$scope.populateTable = function(data) {
		if (data.pagesCount > 0) {
			$scope.state = 'list';

			$scope.page = {
				source : data.exercicios,
				currentPage : $scope.pageToGet,
				pagesCount : data.pagesCount,
				totalContacts : data.totalUser
			};
			if ($scope.page.pagesCount <= $scope.page.currentPage) {
				$scope.pageToGet = $scope.page.pagesCount - 1;
				$scope.page.currentPage = $scope.page.pagesCount - 1;
			}

			$scope.displayCreateContactButton = true;
			$scope.displaySearchButton = true;
		} else {
			$scope.state = 'noresult';
			$scope.displayCreateContactButton = true;

			if (!$scope.searchFor) {
				$scope.displaySearchButton = false;
			}
		}

		if (data.actionMessage || data.searchMessage) {
			$scope.displayMessageToUser = $scope.lastAction != 'search';

			$scope.page.actionMessage = data.actionMessage;
			$scope.page.searchMessage = data.searchMessage;
		} else {
			$scope.displayMessageToUser = false;
		}
	}

	$scope.changePage = function(page) {
		$scope.pageToGet = page;

		if ($scope.searchFor) {
			$scope.searchContact($scope.searchFor, true);
		} else {
			$scope.getContactList();
		}
	};

	$scope.exit = function(modalId) {
		$(modalId).modal('hide');

		$scope.contact = {};
		$scope.errorOnSubmit = false;
		$scope.errorIllegalAccess = false;
		$scope.displayValidationError = false;
	}

	$scope.finishAjaxCallOnSuccess = function(data, modalId, isPagination) {
		$scope.populateTable(data);
		$("#loadingModal").modal('hide');

		if (!isPagination) {
			if (modalId) {
				$scope.exit(modalId);
			}
		}

		$scope.lastAction = '';
	}

	$scope.startDialogAjaxRequest = function() {
		$scope.displayValidationError = false;
		$("#loadingModal").modal('show');
		$scope.previousState = $scope.state;
		$scope.state = 'busy';
	}

	$scope.handleErrorInDialogs = function(status) {
		$("#loadingModal").modal('hide');
		$scope.state = $scope.previousState;

		// illegal access
		if (status == 403) {
			$scope.errorIllegalAccess = true;
			return;
		}

		$scope.errorOnSubmit = true;
		$scope.lastAction = '';
	}

	$scope.addSearchParametersIfNeeded = function(config, isPagination) {
		if (!config.params) {
			config.params = {};
		}

		config.params.page = $scope.pageToGet;

		if ($scope.searchFor) {
			config.params.searchFor = $scope.searchFor;
		}
	}

	$scope.resetContact = function() {
		$scope.user = {};
		$scope.infoMsg = false;
		$scope.dangerMsg = false;
	};
	// CREATE Exercicio
	$scope.createExercicio = function(newExercicioForm) {
		if (!newExercicioForm.$valid) {
			$scope.displayValidationError = true;
			return;
		}

		$scope.lastAction = 'create';

		var url = $scope.url;

		var config = {
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
			}
		};

		$scope.addSearchParametersIfNeeded(config, false);

		$scope.startDialogAjaxRequest();
		$scope.infoMsg = false;
		$http.post(url, $.param($scope.exercicio), config).success(
				function(data) {
					$scope.infoMsg = true;
					$scope.finishAjaxCallOnSuccess(data, "#addExercicioModal",
							false);
				}).error(function(data, status, headers, config) {
			$scope.handleErrorInDialogs(status);
		});
	};

	$scope.selectedExercicio = function(idusuario, name) {
		$scope.name = name;
		$scope.id = idusuario;
	}

	$scope.searchContact = function(searchContactForm, isPagination) {
		if (!($scope.searchFor) && (!searchContactForm.$valid)) {
			$scope.displayValidationError = true;
			return;
		}

		$scope.lastAction = 'search';

		var url = $scope.url + $scope.searchFor;

		$scope.startDialogAjaxRequest();

		var config = {};

		if ($scope.searchFor) {
			$scope.addSearchParametersIfNeeded(config, isPagination);
		}

		$http.get(url, config).success(
				function(data) {
					$scope.finishAjaxCallOnSuccess(data,
							"#searchContactsModal", isPagination);
					$scope.displaySearchMessage = true;
				}).error(function(data, status, headers, config) {
			$scope.handleErrorInDialogs(status);
		});
	};

	$scope.deleteExercicio = function() {
		$scope.lastAction = 'delete';

		var url = $scope.url + $scope.id;

		$scope.startDialogAjaxRequest();

		var params = {
			searchFor : $scope.searchFor,
			page : $scope.pageToGet
		};
		$scope.infoMsg = false;
		$scope.dangerMsg = false;
		$http({
			method : 'DELETE',
			url : url,
			params : params
		}).success(function(data) {
			$scope.resetContact();
			$scope.finishAjaxCallOnSuccess(data, "#deletarExe", false);
			$scope.infoMsg = true;
		}).error(function(data, status, headers, config) {
			$scope.dangerMsg = true;
			$scope.handleErrorInDialogs(status);
		});
	};

	$scope.resetSearch = function() {
		$scope.searchFor = "";
		$scope.pageToGet = 0;
		$scope.getContactList();
		$scope.displaySearchMessage = false;
	}

	$scope.getContactList();
}

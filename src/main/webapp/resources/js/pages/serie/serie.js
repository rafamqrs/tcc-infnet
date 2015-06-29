function serieController($scope, $http) {
	$scope.pageToGet = 0;

	$scope.state = 'busy';

	$scope.lastAction = '';

	$scope.url = "/academiaDigital/protected/serie/";

	$scope.errorOnSubmit = false;
	$scope.errorIllegalAccess = false;
	$scope.displayMessageToUser = false;
	$scope.displayValidationError = false;
	$scope.displaySearchMessage = false;
	$scope.displaySearchButton = false;
	$scope.displayCreateContactButton = false;
	$scope.infoMsg = false;
	$scope.dangerMsg = false;

	$scope.serie = {}
	$scope.serie.exercicio = {}

	$scope.searchFor = ""

	$scope.popularListaExercicios = function() {
		var urlListaPagamentos = "http://localhost:8080/academiaDigital/protected/exercicio/listarExercicios";
		$http.get(urlListaPagamentos).success(function(data, status) {
			$scope.listaPagamentos = data.exercicios;
		}).error(function(status) {
			$scope.state = 'error';
			console.log(status);
			$scope.displayCreateContactButton = false;
		});
	}
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
				source : data.series,
				currentPage : $scope.pageToGet,
				pagesCount : data.pagesCount,
				totalContacts : data.totalContacts
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

	$scope.resetSerie = function() {
		$scope.serie = {};
	};
	// CREATE USUARIO
	$scope.createSerie = function(newSerieForm) {
		if (!newSerieForm.$valid) {
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
		$http.post(url, $.param($scope.serie), config).success(function(data) {
			$scope.infoMsg = true;
			$scope.finishAjaxCallOnSuccess(data, "#addUsersModal", false);
		}).error(function(data, status, headers, config) {
			$scope.handleErrorInDialogs(status);
		});
	};

	$scope.selectedSerie = function(idusuario, name) {
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

	$scope.deleteSerie = function() {
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
			$scope.finishAjaxCallOnSuccess(data, "#deletar", false);
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
	$scope.popularListaExercicios();
}

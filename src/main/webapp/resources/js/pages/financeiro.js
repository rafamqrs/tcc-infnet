function pagamentoController($scope, $http) {
	$scope.pageToGet = 0;

	$scope.state = 'busy';

	$scope.lastAction = '';

	$scope.url = "/academiaDigital/protected/financeiro/";

	$scope.errorOnSubmit = false;
	$scope.errorIllegalAccess = false;
	$scope.displayMessageToUser = false;
	$scope.displayValidationError = false;
	$scope.displaySearchMessage = false;
	$scope.displaySearchButton = false;
	$scope.displayCreateContactButton = true;
	$scope.dangerMsg = false;
	$scope.infoMsg = false;

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

	$scope.popularListaPagamento = function() {
		$scope.listaPagamentos = {};
		var urlListaPagamentos = "http://localhost:8080/academiaDigital/protected/pagamento/listarPagamentos";
		$http.get(urlListaPagamentos).success(function(data, status) {
			$scope.listaPagamentos = data.pagamentos;
		}).error(function(status) {
			$scope.state = 'error';
			console.log(status);
			$scope.displayCreateContactButton = false;
		});
	}
	$scope.listarFinanceiros = function() {
		var urlListaPagamentos = "http://localhost:8080/academiaDigital/protected/financeiro/listarFinanceiros";
		$http.get(urlListaPagamentos).success(function(data, status) {
			if(data.totalFinanceiro > 0){
				$scope.state = 'list';
				$scope.displayCreateContactButton = true;
				$scope.page = {
					source : data.financeiros,
					currentPage : $scope.pageToGet,
					pagesCount : data.pagesCount,
					totalContacts : data.totalFinanceiro
				};
			}
		}).error(function(status) {
			$scope.state = 'error';
			console.log(status);
			$scope.displayCreateContactButton = false;
		});
	}
	$scope.popularListaUsuario = function() {
		var urlListaPagamentos = "http://localhost:8080/academiaDigital/protected/user/listarUsuarios";
		
		$http.get(urlListaPagamentos).success(function(data, status) {
			$scope.listaUsuarios = data.users;
		}).error(function(status) {
			$scope.state = 'error';
			console.log(status);
			$scope.displayCreateContactButton = false;
		});
	}

	$scope.getUsuario = function(id) {
		var urlListaPagamentos = "http://localhost:8080/academiaDigital/protected/user/getUser/"
				+ $scope.financeiro.usuario;
		$http.get(urlListaPagamentos).success(function(data) {
			var dataUsuario = data;
		}).error(function(status) {
			$scope.displayCreateContactButton = false;
		});

	}

	$scope.populateTable = function(data) {
		console.log(data);
		if (data.pagesCount > 0) {
			$scope.state = 'list';

			$scope.page = {
				source : data.financeiros,
				currentPage : $scope.pageToGet,
				pagesCount : data.pagesCount,
				totalContacts : data.totalFinanceiro
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
		$scope.listarFinanceiros();
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

	$scope.resetFinanceiro = function() {
		$scope.financeiro = {};
	};

	$scope.createFinanceiro = function(newFinanceiroForm) {
		if (!newFinanceiroForm.$valid) {
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
		var date = new Date($scope.financeiro.dataPagamento);
		var date2 = new Date($scope.financeiro.dataVencimento);
		$scope.financeiro.dataPagamento = date;
		$scope.financeiro.dataVencimento = date2;
		$scope.getUsuario($scope.financeiro.usuario);
		$http.post(url, $.param($scope.financeiro), config).success(
				function(data) {
					$scope.infoMsg = true;
					$scope.finishAjaxCallOnSuccess(data, "#addPagamentoModal",
							false);
				}).error(function(data, status, headers, config) {
			$scope.dangerMsg = true;
			$scope.handleErrorInDialogs(status);
		});
	};

	$scope.selectedPagamento = function(id, tipo) {
		$scope.dangerMsg = false;
		$scope.infoMsg = false;
		$scope.idPagamento = id;
		$scope.name = tipo;

	}

	$scope.selecionar = function(pagamento) {
		$scope.dangerMsg = false;
		$scope.infoMsg = false;
		$scope.pagamento = pagamento;
	}

	$scope.updatePagamento = function(updatePagamentoForm) {
		if (!updatePagamentoForm.$valid) {
			$scope.displayValidationError = true;
			return;
		}

		$scope.lastAction = 'update';

		var url = $scope.url + $scope.pagamento.idPagamento;

		$scope.startDialogAjaxRequest();

		var config = {}

		$scope.addSearchParametersIfNeeded(config, false);
		$http.put(url, $scope.pagamento, config).success(
				function(data) {
					$scope.infoMsg = true;
					$scope.finishAjaxCallOnSuccess(data,
							"#updatePagamentoModal", false);
				}).error(function(data, status, headers, config) {
			$scope.dangerMsg = true;
			$scope.handleErrorInDialogs(status);
		});
	};

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

	$scope.deletar = function() {
		$scope.lastAction = 'delete';

		var url = $scope.url + $scope.idPagamento;

		$scope.startDialogAjaxRequest();

		var params = {
			searchFor : $scope.searchFor,
			page : $scope.pageToGet
		};

		$http({
			method : 'DELETE',
			url : url,
			params : params
		}).success(function(data) {
			$scope.infoMsg = true;
			$scope.resetPagamento();
			$scope.finishAjaxCallOnSuccess(data, "#deletar", false);
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
	$scope.popularListaPagamento();
	$scope.popularListaUsuario();
	$scope.listarFinanceiros();
}

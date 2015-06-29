function loginController($scope, $location, $http) {
    var url = "" + $location.$$absUrl;
    $scope.displayLoginError = (url.indexOf("error") >= 0);
    
    
    
    $scope.recuperarSenha = function(){
    	var urlnova = '/academiaDigital/login/esqueciSenha/' + $scope.emailRecuperar;
    	$scope.messageSucess = false;
    	$scope.messageError = false;
        $http.post(urlnova)
        .success(function (data) {
        	$scope.messageError = false;
        	$scope.messageSucess = true;
    		$("#loadingModal").modal('hide');
        })
        .error(function(data, status, headers, config) {
        	$scope.messageError = true;
        	$scope.handleErrorInDialogs(status);
        });
    }
}


	
	

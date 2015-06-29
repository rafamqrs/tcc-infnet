function LocationController($scope, $location) {
    if($location.$$absUrl.lastIndexOf('/contacts') > 0){
        $scope.activeURL = 'contacts';
    } else if($location.$$absUrl.lastIndexOf('/user') > 0){
        $scope.activeURL = 'user';
    }else if($location.$$absUrl.lastIndexOf('/serie') > 0 || $location.$$absUrl.lastIndexOf('/exercicio') > 0){
    	$scope.activeURL = 'serie';
    }else if($location.$$absUrl.lastIndexOf('/pagamento') > 0){
    	$scope.activeURL = 'pagamento';
    }else{
    	$scope.activeURL ='home';
    }
}
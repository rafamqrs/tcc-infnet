var CalculatorService = angular.module('CalculatorService', [])
.service('Calculator', function () {
    var getData = function(id) {

        return $http({method:"GET", url:"http://localhost:8080/academiaDigital/protected/user/getUser/" + id}).then(function(result){
            return result.data;
        });
    };
    return { getData: getData };

});

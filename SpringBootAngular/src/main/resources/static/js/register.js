var app = angular.module('registerApp',[]);

app.controller("registerCtrl",function ($scope,$http) {
    $scope.registerBtn=function(){
        var dataJson = {"username":$scope.registerUsername,"password":$scope.registerPassword};
        var requestData = JSON.stringify(dataJson);
        $http({
            method:"POST",
            url:"register",
            data:requestData
        }).then(function successCallback(response) {
            console.log(response);
        });
    };
});
var app = angular.module('loginApp',[]);

app.controller("loginCtrl",function ($scope,$http) {
    $scope.loginBtn=function(){
        var dataJson = {"username":$scope.loginUsername,"password":$scope.loginPassword};
        var requestData = JSON.stringify(dataJson);
        $http({
            method:"POST",
            url:"login",
            data:requestData
        }).then(function successCallback(response) {
            console.log(response);
        });
    };
});
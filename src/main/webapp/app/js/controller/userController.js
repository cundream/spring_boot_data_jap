App.controller('list', ['$scope','$rootScope','$location','$http',function($scope,$rootScope,$location,$http){
    console.log('list控制器')
    $scope.arr = [1,2,3,4,5,6];
    $scope.query = function (s) {

        var  url="/user/getAll";
        if(s=="ca"){
        url="/users/getAll";
        }


        var params = {
            "currentPage": 1,
            "pageSize": 2,
            "state":1
        };

        $http.post(url,params).success(
            function (data) {
                if(s=="ca"){
                    $scope.caitmes=data.data;
                }else{
                    $scope.myitmes=data.data;
                }
                console.log( data.data);
            }).error(function (err) {
        });
    }

    console.log($location.search().name)

}]);
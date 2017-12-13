
  var App=angular.module('app',[
    'ui.router'//此处路由用的是ui－router
  ]);
  App
      .directive('pageTitle',['$rootScope',
          function($rootScope,$http){
              return {
                  link : function(scope,element){
                      var func = function(event,toState){
                          var title = 'Demo';
                          if(toState.data && toState.data.title) title += '___'+toState.data.title;
                          element.text(title)
                      }
                      $rootScope.$on('$stateChangeStart',func)
                  }

              }
          }])
      .directive('createDom', ['$rootScope',
          function($rootScope){
          return {
              restrict : 'A',
              link : function(scope,element){
                  var str = '';
                  for(var i = 0 ;i<scope.arr.length;i++){
                      str += '<p>id:'+scope.arr[i]+'</p>'
                  }
                  element.append(str);
              }
          }
      }]);





  App
      .controller('index',['$scope','$rootScope','apiService','sidebarMemu',function($scope,$rootScope,apiService,sidebarMemu){

          sidebarMemu.load();
          apiService.getInfo('/data/userInfo.json',{'key':'ab1ea2'},'get')
      .success(function(data){
                  $scope.userInfo = data
              })
          console.log('index控制器')
          $rootScope.indexArr = ['fsaf','fsaf','kkkk']

          console.log($rootScope)
      }]);
      App.controller('product',['$scope,$location',function($scope,$location){
          console.log('product控制器')
          $scope.getInfo = function(){
              console.log($location)
              $location.path('user/info')
          }
      }]);

 App.controller('about', ['$scope',function($scope){
     console.log('about控制器')
 }]);

      App.controller('dom',['$scope',function($scope){
      }]);



  App
      .config(['$stateProvider','$urlRouterProvider',function($stateProvider,$urlRouterProvider){
          $urlRouterProvider.otherwise('/home/index')
          $stateProvider
              .state('home',{
                  url : '/home',
                  templateUrl : 'app/views/content.html'
              })
              .state('home.index',{
                  url : '/index',
                  templateUrl : 'app/views/index.html',
                  data : {
                      'title':'index',
                      'data' : [1,2,3,4,5]
                  },
                  controller:'index'
              })
              .state('home.list',{
                  url : '/list?id&name',
                  templateUrl : 'app/views/list.html',
                  data : [1,2,3,4,5,6],
                  controller:'list'
              })
              .state('home.product',{
                  url : '/product',
                  templateUrl : 'app/views/product.html',
                  controller:'product'
              })
              .state('home.about',{
                  url : '/about',
                  templateUrl : 'app/views/about.html',
                  controller:'about'
              })
              .state('user',{
                  url : '/user',
                  templateUrl : 'app/views/content2.html'
              })
              .state('user.info',{
                  url : '/info',
                  templateUrl : 'app/views/info.html'
              })
              .state('home.interceptAccount', {
                  url: "/interceptAccount",
                  templateUrl: 'app/views/interceptAccount.html'
              })
              .state('home.dataMapAccount', {
                  url: "/dataMapAccount",
                  templateUrl:'app/views/dataMapAccount.html'
              })

      }])
      .run(function($rootScope,$state){
          $rootScope.$state = $state;
      });


  App.service('apiService',function($http, $rootScope,$location){
      function json2url(json) {
          var arr = [];
          var str = '';
          for (var i in json) {
              str = i + '=' + json[i];
              arr.push(str);
          }
          return arr.join('&');
   };
      function fetch(url,data,method){
          method = method.toLowerCase();
          if (method == 'get') {
              var params = json2url(data);
              return $http.get(url + '?' + params).success(function(res) {
                  // cb(res);
              });
          } else {
              return $http.post(url, data, {withCredentials: true}).success(function(res) {
                  if( res.error ) {
                      // cb(res);
                  }
              });
          }

      }
      this.getInfo = function(url,params,method) {
          return fetch(url,params,method)
      };
  });

  App
      .service(
          'sidebarMemu',
          [
              "$rootScope",
              "$http",
              function($rootScope, $http) {
                  'use strict';
                  var menuJson = 'server/sidebar/sidebar-items.json', menuURL = menuJson
                      + '?v=' + (new Date().getTime()); // jumps
                  return {
                      load : function() {
                          $http.get(menuURL).success(function(items) {
                              $rootScope.menuItems = items;
                          }).error(
                              function(data, status, headers,
                                       config) {
                                  alert('加载菜单失败！');
                              });
                      }
                  };

              } ]);

var cavApp = angular.module("cavApp",["ngRoute","cavControllers","ngCookies"])

cavApp.config(['$routeProvider','$locationProvider', 
function($routeProvider,$locationProvider) {
	$routeProvider.when('/', {
		templateUrl: 'views/votes/main.html',
		controller : 'indexController'
	}),
	$routeProvider.when('/create', {
		templateUrl: 'views/votes/form.html',
		controller : 'createController'
	}),
	$routeProvider.when('/list', {
		templateUrl: 'views/votes/list.html',
		controller : 'listController'
	});
}]);
var cavControllers = angular.module("cavControllers", ['ngCookies','cavFactories']);
cavControllers.controller('indexController', function($http,$rootScope, $cookieStore, $cookies, $getCookiesAngular){
	$rootScope.title="";
	$("#titulo").innerHTML = ""
	var getCookiesAngular = $getCookiesAngular();
	getCookiesAngular.then(function(result){
		$rootScope.username=$cookieStore.get("angularUser");
	});
	
});
cavControllers.controller('createController', function($scope, $http, $rootScope, $location, $cookieStore){
	$rootScope.title="";
	$scope.qs=[];
	$scope.i = 0;
	$scope.survey={type:"survey", usernameCreator:$cookieStore.get("angularUser"), title:"",description:"",startDate:"",endDate:"",questions:[]};
	$scope.addQuestionField = function(){
		$scope.i+=1;
		$scope.qs.push($scope.i);
	};
	$scope.deleteQuestionField = function(){
		$scope.qs.splice($scope.i-1,$scope.i);
		$scope.i-=1;
	};
	if($cookieStore.get("angularUser")!=null){
		$scope.submit = function(survey){
			console.log(survey)
			var dat = null;
			
			$http.post("vote/save.do",survey).success(function(data,status){
				if (status=200){
					$http.get("/ADMCensus/census/create.do?idVotacion="+data.id+"&fecha_inicio="+data.startDate+"&fecha_fin="+data.endDate+"&tituloVotacion="+data.title).success(function(data,status){
						if (status=200){
							$http.get("vote/saveCensus.do?surveyId="+data.idVotacion+"&censusId="+data.id).success(function(data,status){
								if (status=200){
									$location.path("/list");
								}
							});
						}
					});
				}
				$http.get("/Deliberations/customer/createThreadFromVotacion.do?name="+survey.title);
			});
		};
	}
});
cavControllers.controller('listController', function($scope, $http, $route, $rootScope){
	$rootScope.title = "";
	$http.get("vote/mine.do").success(function(data,status){
		alert(data);
		$scope.surveys = data;
	});
	$scope.borrar = function(survey){
		$http.get("/ADMCensus/census/canDelete.do?idVotacion="+survey.id).success(function(data,status){
			if (data[0].result=="yes"){
				$http.get("vote/delete.do?id="+survey.id).success(function(data,status){
					$route.reload();
				});
			}
		});
	}
	
	$scope.goEditCensus=function(survey){
		window.location="/ADMCensus/census/details.do?censusId="+survey.census;
	}
});
var cavFactories = angular.module('cavFactories',['ngCookies']);

cavFactories.factory('$getCookiesAngular',function($http, $cookieStore, $q){
	return function(){
		var res = $q.defer();
		$http.get("vote/getcookies.do").success(function(data,status){
			if(data.user!="" && data.token!=""){
				$cookieStore.put("angularUser",data.user);
				$cookieStore.put("angularToken",data.token);
				res.resolve("OK")
			}else{
				res.reject("Not Auth")
			}
		});
		return res.promise;
	};
});
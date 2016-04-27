var app = angular.module('homeApp',['ngRoute', 'ngMaterial']);
var locId = 1;
app.config(function($routeProvider) {

	$routeProvider
	.when('/',{
		templateUrl:'home.html'
	})
	.when('/volunteer',{
		templateUrl:'volunteer.html'
	})
	.when('/admin',{
		templateUrl:'admin.html'
	})
	.otherwise({
		redirectTo:'/'
	});
	
});

app.controller('loginController',function($scope, $location) {
	$scope.submit = function(){
		var loginId = $scope.email;
		var password = $scope.password;
	    var JSONObject= {"loginId":loginId, "password":password};
		var jsonData = JSON.stringify( JSONObject );
		$.ajax({
	 		url: '../login',
	 		type: 'POST',
	 		dataType: 'json',                  
	 		data: jsonData,
	 		contentType: 'application/json',
	 		success: function(response){
				if(response.role == "Admin")
					window.location.hash='#/admin';
				else if(response.role == "Volunteer")
					window.location.hash='#/volunteer';
				else
					window.location.hash='#/';
	    	},		
        	error: function(err){            	
         	}
		});
	};
});

app.controller('ListLocController',function($scope, $http) {	
	
	$http.get('../locations').success(function(data){
		
		var name;
		for ( var i = 0; i < data.length; i++) { 
			
			if(data[i].id == '1') {				
				$scope.locId = data[i].id;
				name = data[i].name;
				break;				
			}					
		}
		$scope.data = {
		   availableOptions: data,
		   selectedOption: {id: locId, name: name } //This sets the default value for the select element in html 
		};	   
	})

});


app.controller('eventController', function($scope, $http) {
	
	$scope.tabs = ['Today', 'Weekly', 'Monthly'];  

  	$scope.getStuff = function(selection) {
	
		locId = selection.id;
		tab = 'Today';
		$http.get('../homeEvents?tab='+tab+'&locId='+selection.id).success(function(data){
			$scope.tab = tab;
			$scope.events = JSON.parse(JSON.stringify(data));	

		})
		
	}
  
	$scope.selectTab = function (tab) {

	$http.get('../homeEvents?tab='+tab+'&locId='+locId).success(function(data){	

		$scope.events = JSON.parse(JSON.stringify(data));
	
		})
	}

});



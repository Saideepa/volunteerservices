var app = angular.module('loginApp',['ui.router', 'ngMaterial']);
var locId = 1;

app.config(function($stateProvider,$urlRouterProvider) {
	$urlRouterProvider.otherwise('/');

	$stateProvider
		.state('home', {
			url:'/',
			views: {
				'header': {
					templateUrl: 'homeHeader.html'
				},
				'content': {
					templateUrl: 'loginHome.html'
				},
				'footer': {
					templateUrl: 'footer.html'
				}
			}
		})
	.state('home.admin',{
		url:'admin',
		views: {
			'header@': {
				templateUrl: 'adminHeader.html',					
			},
			'content@': {
				templateUrl: 'admin.html',	
				controller: 'adminController'				
			}
		}
	})

	.state('home.volunteer',{
		url:'volunteer',
		views: {
			'header@': {
				templateUrl: 'dashboardHeader.html',
				controller: 'volunteerController'	
			},
			'content@': {
				templateUrl: 'volunteer.html',
				controller: 'volunteerController'
			}
		}		
	})
	.state('home.admin.events',{		
		views: {
			'detail@home.admin': {
				templateUrl: 'createEvent.html',		
				controller: 'CreateEventController'	
			}
		}
	})
	.state('home.admin.tasks',{		
		views: {
			'detail@home.admin': {
				templateUrl: 'createTask.html',		

			}
		}
	})
	.state('home.admin.location',{		
		views: {
			'detail@home.admin': {
				templateUrl: 'createLocation.html',		

			}
		}		
	});

	
});

app.controller('loginController',function($scope, $location, $state) {
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
					$scope.redirectToAdminpage();
				else if(response.role == "Volunteer")
					$scope.redirectToVolunteerpage();
				else
					window.location.hash='#/';
	    	},	error: function(err){            	
         	}
		});
		$scope.redirectToAdminpage = function(){
			$state.go('home.admin');
		}
		$scope.redirectToVolunteerpage = function(){
			$state.go('home.volunteer');
		}
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

			$scope.selectTab = tabs[tab];
			$scope.events = JSON.parse(JSON.stringify(data));	

		})
		
	},
  
	$scope.selectTab = function (tab) {

	$http.get('../homeEvents?tab='+tab+'&locId='+locId).success(function(data){	

		$scope.events = JSON.parse(JSON.stringify(data));
	
		});
	}

});

app.controller('adminController', function($scope, $http, $state) {
	
	$scope.createEvent = function(){
		$state.go('home.admin.events');
	}
	$scope.createTasks = function(){
		$state.go('home.admin.tasks');
	}
	$scope.createLocation = function(){
		$state.go('home.admin.location');
	}	
});

app.controller('volunteerController', function($scope, $http) {
});

app.controller('CreateEventController', function($scope, $http) {
	
	var locId = 1;
	
  	$scope.getlocation = function(selection) {
  		
		locId = selection.id;
  	}
  	
  	$scope.createEvent = function(event){
  		$http.post('../createEvent',JSON.stringify(event)).then(function(responseData){
  			if(responseData.success){ alert(" Successfully saved");}
  		},function(error){
  			alert(" Server error while saving");
  		});
  	}
	
});



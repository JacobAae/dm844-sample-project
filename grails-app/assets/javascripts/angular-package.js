//= require angular/angular-1.0.8.js
//= require angular/restangular-1.1.3.js
//= require angular/lodash-2.2.0.js
//= require_self

/*
angular.module('Bsg', ['restangular']).config(
	function(RestangularProvider) {
		RestangularProvider.setBaseUrl('bsg/api');
	}
);

function PersonCtrl($scope, Restangular) {
	var peopleApi = Restangular.all('list');
	$scope.allPersons = peopleApi.getList();
}
*/


angular.module('Bsg', ['restangular']).config(
	function(RestangularProvider) {
		RestangularProvider.setBaseUrl('/bsg/');
	}
);

function InfoCtrl($scope, Restangular) {

	var infoApi = Restangular.all("info");

	$scope.allMessages = [];

	$scope.refreshPosts = function() {
		infoApi.getList().then(function(newPostList) {
			console.debug(newPostList);
			$scope.allMessages = newPostList;
		}, function(errorResponse) {
			alert("Error on refreshing messages: " + errorResponse.status);
		});
	}

	$scope.refreshPosts();

}


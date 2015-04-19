//= require angular/angular.js
//= require angular/restangular.js
//= require angular/lodash.js
//= require_self

angular.module('Bsg', ['restangular']).config(
	function(RestangularProvider) {
		RestangularProvider.setBaseUrl('bsg/api');
	}
);

function PersonCtrl($scope, Restangular) {
	var peopleApi = Restangular.all('list');
	$scope.allPersons = peopleApi.getList();
}


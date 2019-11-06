var doc = angular.module('documentation', ['ngRoute']);
  doc.controller('DocCtrl', function($scope) {
	  
  });
  doc.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/configuration', {
        templateUrl: 'partials/configuration.html',
        controller: 'DocCtrl'
      }).
      when('/workflow', {
        templateUrl: 'partials/workflow.html',
        controller: 'DocCtrl'
      }).
      when('/soap', {
        templateUrl: 'partials/soap.html',
        controller: 'DocCtrl'
      }).
      when('/rest', {
        templateUrl: 'partials/rest.html',
        controller: 'DocCtrl'
      }).
      when('/contract', {
        templateUrl: 'partials/contract.html',
        controller: 'DocCtrl'
      }).
      when('/soaptrigger', {
        templateUrl: 'partials/soaptrigger.html',
        controller: 'DocCtrl'
      }).
      when('/diagrams', {
        templateUrl: 'partials/diagrams.html',
        controller: 'DocCtrl'
      }).
      when('/errorhandler', {
        templateUrl: 'partials/errorhandler.html',
        controller: 'DocCtrl'
      }).
      otherwise({
        redirectTo: '/configuration'
      });
  }]);

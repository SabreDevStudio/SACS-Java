var app = angular.module('showcase', []);
app.controller('showcase_controller', function($scope, $http) {
	$scope.responses = [];
	$scope.restResponses = [];
	$scope.sessions = [];
	$scope.availableSessions = [];
	$scope.requestCount = 1;
	$scope.restRequestCount = 1;
	//$scope.triggerUri = 'http://192.168.31.101:8080';
	$scope.triggerUri = 'http://localhost:8080';
	
	$scope.runOrchestratedFlow = function() {
		for (i=0; i < $scope.requestCount; i++) {
			$http.get($scope.triggerUri + '/orchestratedFlow').success(function(data) {
				$scope.responses.push(data);
			});
		}
	}
	
	$scope.runRestFlow = function() {
		for (i=0; i < $scope.restRequestCount; i++) {
			$http.get($scope.triggerUri + '/restFlow').success(function(data) {
				$scope.restResponses.push(data);
			});
		}
	}
	
	$scope.refreshSessions = function() {
		$http.get($scope.triggerUri + '/getSessions').success(function(data) {
			$scope.sessions = data;
		});
	}
	
	$scope.getAvailableSessions = function() {
		$http.get($scope.triggerUri + '/getAvailableSessions').success(function(data) {
			$scope.availableSessions = data;
		});
	}
	
	$scope.pingSession = function(session) {
		$http.put($scope.triggerUri + '/pingSession', session)
			.success(function(data) {
			//do nothing
			alert('Session pinged');
	});
	}
	
	$scope.clearResults = function() {
		$scope.responses = [];
	}
});
app.filter('xml', function() {
	return function(xml) {
		var formatted = '';
		var reg = /(>)(<)(\/*)/g;
		xml = xml.replace(reg, '$1\r\n$2$3');
		var pad = 0;
		jQuery.each(xml.split('\r\n'), function(index, node) {
			var indent = 0;
			if (node.match( /.+<\/\w[^>]*>$/ )) {
				indent = 0;
			} else if (node.match( /^<\/\w/ )) {
				if (pad != 0) {
					pad -= 1;
				}
			} else if (node.match( /^<\w[^>]*[^\/]>.*$/ )) {
				indent = 1;
			} else {
				indent = 0;
			}

			var padding = '';
			for (var i = 0; i < pad; i++) {
				padding += '  ';
			}

			formatted += padding + node + '\r\n';
			pad += indent;
		});

		return formatted;
	}
});

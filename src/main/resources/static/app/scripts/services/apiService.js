'use strict';


angular.module("pruebaApp").factory('apiService', ['$resource', function($resource) {
    return $resource('http://localhost:8080/information', {}, {
        resolve: {
            method: 'POST',
            transformRequest: function(data) {
            	var arr = data.data.toString();
                arr = arr.substr(0,arr.length-1).split('\n');
                data.data = arr;
                return angular.toJson(data);
            },
            isArray: true
        }
    });
}]);

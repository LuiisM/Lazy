'use strict';

/**
 * @ngdoc function
 * @name pruebaApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the pruebaApp
 */
angular.module('pruebaApp')
    .controller('MainCtrl', function($scope, apiService, $window) {
        var vm = this;
        vm.form = {};
        $scope.file_name = "lazy_loading_example_output.txt";
        vm.send = function() {
            apiService.resolve({
                name: vm.form.name,
                personId: vm.form.document,
                data: $scope.content
            }, function(success) {
                var data = "";
                for (var i = 0; i < success.length; i++) {
                    data += "Case #" + i + " " + success[i] + "\n";
                }
                $scope.content = data;
                console.log(success);
                writeFile();
                $scope.done = true;
            }, function(err) {
                $scope.content = "Archivo no válido";
                console.log(data);
            });
        };
        $scope.readFile = function(file) {
        	$scope.done = false;
            $scope.content = file;
        };

        function writeFile() {
            var blob = new Blob([$scope.content], {
                type: "text/plain"
            });
            var url = $window.URL || $window.webkitURL;
            $scope.file = url.createObjectURL(blob);
        }
    });

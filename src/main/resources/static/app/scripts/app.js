'use strict';

/**
 * @ngdoc overview
 * @name pruebaApp
 * @description
 * # pruebaApp
 *
 * Main module of the application.
 */
angular
    .module('pruebaApp', [
        'ngResource'
    ]).config(['$compileProvider', function($compileProvider) {
        $compileProvider.aHrefSanitizationWhitelist(/^\s*(|blob|):/);
    }]);;

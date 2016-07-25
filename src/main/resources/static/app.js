angular.module('app', ['ui.router', 'ui.bootstrap', 'app.home'])
        .config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

            $stateProvider
                    .state('app', {
                        url: '',
                        abstract: true
                    });
        })
        .controller('AppController', function () {
        });
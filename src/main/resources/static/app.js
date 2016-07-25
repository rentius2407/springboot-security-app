angular.module('app', ['ui.router', 'ui.bootstrap', 'app.home'])
        .config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

            $stateProvider
                    .state('app', {
                        url: '',
                        abstract: true
                    });

            $urlRouterProvider.otherwise(function ($injector, $location) {
                var $state = $injector.get('$state');
                $state.go('app.home');

            });
        })
        .controller('AppController', function () {
        });
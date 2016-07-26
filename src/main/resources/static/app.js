angular.module('app',
        [
            'ui.router',
            'ui.bootstrap',
            'app.home',
            'ngCookies'
        ])
        .factory('TokenService', function ($cookies) {

            var TOKEN_CONST = 'AuthToken';

            return {
                put: function (token) {
                    $cookies.put(TOKEN_CONST, token);
                },
                get: function () {
                    return $cookies.get(TOKEN_CONST);
                }
            };
        })
        .config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

            $httpProvider.interceptors.push(interceptor);

            $stateProvider
                    .state('app', {
                        url: '',
                        abstract: true
                    });

            $urlRouterProvider.otherwise(function ($injector) {
                var $state = $injector.get('$state');
                $state.go('app.home');

            });
        })
        .controller('AppController', function () {
        });

function interceptor($q, $injector, TokenService) {

    return {
        request: function (config) {
            config.headers['authorization'] = 'Bearer ' + TokenService.get();
            return config;
        },
        response: function (result) {
            return result;
        },
        responseError: function (rejection) {
            if (rejection.status === 401) {
                $injector.get('$state').transitionTo('app.login', null, {reload: true});
            }
            return $q.reject(rejection);
        }
    };
}
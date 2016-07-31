angular.module('app',
        [
            'ui.router',
            'ui.bootstrap',
            'app.user.login',
            'app.home',
            'app.user.menu.service',
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
                },
                inValid: function () {
                    var token = $cookies.get(TOKEN_CONST);
                    return (!token || token.trim().length === 0);
                },
                clear: function () {
                    $cookies.remove(TOKEN_CONST);
                }
            };
        })
        .config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

            $httpProvider.interceptors.push(interceptor);

            $stateProvider
                    .state('app', {
                        url: '',
                        data: {
                            secure: true
                        },
                        abstract: true
                    });

            $urlRouterProvider.otherwise(function ($injector) {
                var $state = $injector.get('$state');
                $state.go('app.home');

            });
        })
        .run(function ($rootScope, $state, TokenService) {
            $rootScope.$on("$stateChangeStart", function (event, next, current) {
                console.log('State changed to = ' + next.name + ' secure = ' + next.data.secure);
                if (next.data.secure && TokenService.inValid()) {
                    console.log('Redirect');
                    $state.go('app.login');
                    event.preventDefault();
                }
            });
        })
        .controller('AppController', function () {
        });

function interceptor($q, $injector, TokenService) {

    return {
        request: function (config) {
            console.log('Interceptor Request');
            config.headers['authorization'] = 'Bearer ' + TokenService.get();
            return config;
        },
        response: function (result) {
            console.log('Interceptor Response');
            return result;
        },
        responseError: function (rejection) {
            console.log('Interceptor Response Error');
            if (rejection.status === 401) {
                $injector.get('$state').transitionTo('app.login', null, {reload: true});
            }
            return $q.reject(rejection);
        }
    };
}
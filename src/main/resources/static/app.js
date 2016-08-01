angular.module('app',
        [
            'ui.router',
            'ui.bootstrap',
            'app.nutrition',
            'app.user',
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
                if (next.data.secure && TokenService.inValid()) {
                    $state.go('app.login');
                    event.preventDefault();
                }
            });
        })
        .controller('AppController', function ($scope, UserDetailService) {
            var appCtrl = this;
            appCtrl.showMenu = UserDetailService.validUser();
            $scope.$on('showMenuEvent', function (event, args) {
                appCtrl.showMenu = args.show;
            });
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
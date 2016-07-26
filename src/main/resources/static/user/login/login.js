angular.module('app.user.login', [])
        .config(function ($stateProvider) {

            $stateProvider
                    .state('app.login', {
                        url: '/login',
                        data: {
                            secure: false
                        },
                        views: {
                            'main@': {
                                controller: 'LoginController as loginCtrl',
                                templateUrl: 'user/login/login.html'
                            }
                        }
                    });
        })
        .controller('LoginController', function () {

        });
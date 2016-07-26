angular.module('app.user.login', ['app.user.login.service'])
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
        .controller('LoginController', function (LoginService, TokenService, $state) {

            var loginCtrl = this;
            loginCtrl.login = function () {
                var credentials = {username: loginCtrl.username, password: loginCtrl.password};
                LoginService.login(credentials).then(function (result) {
                    var tokenNumber = result.data.number;
                    TokenService.put(tokenNumber);
                    $state.go('app.home');
                }, function (error) {
                    //go back to login and display error message
                    console.log(error);
                });
            };

        });
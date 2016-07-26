angular.module('app.user.login.service', [])
        .factory('LoginService', function ($http) {
            return {
                login: function (credentials) {
                    return $http.post('/authenticate', credentials);
                }
            };
        });
angular.module('app.user.menu.service', [])
        .factory('MenuService', function ($http) {
            return {
                all: function (userId) {
                    return $http.get('/menu/' + userId);
                }
            };
        });


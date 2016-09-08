angular.module('app.group', [])
        .factory('GroupService', function ($http) {
            return {
                findAll: function () {
                    return $http.get('/api/group');
                }
            };
        });


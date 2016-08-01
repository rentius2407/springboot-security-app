angular.module('app.user', [])
        .controller('UserController', function () {
        })
        .factory('UserDetailService', function ($window) {

            var KEY_CONST = 'userdetail';

            return {
                clear: function () {
                    $window.localStorage.removeItem(KEY_CONST);
                },
                add: function (userDetail) {
                    $window.localStorage.setItem(KEY_CONST, angular.toJson(userDetail));
                },
                get: function () {
                    var data = $window.localStorage.getItem(KEY_CONST);
                    return angular.fromJson(data);
                }, 
                validUser: function () {
                    var data = $window.localStorage.getItem(KEY_CONST);
                    return data && data.length > 0;
                }
            };
        });


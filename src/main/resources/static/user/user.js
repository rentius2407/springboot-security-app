angular.module('app.user', [])
        .config(function ($stateProvider) {

            $stateProvider
                    .state('app.user', {
                        url: '/user',
                        data: {
                            secure: true
                        },
                        views: {
                            'main@': {
                                controller: 'UserController as userCtrl',
                                templateUrl: 'user/user.html'
                            }
                        }
                    })
                    .state('app.user.add', {
                        url: '/user/add',
                        data: {
                            secure: true
                        },
                        views: {
                            'main@': {
                                controller: 'UserAddController as userAddCtrl',
                                templateUrl: 'user/add/userAdd.html'
                            }
                        }
                    });
        })
        .controller('UserController', function ($state) {

            var userCtrl = this;
            var myData = [
                {
                    "firstName": "Cox",
                    "lastName": "Carney"
                },
                {
                    "firstName": "Lorraine",
                    "lastName": "Wise"
                },
                {
                    "firstName": "Nancy",
                    "lastName": "Waters"
                }
            ];

            userCtrl.gridOptions = {
                enableSorting: true,
                enableColumnMenus: false,
                columnDefs: [
                    {field: 'firstName', displayName: 'First Name', width: '50%'},
                    {field: 'lastName', displayName: 'Last Name', width: '50%'}
                ],
                data: myData
            };

            userCtrl.add = function () {
                $state.go('app.user.add');
            };
        })
        .controller('UserAddController', function ($state) {
            var userAddCtrl = this;
            userAddCtrl.add = function (newUser) {
                console.log(newUser);
            };
        })
        .factory('UserDetailService', function ($window) {

            var KEY_CONST = 'userdetail';

            return {
                clear: function () {
                    $window.localStorage.removeItem(KEY_CONST);
                },
                add: function (userDetail) {
                    $window.localStorage.setItem(KEY_CONST, userDetail);
                },
                get: function () {
                    var data = $window.localStorage.getItem(KEY_CONST);
                    return angular.fromJson(data);
                },
                validUser: function () {
                    var data = $window.localStorage.getItem(KEY_CONST);
                    return data && data.length > 0;
                },
                hasRole: function (role) {
                    var data = $window.localStorage.getItem(KEY_CONST);
                    var dataObject = angular.fromJson(data);
                    return dataObject && dataObject.role && dataObject.role === role;
                }
            };
        });


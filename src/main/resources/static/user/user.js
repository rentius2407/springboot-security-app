angular.module('app.user', [])
        .config(function ($stateProvider) {

            $stateProvider
                    .state('app.user', {
                        url: '/user',
                        data: {
                            secure: true
                        },
                        resolve: {
                            users: ['UserService', function (UserService) {
                                    return UserService.findAll();
                                }]
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
                        resolve: {
                            groups: ['GroupService', function (GroupService) {
                                    return GroupService.findAll();
                                }]
                        },
                        views: {
                            'main@': {
                                controller: 'UserAddController as userAddCtrl',
                                templateUrl: 'user/add/userAdd.html'
                            }
                        }
                    });
        })
        .controller('UserController', function ($state, $scope, users) {
            
            var userCtrl = this;
            userCtrl.gridOptions = {
                enableSorting: true,
                enableColumnMenus: false,
                enableHorizontalScrollbar: 0,
                enableVerticalScrollbar: 0,
                onRegisterApi: function (gridApi) {
                    $scope.gridApi = gridApi;
                    var newHeight = (users.data.length + 1) * 30;
                    angular.element(document.getElementsByClassName('grid')[0]).css('height', newHeight + 'px');
                },
                columnDefs: [
                    {field: 'firstName', displayName: 'First Name', width: '50%'},
                    {field: 'lastName', displayName: 'Last Name', width: '50%'}
                ],
                data: users.data
            };

            userCtrl.add = function () {
                $state.go('app.user.add');
            };
        })
        .controller('UserAddController', function ($state, groups, UserService) {
            var userAddCtrl = this;
            userAddCtrl.groups = groups.data;

            userAddCtrl.add = function (newUser) {
                UserService.create(newUser).then(function (result) {
                    $state.transitionTo('app.user', null, {'reload':true});
                });

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
        })
        .factory('UserService', function ($http) {
            return {
                create: function (newUser) {
                    return $http.post('/api/user/', newUser);
                },
                findAll: function () {
                    return $http.get('/api/user');
                }
            };
        });


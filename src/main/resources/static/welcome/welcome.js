angular.module('app.home', [])
        .config(function ($stateProvider) {

            $stateProvider
                    .state('app.home', {
                        url: '/home',
//                        data: {
//                            requireLogin: true,
//                            moduleName: 'home',
//                            moduleDisplayHeader: 'Home'
//                        },
                        views: {
                            'main@': {
                                controller: 'WelcomeController as welcomeCtrl',
                                templateUrl: 'welcome/welcome.html'
                            }
                        }
                    });
        })
        .controller('WelcomeController', function (MenuService, CategoryService) {

            var welcomeCtrl = this;
            welcomeCtrl.message = 'Welcome Rentius';

            CategoryService.rootCategory().then(function (result) {
                welcomeCtrl.categories = result.data;
            }, function (error) {
                console.log(error);
            });

        });

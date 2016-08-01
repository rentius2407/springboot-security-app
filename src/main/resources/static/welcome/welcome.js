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
        .controller('WelcomeController', function (MenuService) {

            MenuService.all('123').then(function (result) {
            }, function (error) {
                //go back to login and display error message
                console.log(error);
            });

            var welcomeCtrl = this;
            welcomeCtrl.message = 'Welcome Rentius';
            welcomeCtrl.menus = [
                {id: 'nutritionImage', url: '../icons/nutrition.png'}, {id: 'exerciseImage', url: '../icons/exercise.png'}
            ];

        });

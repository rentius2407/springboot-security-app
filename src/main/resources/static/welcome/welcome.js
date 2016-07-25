angular.module('app.home', [])
        .config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

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
        .controller('WelcomeController', function () {

            var welcomeCtrl = this;
            welcomeCtrl.message = 'Welcome Rentius';
            welcomeCtrl.menus = [
                {id: 'nutritionImage', url: '../icons/nutrition.png'}, {id: 'exerciseImage', url: '../icons/exercise.png'}
            ];

        });

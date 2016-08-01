angular.module('app.nutrition', [])
        .config(function ($stateProvider) {

            $stateProvider
                    .state('app.nutrition', {
                        url: '/nutrition',
//                        data: {
//                            requireLogin: true,
//                            moduleName: 'home',
//                            moduleDisplayHeader: 'Home'
//                        },
                        views: {
                            'main@': {
                                controller: 'NutritionController as nutritionCtrl',
                                templateUrl: 'nutrition/nutrition.html'
                            }
                        }
                    });
        })
        .controller('NutritionController', function () {
            
            var nutritionCtrl = this;
            nutritionCtrl.detailsLabel = '';
            
        })
        .factory('NutritionService', function ($window) {
        });


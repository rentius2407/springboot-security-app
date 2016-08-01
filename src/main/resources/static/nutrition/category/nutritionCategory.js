angular.module('app.nutrition.category', [])
        .config(function ($stateProvider) {

            $stateProvider
                    .state('app.nutrition.category', {
                        url: '/nutrition/category',
//                        data: {
//                            requireLogin: true,
//                            moduleName: 'home',
//                            moduleDisplayHeader: 'Home'
//                        },
                        views: {
                            'main@': {
                                controller: 'NutritionCategoryController as nutritionCatCtrl',
                                templateUrl: 'nutrition/category/nutritionCategory.html'
                            }
                        }
                    });
        })
        .controller('NutritionCategoryController', function (UserDetailService) {
            
            var nutritionCatCtrl = this;
        })
        .factory('NutritionCategoryService', function ($window) {
        });


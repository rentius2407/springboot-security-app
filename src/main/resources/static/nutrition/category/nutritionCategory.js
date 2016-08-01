angular.module('app.nutrition.category', [])
        .config(function ($stateProvider) {

            $stateProvider
                    .state('app.nutrition.category', {
                        url: '/category/:catName',
                        resolve: {
                            catName: ['$stateParams', function ($stateParams) {
                                    return $stateParams.catName;
                                }]
                        },
                        views: {
                            'main@': {
                                controller: 'NutritionCategoryController as nutritionCatCtrl',
                                templateUrl: 'nutrition/category/nutritionCategory.html'
                            }
                        }
                    });
        })
        .controller('NutritionCategoryController', function (UserDetailService, catName) {
            var nutritionCatCtrl = this;
            nutritionCatCtrl.header = catName;
            nutritionCatCtrl.addOption = false;

            nutritionCatCtrl.options = [];

            nutritionCatCtrl.addCategoryOption = function () {
                if (nutritionCatCtrl.addOption) { //adding new at this moment

                    var newOption = {
                        name: nutritionCatCtrl.optionName,
                        content: nutritionCatCtrl.optionContent
                    };

                    nutritionCatCtrl.options.push(newOption);
                    nutritionCatCtrl.addOption = false;
                } else {
                    nutritionCatCtrl.optionName = '';
                    nutritionCatCtrl.optionContent = '';
                    nutritionCatCtrl.addOption = true;
                }
            };
        })
        .factory('NutritionCategoryService', function ($window) {
        });


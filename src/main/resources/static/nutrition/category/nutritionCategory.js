angular.module('app.nutrition.category', [])
        .config(function ($stateProvider) {

            $stateProvider
                    .state('app.nutrition.category', {
                        url: '/category/:categoryId',
                        resolve: {
                            categoryId: ['$stateParams', function ($stateParams) {
                                    return $stateParams.categoryId;
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
        .controller('NutritionCategoryController', function (CategoryService, categoryId) {
            var nutritionCatCtrl = this;
    
            nutritionCatCtrl.options = [];

            CategoryService.categoryByIdWithOptions(categoryId).then(function (result) {
                nutritionCatCtrl.header = result.data.category.name;
                nutritionCatCtrl.options = result.data.options;

            });
            nutritionCatCtrl.addOption = false;

            nutritionCatCtrl.addCategoryOption = function () {
                if (nutritionCatCtrl.addOption) { //adding new at this moment

                    var newOption = {
                        name: nutritionCatCtrl.optionName,
                        content: nutritionCatCtrl.optionContent
                    };

                    CategoryService.createOption(categoryId, newOption).then(function (result) {
                        var data = result.data;
                        nutritionCatCtrl.options.push(data);
                    }, function (error) {
                        console.log('Error occured');
                    }).finally(function () {
                        nutritionCatCtrl.addOption = false;
                    });


                } else {
                    nutritionCatCtrl.optionName = '';
                    nutritionCatCtrl.optionContent = '';
                    nutritionCatCtrl.addOption = true;
                }
            };
        })
        .factory('NutritionCategoryService', function ($window) {
        });


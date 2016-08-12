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
                    })
                    .state('app.nutrition.category.option', {
                        url: '/option/:optionId',
                        resolve: {
                            categoryId: ['$stateParams', function ($stateParams) {
                                    return $stateParams.categoryId;
                                }],
                            optionId: ['$stateParams', function ($stateParams) {
                                    return $stateParams.optionId;
                                }]
                        },
                        views: {
                            'main@': {
                                controller: 'NutritionEditOptionController as nutritionEditOptionCtrl',
                                templateUrl: 'nutrition/category/option/nutritionEditOption.html'
                            }
                        }
                    });
        })
        .controller('NutritionCategoryController', function (CategoryService, categoryId) {
            var nutritionCatCtrl = this;

            nutritionCatCtrl.options = [];

            CategoryService.categoryByIdWithOptions(categoryId).then(function (result) {
                nutritionCatCtrl.category = result.data.category;
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
                        nutritionCatCtrl.options = [];
                        nutritionCatCtrl.options = data;
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
        .controller('NutritionEditOptionController', function (CategoryService, categoryId, optionId) {
            var nutritionEditOptionCtrl = this;
            nutritionEditOptionCtrl.header = 'Test';

            CategoryService.findOptionByCategoryIdAndId(categoryId, optionId).then(function (result) {
                console.log(result);
                nutritionEditOptionCtrl.option = result.data;
            });
            nutritionEditOptionCtrl.updateOption = function () {
                
                console.log('update option was called!');
                console.log(nutritionEditOptionCtrl.option);
//                if (nutritionEditOptionCtrl.addOption) { //adding new at this moment
//
//                    var newOption = {
//                        name: nutritionEditOptionCtrl.optionName,
//                        content: nutritionEditOptionCtrl.optionContent
//                    };
//
//                    CategoryService.createOption(categoryId, newOption).then(function (result) {
//                        var data = result.data;
//                        nutritionEditOptionCtrl.options.push(data);
//                    }, function (error) {
//                        console.log('Error occured');
//                    }).finally(function () {
//                        nutritionEditOptionCtrl.addOption = false;
//                    });
//
//
//                } else {
//                    nutritionEditOptionCtrl.optionName = '';
//                    nutritionEditOptionCtrl.optionContent = '';
//                    nutritionEditOptionCtrl.addOption = true;
//                }
            };
        })
        .factory('NutritionCategoryService', function ($window) {
        });


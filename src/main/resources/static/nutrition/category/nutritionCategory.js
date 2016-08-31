angular.module('app.nutrition.category', [])
        .config(function ($stateProvider) {

            $stateProvider
                    .state('app.nutrition.category', {
                        url: '/category/:categoryId',
                        resolve: {
                            categoryId: ['$stateParams', function ($stateParams) {
                                    return $stateParams.categoryId;
                                }],
                            nutritionId: ['$stateParams', function ($stateParams) {
                                    return $stateParams.id;
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
        .controller('NutritionCategoryController', function (CategoryService, categoryId, $state, UserDetailService, nutritionId) {
            var nutritionCatCtrl = this;

            nutritionCatCtrl.options = [];

            nutritionCatCtrl.cancel = function () {
                nutritionCatCtrl.addOption = false;
            };

            nutritionCatCtrl.addOption = false;
            nutritionCatCtrl.showAddOption = UserDetailService.hasRole('ADMIN');
            nutritionCatCtrl.showEditOption = UserDetailService.hasRole('ADMIN');

            nutritionCatCtrl.back = function () {
                $state.go('app.nutrition', {id: nutritionId});
            };

            if (UserDetailService.hasRole('USER')) {
                var userDetail = UserDetailService.get();
                CategoryService.findOptionByCategoryAndGroup(categoryId, userDetail.groupId).then(function (result) {
                    if (result.data.length > 0) {
                        nutritionCatCtrl.options = result.data;
                        nutritionCatCtrl.category = result.data[0].category;
                    }
                });
            } else {
                CategoryService.categoryByIdWithOptions(categoryId).then(function (result) {
                    nutritionCatCtrl.category = result.data.category;
                    nutritionCatCtrl.options = result.data.options;
                });
            }

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
        .controller('NutritionEditOptionController', function (CategoryService, categoryId, optionId, $state) {
            var nutritionEditOptionCtrl = this;

            nutritionEditOptionCtrl.cancelOption = function () {
                $state.go('app.nutrition.category', {categoryId: categoryId});
            };

            CategoryService.findOptionByCategoryIdAndId(categoryId, optionId).then(function (result) {
                nutritionEditOptionCtrl.option = result.data;
            });
            nutritionEditOptionCtrl.updateOption = function () {

                CategoryService.updateOption(categoryId, nutritionEditOptionCtrl.option).then(function (result) {
                    $state.go('app.nutrition.category', {categoryId: categoryId});
                }, function (error) {
                    console.log('Error occured');
                }).finally(function () {
                });
            };
        })
        .factory('NutritionCategoryService', function ($window) {
        });


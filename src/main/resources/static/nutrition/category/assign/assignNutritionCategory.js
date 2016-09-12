angular.module('app.nutrition.category.assign', [])
        .config(function ($stateProvider) {

            $stateProvider
                    .state('app.nutrition.category.assign', {
                        url: '/assign',
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
                                controller: 'AssignCategoryController as assignCatCtrl',
                                templateUrl: 'nutrition/category/assign/assignNutritionCategory.html'
                            }
                        }
                    });
        })
        .controller('AssignCategoryController', function (CategoryService, categoryId, $state, nutritionId) {
            var assignCatCtrl = this;
            console.log(categoryId);

            assignCatCtrl.back = function () {
                $state.go('app.nutrition', {id: nutritionId});
            };
        });


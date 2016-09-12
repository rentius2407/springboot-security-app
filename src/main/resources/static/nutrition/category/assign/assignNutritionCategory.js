angular.module('app.nutrition.category.assign', [])
        .config(function ($stateProvider) {

            $stateProvider
                    .state('app.nutrition.category.assign', {
                        url: '/category/:categoryId',
                        resolve: {
                            categoryId: ['$stateParams', function ($stateParams) {
                                    return $stateParams.categoryId;
                                }]
//                            nutritionId: ['$stateParams', function ($stateParams) {
//                                    return $stateParams.id;
//                                }]
                        },
                        views: {
                            'main@': {
                                controller: 'AssignCategoryController as assignCatCtrl',
                                templateUrl: 'nutrition/category/assign/assignNutritionCategory.html'
                            }
                        }
                    });
        })
        .controller('AssignCategoryController', function (CategoryService, categoryId) {
            var assignCatCtrl = this;
            console.log(categoryId);
        });


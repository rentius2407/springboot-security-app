angular.module('app.nutrition', [])
        .config(function ($stateProvider) {

            $stateProvider
                    .state('app.nutrition', {
                        url: '/nutrition/:id',
                        resolve: {
                            nutritionId: ['$stateParams', function ($stateParams) {
                                    return $stateParams.id;
                                }]
                        },
                        views: {
                            'main@': {
                                controller: 'NutritionController as nutritionCtrl',
                                templateUrl: 'nutrition/nutrition.html'
                            }
                        }
                    });
        })
        .controller('NutritionController', function (UserDetailService, nutritionId, CategoryService, $state) {

            var nutritionCtrl = this;
            nutritionCtrl.back = function () {
                $state.go('app.home');
            };

            CategoryService.categoryByParent(nutritionId).then(function (result) {
                nutritionCtrl.categories = result.data;
            });

            nutritionCtrl.hideAssign = true;
            nutritionCtrl.detailsLabel = 'View Details';
            if (UserDetailService.hasRole('ADMIN')) {
                nutritionCtrl.detailsLabel = 'Add Details';
                nutritionCtrl.hideAssign = false;
            }

        })
        .factory('NutritionService', function ($window) {
        });


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

            var fromMockGroups = [
                {id: 1, name: 'GroupA'}, {id: 2, name: 'GroupB'}
            ]

            assignCatCtrl.fromGroups = fromMockGroups;
            assignCatCtrl.toGroups = [];

            assignCatCtrl.assign = function (selectedFromGroups) {
                angular.forEach(selectedFromGroups, function (group) {
                    var index = assignCatCtrl.fromGroups.indexOf(group);
                    assignCatCtrl.fromGroups.splice(index, 1);
                    assignCatCtrl.toGroups.push(group);
                });

            };


            assignCatCtrl.back = function () {
                $state.go('app.nutrition', {id: nutritionId});
            };
        });


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
        .controller('AssignCategoryController', function (CategoryService, GroupService, categoryId, $state, nutritionId) {
            var assignCatCtrl = this;
            GroupService.findAll().then(function (result) {
                assignCatCtrl.fromGroups = result.data;
                CategoryService.assignedGroups(categoryId).then(function (result) {
                    assignCatCtrl.toGroups = result.data;
                    angular.forEach(assignCatCtrl.toGroups, function (toGroup) {
                        var index = assignCatCtrl.fromGroups.indexOf(toGroup);
                        assignCatCtrl.fromGroups.splice(index, 1);
                    });
                });
            });

            assignCatCtrl.assign = function (selectedFromGroups) {
                angular.forEach(selectedFromGroups, function (group) {
                    var index = assignCatCtrl.fromGroups.indexOf(group);
                    assignCatCtrl.fromGroups.splice(index, 1);
                    assignCatCtrl.toGroups.push(group);
                });

            };
            assignCatCtrl.remove = function (selectedToGroups) {
                angular.forEach(selectedToGroups, function (group) {
                    var index = assignCatCtrl.toGroups.indexOf(group);
                    assignCatCtrl.toGroups.splice(index, 1);
                    assignCatCtrl.fromGroups.push(group);
                });
            };

            assignCatCtrl.back = function () {
                $state.go('app.nutrition', {id: nutritionId});
            };
        });


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

            nutritionCatCtrl.options = [
                {id: 1, name: 'Option1',
                    content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tincidunt est vitae ultrices accumsan. Aliquam ornare lacus adipiscing, posuere lectus et, fringilla augue.'
                },
                {id: 2, name: 'Option2',
                    content: 'This is the new content that is being displayed'
                }
            ];

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


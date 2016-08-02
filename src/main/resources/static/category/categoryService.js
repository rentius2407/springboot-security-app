angular.module('app.category', [])
        .factory('CategoryService', function ($http) {
            return {
                rootCategory: function () {
                    return $http.get('/category');
                },
                categoryByParent: function (parentId) {
                    return $http.get('/category/parent/' + parentId);
                }
            };
        });


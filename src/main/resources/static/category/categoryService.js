angular.module('app.category', [])
        .factory('CategoryService', function ($http) {
            return {
                rootCategory: function () {
                    return $http.get('/api/category');
                },
                categoryByParent: function (parentId) {
                    return $http.get('/api/category/parent/' + parentId);
                },
                categoryById: function (id) {
                    return $http.get('/api/category/' + id);
                },
                createOption: function (id, option) {
                    return $http.post('/api/category/' + id, option);
                },
                categoryByIdWithOptions: function (id) {
                    return $http.get('/api/category/' + id + '/option');
                },
                findOptionByCategoryIdAndId: function (categoryId, optionId) {
                    return $http.get('/api/category/' + categoryId + '/option/' + optionId);
                }
            };
        });


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
                },
                updateOption: function (categoryId, option) {
                    return $http.put('/api/category/' + categoryId + '/option', option);
                },
                findOptionByCategoryAndGroup: function (categoryId, groupId) {
                    return $http.get('/api/category/' + categoryId + '/group/' + groupId + '/option');
                },
                assignedGroups: function (categoryId) {
                    return $http.get('/api/category/' + categoryId + '/group');
                },
                assignGroup: function (categoryId, groupId) {
                    return $http.post('/api/category/' + categoryId + '/group/' + groupId);
                }
            };
        });


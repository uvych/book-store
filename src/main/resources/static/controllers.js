var Controllers= angular.module('app');


Controllers.controller('booksController',function ($scope, $http) {

    fillTable = async function () {
        await  $http.get(contextPath + '/api/v1/books')
            .then(function (response) {
                $scope.BooksList = response.data;
            });
    };
    fillTable();
});

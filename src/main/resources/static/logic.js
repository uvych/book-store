var app = angular.module('app', ['ngRoute','ui.bootstrap']);
var contextPath = 'http://localhost:8185/store'




app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'about-page.html',
            controller: 'aboutController'
        })
        .when('/books', {
            templateUrl: 'book-store.html',
            controller: 'booksController'
        })
});

app.controller('booksController',function ($scope, $http) {

    fillTable = async function () {
        await  $http.get(contextPath + '/api/v1/books')
            .then(function (response) {
                $scope.BooksList = response.data;
            });
    };
    fillTable();

    $scope.FilterBooks = function(minPrice,maxPrice,title) {
        $scope.minPrice = minPrice;
        $scope.maxPrice = maxPrice;
        $scope.title = title;
        $scope.filteredItems = $scope.BooksList;
        if (minPrice != null && minPrice !== "") {
            $scope.filteredItems = $scope.BooksList.filter(function (book) {
                return book.price >= minPrice;
            })
        }
        if (maxPrice != null && maxPrice !== "") {
            $scope.filteredItems = $scope.filteredItems.filter(function (book) {
                return book.price <= maxPrice;
            })
        }
        if (title != null && title !== "") {
            $scope.filteredItems = $scope.filteredItems.filter(function (book) {
                return book.title.indexOf(title) + 1;
            })
        }
    }

    $scope.Page = function () {

    }
});




// START - USED SERVICES
/*
 *	lecturersService.delete
 *		PARAMS: 
 *					ObjectId id - Id
 *		
 *
 *	lecturersService.list
 *		PARAMS: 
 *		
 *
 */
// END - USED SERVICES

// START - REQUIRED RESOURCES
/*
 * lecturersService  
 */
// END - REQUIRED RESOURCES


//CRUD LIST FOR [object Object]

app.controller('lecturersListController', ['$scope', 'lecturersService',
    function ($scope, lecturersService ) {
		
    	$scope.list = lecturersService.query();
    	
}]);
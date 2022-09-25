// START - USED SERVICES
/*
 *	coursesService.delete
 *		PARAMS: 
 *					ObjectId id - Id
 *		
 *
 *	coursesService.list
 *		PARAMS: 
 *		
 *
 */
// END - USED SERVICES

// START - REQUIRED RESOURCES
/*
 * coursesService  
 */
// END - REQUIRED RESOURCES


//CRUD LIST FOR [object Object]

app.controller('coursesListController', ['$scope', 'coursesService',
    function ($scope, coursesService ) {
		
    	$scope.list = coursesService.query();
    	
}]);
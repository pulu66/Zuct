// START - USED SERVICES
/*
 *	programsService.delete
 *		PARAMS: 
 *					ObjectId id - Id
 *		
 *
 *	programsService.list
 *		PARAMS: 
 *		
 *
 */
// END - USED SERVICES

// START - REQUIRED RESOURCES
/*
 * programsService  
 */
// END - REQUIRED RESOURCES


//CRUD LIST FOR [object Object]

app.controller('programsListController', ['$scope', 'programsService',
    function ($scope, programsService ) {
		
    	$scope.list = programsService.query();
    	
}]);
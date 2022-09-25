// START - USED SERVICES
/*
 *	studentsService.delete
 *		PARAMS: 
 *					ObjectId id - Id
 *		
 *
 *	studentsService.list
 *		PARAMS: 
 *		
 *
 */
// END - USED SERVICES

// START - REQUIRED RESOURCES
/*
 * studentsService  
 */
// END - REQUIRED RESOURCES


//CRUD LIST FOR [object Object]

app.controller('studentsListController', ['$scope', 'studentsService',
    function ($scope, studentsService ) {
		
    	$scope.list = studentsService.query();
    	
}]);
// START - USED SERVICES
/*
 *	coursesService.create
 *		PARAMS: 
 *		
 *
 *	coursesService.update
 *		PARAMS: 
 *					ObjectId id - Id
 *		
 *
 *	coursesService.get
 *		PARAMS: 
 *					ObjectId id - Id resource
 *		
 *
 */
// END - USED SERVICES

// START - REQUIRED RESOURCES
/*
 * coursesService  
 */
// END - REQUIRED RESOURCES

app.controller('coursesEditController', ['$scope', '$location', '$routeParams', '$q', 'coursesService', 'programsService', 'lecturersService', 'studentsService',
    function ($scope, $location, $routeParams, $q, coursesService , programsService, lecturersService, studentsService) {

    	//manage create and save
		$scope.external = {};
		
    	if ($routeParams.id != 'new')
    	{
        	$scope.id = $routeParams.id;
        	$scope.obj = coursesService.get({_id: $scope.id});
        	$scope.external._students_takes = studentsService.findBy_takes({key: $scope.id});
        	
    	}
    	else{
    		$scope.obj = new coursesService();
        	$scope.external._students_takes = [];
        	
    	}
    	
    	$scope.save = function(){
    		$scope.obj.$save().then(function(data){
    			$scope.obj=data;
        		$location.path('/courseses/');
    		});
    	}
    	
    	$scope.remove = function(){
    		coursesService.remove({_id: $scope.obj._id}).$promise.then(function(){
				$('#removeModal').modal('hide');
				$('.modal-backdrop').remove();
				$('.modal-open').removeClass("modal-open");
        		$location.path('/courseses/');
    		});
    	}
    	
    		
        //manage relation _contains
        		
    	$scope.list_programs = programsService.query();

    		
        //manage relation _teaches
        		
    	$scope.list_lecturers = lecturersService.query();

    		
        //manage External relation _takes
        		
    	$scope.list_students_takes = studentsService.query();
    	
}]);
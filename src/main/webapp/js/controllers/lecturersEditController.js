// START - USED SERVICES
/*
 *	lecturersService.create
 *		PARAMS: 
 *		
 *
 *	lecturersService.update
 *		PARAMS: 
 *					ObjectId id - Id
 *		
 *
 *	lecturersService.get
 *		PARAMS: 
 *					ObjectId id - Id resource
 *		
 *
 */
// END - USED SERVICES

// START - REQUIRED RESOURCES
/*
 * lecturersService  
 */
// END - REQUIRED RESOURCES

app.controller('lecturersEditController', ['$scope', '$location', '$routeParams', '$q', 'lecturersService', 'programsService', 'studentsService', 'coursesService',
    function ($scope, $location, $routeParams, $q, lecturersService , programsService, studentsService, coursesService) {

    	//manage create and save
		$scope.external = {};
		
    	if ($routeParams.id != 'new')
    	{
        	$scope.id = $routeParams.id;
        	$scope.obj = lecturersService.get({_id: $scope.id});
        	$scope.external._students_taught = studentsService.findBy_taught({key: $scope.id});
        	$scope.external._courses_teaches = coursesService.findBy_teaches({key: $scope.id});
        	
    	}
    	else{
    		$scope.obj = new lecturersService();
        	$scope.external._students_taught = [];
        	$scope.external._courses_teaches = [];
        	
    	}
    	
    	$scope.save = function(){
    		$scope.obj.$save().then(function(data){
    			$scope.obj=data;
        		$location.path('/lecturerses/');
    		});
    	}
    	
    	$scope.remove = function(){
    		lecturersService.remove({_id: $scope.obj._id}).$promise.then(function(){
				$('#removeModal').modal('hide');
				$('.modal-backdrop').remove();
				$('.modal-open').removeClass("modal-open");
        		$location.path('/lecturerses/');
    		});
    	}
    	
    		
        //manage relation _contain
        		
    	$scope.list_programs = programsService.query();

    		
        //manage External relation _taught
        		
    	$scope.list_students_taught = studentsService.query();
    	
    		
        //manage External relation _teaches
        		
    	$scope.list_courses_teaches = coursesService.query();
    	
}]);
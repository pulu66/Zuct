// START - USED SERVICES
/*
 *	programsService.create
 *		PARAMS: 
 *		
 *
 *	programsService.update
 *		PARAMS: 
 *					ObjectId id - Id
 *		
 *
 *	programsService.get
 *		PARAMS: 
 *					ObjectId id - Id resource
 *		
 *
 */
// END - USED SERVICES

// START - REQUIRED RESOURCES
/*
 * programsService  
 */
// END - REQUIRED RESOURCES

app.controller('programsEditController', ['$scope', '$location', '$routeParams', '$q', 'programsService', 'lecturersService', 'coursesService', 'studentsService',
    function ($scope, $location, $routeParams, $q, programsService , lecturersService, coursesService, studentsService) {

    	//manage create and save
		$scope.external = {};
		
    	if ($routeParams.id != 'new')
    	{
        	$scope.id = $routeParams.id;
        	$scope.obj = programsService.get({_id: $scope.id});
        	$scope.external._lecturers_contain = lecturersService.findBy_contain({key: $scope.id});
        	$scope.external._courses_contains = coursesService.findBy_contains({key: $scope.id});
        	$scope.external._students_enrolls = studentsService.findBy_enrolls({key: $scope.id});
        	
    	}
    	else{
    		$scope.obj = new programsService();
        	$scope.external._lecturers_contain = [];
        	$scope.external._courses_contains = [];
        	$scope.external._students_enrolls = [];
        	
    	}
    	
    	$scope.save = function(){
    		$scope.obj.$save().then(function(data){
    			$scope.obj=data;
        		$location.path('/programses/');
    		});
    	}
    	
    	$scope.remove = function(){
    		programsService.remove({_id: $scope.obj._id}).$promise.then(function(){
				$('#removeModal').modal('hide');
				$('.modal-backdrop').remove();
				$('.modal-open').removeClass("modal-open");
        		$location.path('/programses/');
    		});
    	}
    	
    		
        //manage External relation _contain
        		
    	$scope.list_lecturers_contain = lecturersService.query();
    	
    		
        //manage External relation _contains
        		
    	$scope.list_courses_contains = coursesService.query();
    	
    		
        //manage External relation _enrolls
        		
    	$scope.list_students_enrolls = studentsService.query();
    	
}]);
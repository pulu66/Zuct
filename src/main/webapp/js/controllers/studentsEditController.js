// START - USED SERVICES
/*
 *	studentsService.create
 *		PARAMS: 
 *		
 *
 *	studentsService.update
 *		PARAMS: 
 *					ObjectId id - Id
 *		
 *
 *	studentsService.get
 *		PARAMS: 
 *					ObjectId id - Id resource
 *		
 *
 */
// END - USED SERVICES

// START - REQUIRED RESOURCES
/*
 * studentsService  
 */
// END - REQUIRED RESOURCES

app.controller('studentsEditController', ['$scope', '$location', '$routeParams', '$q', 'studentsService', 'programsService', 'coursesService', 'lecturersService',
    function ($scope, $location, $routeParams, $q, studentsService , programsService, coursesService, lecturersService) {

    	//manage create and save
		$scope.external = {};
		
    	if ($routeParams.id != 'new')
    	{
        	$scope.id = $routeParams.id;
        	$scope.obj = studentsService.get({_id: $scope.id});
        	
    	}
    	else{
    		$scope.obj = new studentsService();
        	
    	}
    	
    	$scope.save = function(){
    		$scope.obj.$save().then(function(data){
    			$scope.obj=data;
        		$location.path('/studentses/');
    		});
    	}
    	
    	$scope.remove = function(){
    		studentsService.remove({_id: $scope.obj._id}).$promise.then(function(){
				$('#removeModal').modal('hide');
				$('.modal-backdrop').remove();
				$('.modal-open').removeClass("modal-open");
        		$location.path('/studentses/');
    		});
    	}
    	
    		
        //manage relation _enrolls
        		
    	$scope.list_programs = programsService.query();

    		
        //manage relation _takes
        		
    	$scope.list_courses = coursesService.query();

    	$scope.contain_courses = function(item){
    		if (!$scope.obj._takes) return false;
    		return $scope.obj._takes.indexOf(item) != -1;
    	}
		    	
		$scope.add_courses = function(item){
			if(!$scope.obj._takes)
				$scope.obj._takes = [];
			$scope.obj._takes.push(item);
		}
		
		$scope.remove_courses = function(index){
			$scope.obj._takes.splice(index, 1);
		}
    		
        //manage relation _taught
        		
    	$scope.list_lecturers = lecturersService.query();

    	$scope.contain_lecturers = function(item){
    		if (!$scope.obj._taught) return false;
    		return $scope.obj._taught.indexOf(item) != -1;
    	}
		    	
		$scope.add_lecturers = function(item){
			if(!$scope.obj._taught)
				$scope.obj._taught = [];
			$scope.obj._taught.push(item);
		}
		
		$scope.remove_lecturers = function(index){
			$scope.obj._taught.splice(index, 1);
		}
}]);
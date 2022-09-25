/* 
 * ADD HERE YOUR CUSTOMIZAZION, 
 * THESE WILL OVERRIDE DEFAULT SERVICES 
 * THIS FILE WILL BE NEVER OVERWRITTEN BY SKAFFOLDER
 * 
 */

programsService = app.factory('programsServiceCustom', ['$rootScope', function($rootScope){
  return {
	  //WRITE HERE YOUR CUSTOM SERVICE
	};
  }]);
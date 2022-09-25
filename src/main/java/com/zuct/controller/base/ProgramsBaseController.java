package com.zuct.controller.base;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.springframework.security.access.annotation.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import com.zuct.db.zuct_db.service.ProgramsService;
import com.zuct.db.zuct_db.entity.Programs;
import com.zuct.db.zuct_db.dtos.ProgramsDto;

//IMPORT RELATIONS

import com.zuct.db.zuct_db.service.LecturersService;

import com.zuct.db.zuct_db.service.CoursesService;

import com.zuct.db.zuct_db.service.StudentsService;


public class ProgramsBaseController {
    
    @Autowired
	ProgramsService programsService;

	
	@Autowired
	LecturersService lecturersService;
	
	@Autowired
	CoursesService coursesService;
	
	@Autowired
	StudentsService studentsService;
	

	@Autowired
	private ModelMapper modelMapper;



//CRUD METHODS


    //CRUD - CREATE
    @Secured({ "ROLE_PRIVATE_USER" })
	@PostMapping("/programs")
	public ResponseEntity<ProgramsDto> insert(@RequestBody Programs obj) {
				
		//external relation lecturers
		if (obj.getLecturers() != null && !obj.getLecturers().isEmpty())
			obj.getLecturers().forEach(lecturers -> lecturersService.insert(lecturers));
		
		//external relation courses
		if (obj.getCourses() != null && !obj.getCourses().isEmpty())
			obj.getCourses().forEach(courses -> coursesService.insert(courses));
		
		//external relation students
		if (obj.getStudents() != null && !obj.getStudents().isEmpty())
			obj.getStudents().forEach(students -> studentsService.insert(students));
		
		
		return ResponseEntity.ok().body(convertToDto(programsService.insert(obj)));
	}

	
    //CRUD - REMOVE
    @Secured({ "ROLE_PRIVATE_USER" })
	@DeleteMapping("/programs/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		Programs programsSelected = programsService.getOne(id);
		
		//external relation lecturers
		if (programsSelected.getLecturers() != null && !programsSelected.getLecturers().isEmpty()) {
			programsSelected.getLecturers().forEach(lecturers -> lecturersService.delete(lecturers.get_id()));
		}
		
		//external relation courses
		if (programsSelected.getCourses() != null && !programsSelected.getCourses().isEmpty()) {
			programsSelected.getCourses().forEach(courses -> coursesService.delete(courses.get_id()));
		}
		
		//external relation students
		if (programsSelected.getStudents() != null && !programsSelected.getStudents().isEmpty()) {
			programsSelected.getStudents().forEach(students -> studentsService.delete(students.get_id()));
		}
		
		programsService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	
    //CRUD - GET ONE
    @Secured({ "ROLE_PRIVATE_USER" })
	@GetMapping("/programs/{id}")
	public ResponseEntity<ProgramsDto> get(@PathVariable("id") Long id) {
		Programs programsSelected = programsService.getOne(id);
		return ResponseEntity.ok().body(convertToDto(programsSelected));
	}
	
	
    //CRUD - GET LIST
    @Secured({ "ROLE_PRIVATE_USER" })
	@GetMapping("/programs")
	public ResponseEntity<List<ProgramsDto>> getList() {
		List<Programs> list = programsService.getAll();
		List<ProgramsDto> listDto = list.stream()
				.map(programs -> convertToDto(programs))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}


    //CRUD - EDIT
    @Secured({ "ROLE_PRIVATE_USER" })
	@PostMapping("/programs/{id}")
	public ResponseEntity<ProgramsDto> update(@RequestBody Programs obj, @PathVariable("id") Long id) {
	    
		//external relation lecturers
		if (obj.getLecturers() != null)
			obj.getLecturers().forEach(lecturers -> lecturersService.insert(lecturers));
		
		//external relation courses
		if (obj.getCourses() != null)
			obj.getCourses().forEach(courses -> coursesService.insert(courses));
		
		//external relation students
		if (obj.getStudents() != null)
			obj.getStudents().forEach(students -> studentsService.insert(students));
		
		
		return ResponseEntity.ok().body(convertToDto(programsService.insert(obj)));
	}
	


/*
 * CUSTOM SERVICES
 * 
 *	These services will be overwritten and implemented in  Custom.js
 */


	private ProgramsDto convertToDto(Programs programs) {
		ProgramsDto programsDto = modelMapper.map(programs, ProgramsDto.class);
		return programsDto;
	}
}

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
import com.zuct.db.zuct_db.service.CoursesService;
import com.zuct.db.zuct_db.entity.Courses;
import com.zuct.db.zuct_db.dtos.CoursesDto;

import com.zuct.db.zuct_db.entity.Programs;

import com.zuct.db.zuct_db.entity.Lecturers;

//IMPORT RELATIONS

import com.zuct.db.zuct_db.service.ProgramsService;

import com.zuct.db.zuct_db.service.StudentsService;

import com.zuct.db.zuct_db.service.LecturersService;


public class CoursesBaseController {
    
    @Autowired
	CoursesService coursesService;

	
	@Autowired
	ProgramsService programsService;
	
	@Autowired
	StudentsService studentsService;
	
	@Autowired
	LecturersService lecturersService;
	

	@Autowired
	private ModelMapper modelMapper;



//CRUD METHODS


    //CRUD - CREATE
    @Secured({ "ROLE_PRIVATE_USER" })
	@PostMapping("/courses")
	public ResponseEntity<CoursesDto> insert(@RequestBody Courses obj) {
				
		//external relation _contains
		if (obj.get_contains() != null) {
			if (obj.get_contains().get_id() == null)
				programsService.insert(obj.get_contains());
			else
				programsService.insert(programsService.getOne(obj.get_contains().get_id()));
		}
				
		//external relation students
		if (obj.getStudents() != null) {
			obj.getStudents().forEach(students -> {
				students.add_takes(obj);
				studentsService.insert(students);
			});
		}
		
		//external relation _teaches
		if (obj.get_teaches() != null) {
			if (obj.get_teaches().get_id() == null)
				lecturersService.insert(obj.get_teaches());
			else
				lecturersService.insert(lecturersService.getOne(obj.get_teaches().get_id()));
		}
				
		
		return ResponseEntity.ok().body(convertToDto(coursesService.insert(obj)));
	}

	
    //CRUD - REMOVE
    @Secured({ "ROLE_PRIVATE_USER" })
	@DeleteMapping("/courses/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		Courses coursesSelected = coursesService.getOne(id);
		
		
		//external relation students
		if (coursesSelected.getStudents() != null) {
			coursesSelected.getStudents().forEach(students -> {
				students.get_takes().removeIf(courses -> courses.get_id() == coursesSelected.get_id());
				studentsService.insert(students);
			});
		}
		
		
		coursesService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	
    //CRUD - GET ONE
    @Secured({ "ROLE_PRIVATE_USER" })
	@GetMapping("/courses/{id}")
	public ResponseEntity<CoursesDto> get(@PathVariable("id") Long id) {
		Courses coursesSelected = coursesService.getOne(id);
		return ResponseEntity.ok().body(convertToDto(coursesSelected));
	}
	
	
    //CRUD - GET LIST
    @Secured({ "ROLE_PRIVATE_USER" })
	@GetMapping("/courses")
	public ResponseEntity<List<CoursesDto>> getList() {
		List<Courses> list = coursesService.getAll();
		List<CoursesDto> listDto = list.stream()
				.map(courses -> convertToDto(courses))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}


    //CRUD - EDIT
    @Secured({ "ROLE_PRIVATE_USER" })
	@PostMapping("/courses/{id}")
	public ResponseEntity<CoursesDto> update(@RequestBody Courses obj, @PathVariable("id") Long id) {
	    
		//external relation _contains
		if (obj.get_contains() != null) {
			if (obj.get_contains().get_id() == null)
				programsService.insert(obj.get_contains());
			else
				programsService.insert(programsService.getOne(obj.get_contains().get_id()));
		}
				
		//external relation students
		if (obj.getStudents() != null) {
			obj.getStudents().forEach(students -> {
				students.add_takes(obj);
				studentsService.insert(students);
			});
		}
		
		//external relation _teaches
		if (obj.get_teaches() != null) {
			if (obj.get_teaches().get_id() == null)
				lecturersService.insert(obj.get_teaches());
			else
				lecturersService.insert(lecturersService.getOne(obj.get_teaches().get_id()));
		}
				
		
		return ResponseEntity.ok().body(convertToDto(coursesService.insert(obj)));
	}
	


/*
 * CUSTOM SERVICES
 * 
 *	These services will be overwritten and implemented in  Custom.js
 */


	private CoursesDto convertToDto(Courses courses) {
		CoursesDto coursesDto = modelMapper.map(courses, CoursesDto.class);
		return coursesDto;
	}
}

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
import com.zuct.db.zuct_db.service.LecturersService;
import com.zuct.db.zuct_db.entity.Lecturers;
import com.zuct.db.zuct_db.dtos.LecturersDto;

import com.zuct.db.zuct_db.entity.Programs;

//IMPORT RELATIONS

import com.zuct.db.zuct_db.service.ProgramsService;

import com.zuct.db.zuct_db.service.StudentsService;

import com.zuct.db.zuct_db.service.CoursesService;


public class LecturersBaseController {
    
    @Autowired
	LecturersService lecturersService;

	
	@Autowired
	ProgramsService programsService;
	
	@Autowired
	StudentsService studentsService;
	
	@Autowired
	CoursesService coursesService;
	

	@Autowired
	private ModelMapper modelMapper;



//CRUD METHODS


    //CRUD - CREATE
    @Secured({ "ROLE_PRIVATE_USER" })
	@PostMapping("/lecturers")
	public ResponseEntity<LecturersDto> insert(@RequestBody Lecturers obj) {
				
		//external relation _contain
		if (obj.get_contain() != null) {
			if (obj.get_contain().get_id() == null)
				programsService.insert(obj.get_contain());
			else
				programsService.insert(programsService.getOne(obj.get_contain().get_id()));
		}
				
		//external relation students
		if (obj.getStudents() != null) {
			obj.getStudents().forEach(students -> {
				students.add_taught(obj);
				studentsService.insert(students);
			});
		}
		
		//external relation courses
		if (obj.getCourses() != null && !obj.getCourses().isEmpty())
			obj.getCourses().forEach(courses -> coursesService.insert(courses));
		
		
		return ResponseEntity.ok().body(convertToDto(lecturersService.insert(obj)));
	}

	
    //CRUD - REMOVE
    @Secured({ "ROLE_PRIVATE_USER" })
	@DeleteMapping("/lecturers/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		Lecturers lecturersSelected = lecturersService.getOne(id);
		
		
		//external relation students
		if (lecturersSelected.getStudents() != null) {
			lecturersSelected.getStudents().forEach(students -> {
				students.get_taught().removeIf(lecturers -> lecturers.get_id() == lecturersSelected.get_id());
				studentsService.insert(students);
			});
		}
		
		//external relation courses
		if (lecturersSelected.getCourses() != null && !lecturersSelected.getCourses().isEmpty()) {
			lecturersSelected.getCourses().forEach(courses -> coursesService.delete(courses.get_id()));
		}
		
		lecturersService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	
    //CRUD - GET ONE
    @Secured({ "ROLE_PRIVATE_USER" })
	@GetMapping("/lecturers/{id}")
	public ResponseEntity<LecturersDto> get(@PathVariable("id") Long id) {
		Lecturers lecturersSelected = lecturersService.getOne(id);
		return ResponseEntity.ok().body(convertToDto(lecturersSelected));
	}
	
	
    //CRUD - GET LIST
    @Secured({ "ROLE_PRIVATE_USER" })
	@GetMapping("/lecturers")
	public ResponseEntity<List<LecturersDto>> getList() {
		List<Lecturers> list = lecturersService.getAll();
		List<LecturersDto> listDto = list.stream()
				.map(lecturers -> convertToDto(lecturers))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}


    //CRUD - EDIT
    @Secured({ "ROLE_PRIVATE_USER" })
	@PostMapping("/lecturers/{id}")
	public ResponseEntity<LecturersDto> update(@RequestBody Lecturers obj, @PathVariable("id") Long id) {
	    
		//external relation _contain
		if (obj.get_contain() != null) {
			if (obj.get_contain().get_id() == null)
				programsService.insert(obj.get_contain());
			else
				programsService.insert(programsService.getOne(obj.get_contain().get_id()));
		}
				
		//external relation students
		if (obj.getStudents() != null) {
			obj.getStudents().forEach(students -> {
				students.add_taught(obj);
				studentsService.insert(students);
			});
		}
		
		//external relation courses
		if (obj.getCourses() != null)
			obj.getCourses().forEach(courses -> coursesService.insert(courses));
		
		
		return ResponseEntity.ok().body(convertToDto(lecturersService.insert(obj)));
	}
	


/*
 * CUSTOM SERVICES
 * 
 *	These services will be overwritten and implemented in  Custom.js
 */


	private LecturersDto convertToDto(Lecturers lecturers) {
		LecturersDto lecturersDto = modelMapper.map(lecturers, LecturersDto.class);
		return lecturersDto;
	}
}

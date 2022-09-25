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
import com.zuct.db.zuct_db.service.StudentsService;
import com.zuct.db.zuct_db.entity.Students;
import com.zuct.db.zuct_db.dtos.StudentsDto;

import com.zuct.db.zuct_db.entity.Programs;

import com.zuct.db.zuct_db.entity.Courses;

import com.zuct.db.zuct_db.entity.Lecturers;

//IMPORT RELATIONS

import com.zuct.db.zuct_db.service.ProgramsService;

import com.zuct.db.zuct_db.service.CoursesService;

import com.zuct.db.zuct_db.service.LecturersService;


public class StudentsBaseController {
    
    @Autowired
	StudentsService studentsService;

	
	@Autowired
	ProgramsService programsService;
	
	@Autowired
	CoursesService coursesService;
	
	@Autowired
	LecturersService lecturersService;
	

	@Autowired
	private ModelMapper modelMapper;



//CRUD METHODS


    //CRUD - CREATE
    @Secured({ "ROLE_PRIVATE_USER" })
	@PostMapping("/students")
	public ResponseEntity<StudentsDto> insert(@RequestBody Students obj) {
				
		//external relation _enrolls
		if (obj.get_enrolls() != null) {
			if (obj.get_enrolls().get_id() == null)
				programsService.insert(obj.get_enrolls());
			else
				programsService.insert(programsService.getOne(obj.get_enrolls().get_id()));
		}
				
		//external relation _takes
		if (obj.get_takes() != null && !obj.get_takes().isEmpty()) {
			obj.get_takes().forEach(courses -> {
				courses = coursesService.getOne(courses.get_id());
				courses.addStudents(obj);
				coursesService.insert(courses);
			});
		}
		
		//external relation _taught
		if (obj.get_taught() != null && !obj.get_taught().isEmpty()) {
			obj.get_taught().forEach(lecturers -> {
				lecturers = lecturersService.getOne(lecturers.get_id());
				lecturers.addStudents(obj);
				lecturersService.insert(lecturers);
			});
		}
		
		
		return ResponseEntity.ok().body(convertToDto(studentsService.insert(obj)));
	}

	
    //CRUD - REMOVE
    @Secured({ "ROLE_PRIVATE_USER" })
	@DeleteMapping("/students/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		Students studentsSelected = studentsService.getOne(id);
		
		
		//external relation _takes
		if (studentsSelected.get_takes() != null) {
			studentsSelected.get_takes().forEach(courses -> {
				courses.getStudents().removeIf(students -> students.get_id() == studentsSelected.get_id());
				coursesService.insert(courses);
			});
		}
		
		//external relation _taught
		if (studentsSelected.get_taught() != null) {
			studentsSelected.get_taught().forEach(lecturers -> {
				lecturers.getStudents().removeIf(students -> students.get_id() == studentsSelected.get_id());
				lecturersService.insert(lecturers);
			});
		}
		
		studentsService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	
    //CRUD - GET ONE
    @Secured({ "ROLE_PRIVATE_USER" })
	@GetMapping("/students/{id}")
	public ResponseEntity<StudentsDto> get(@PathVariable("id") Long id) {
		Students studentsSelected = studentsService.getOne(id);
		return ResponseEntity.ok().body(convertToDto(studentsSelected));
	}
	
	
    //CRUD - GET LIST
    @Secured({ "ROLE_PRIVATE_USER" })
	@GetMapping("/students")
	public ResponseEntity<List<StudentsDto>> getList() {
		List<Students> list = studentsService.getAll();
		List<StudentsDto> listDto = list.stream()
				.map(students -> convertToDto(students))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}


    //CRUD - EDIT
    @Secured({ "ROLE_PRIVATE_USER" })
	@PostMapping("/students/{id}")
	public ResponseEntity<StudentsDto> update(@RequestBody Students obj, @PathVariable("id") Long id) {
	    
		//external relation _enrolls
		if (obj.get_enrolls() != null) {
			if (obj.get_enrolls().get_id() == null)
				programsService.insert(obj.get_enrolls());
			else
				programsService.insert(programsService.getOne(obj.get_enrolls().get_id()));
		}
				
		//external relation _takes
		if (obj.get_takes() != null) {
			obj.get_takes().forEach(courses -> {
				courses = coursesService.getOne(courses.get_id());
				courses.addStudents(obj);
				coursesService.insert(courses);
			});
		}
		
		//external relation _taught
		if (obj.get_taught() != null) {
			obj.get_taught().forEach(lecturers -> {
				lecturers = lecturersService.getOne(lecturers.get_id());
				lecturers.addStudents(obj);
				lecturersService.insert(lecturers);
			});
		}
		
		
		return ResponseEntity.ok().body(convertToDto(studentsService.insert(obj)));
	}
	


/*
 * CUSTOM SERVICES
 * 
 *	These services will be overwritten and implemented in  Custom.js
 */


	private StudentsDto convertToDto(Students students) {
		StudentsDto studentsDto = modelMapper.map(students, StudentsDto.class);
		return studentsDto;
	}
}

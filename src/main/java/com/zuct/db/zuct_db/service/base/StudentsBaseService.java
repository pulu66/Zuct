package com.zuct.db.zuct_db.service.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


import com.zuct.db.zuct_db.entity.Students;

import com.zuct.db.zuct_db.entity.Programs;

import com.zuct.db.zuct_db.entity.Courses;

import com.zuct.db.zuct_db.entity.Lecturers;

import com.zuct.db.zuct_db.repositories.StudentsRepository;

@Service
@Transactional
public class StudentsBaseService {

	
	@Autowired
	StudentsRepository studentsRepository;


    // CRUD METHODS
    
    // CRUD - CREATE
    
	public Students insert(Students students) {
		return studentsRepository.save(students);
	}
	
	// CRUD - REMOVE
    
	public void delete(Long id) {
		studentsRepository.delete(id);
	}

	// CRUD - GET ONE
    	
	public Students getOne(Long id) {
		return studentsRepository.findOne(id);
	}

    	
    // CRUD - GET LIST
    	
	public List<Students> getAll() {
		List<Students> list = new ArrayList<>();
		studentsRepository.findAll().forEach(list::add);
		return list;
	}
	

}

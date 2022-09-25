package com.zuct.db.zuct_db.service.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


import com.zuct.db.zuct_db.entity.Courses;

import com.zuct.db.zuct_db.entity.Programs;

import com.zuct.db.zuct_db.entity.Lecturers;

import com.zuct.db.zuct_db.repositories.CoursesRepository;

@Service
@Transactional
public class CoursesBaseService {

	
	@Autowired
	CoursesRepository coursesRepository;


    // CRUD METHODS
    
    // CRUD - CREATE
    
	public Courses insert(Courses courses) {
		return coursesRepository.save(courses);
	}
	
	// CRUD - REMOVE
    
	public void delete(Long id) {
		coursesRepository.delete(id);
	}

	// CRUD - GET ONE
    	
	public Courses getOne(Long id) {
		return coursesRepository.findOne(id);
	}

    	
    // CRUD - GET LIST
    	
	public List<Courses> getAll() {
		List<Courses> list = new ArrayList<>();
		coursesRepository.findAll().forEach(list::add);
		return list;
	}
	

}

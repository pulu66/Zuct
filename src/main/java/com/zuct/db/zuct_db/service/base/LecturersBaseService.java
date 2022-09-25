package com.zuct.db.zuct_db.service.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


import com.zuct.db.zuct_db.entity.Lecturers;

import com.zuct.db.zuct_db.entity.Programs;

import com.zuct.db.zuct_db.repositories.LecturersRepository;

@Service
@Transactional
public class LecturersBaseService {

	
	@Autowired
	LecturersRepository lecturersRepository;


    // CRUD METHODS
    
    // CRUD - CREATE
    
	public Lecturers insert(Lecturers lecturers) {
		return lecturersRepository.save(lecturers);
	}
	
	// CRUD - REMOVE
    
	public void delete(Long id) {
		lecturersRepository.delete(id);
	}

	// CRUD - GET ONE
    	
	public Lecturers getOne(Long id) {
		return lecturersRepository.findOne(id);
	}

    	
    // CRUD - GET LIST
    	
	public List<Lecturers> getAll() {
		List<Lecturers> list = new ArrayList<>();
		lecturersRepository.findAll().forEach(list::add);
		return list;
	}
	

}

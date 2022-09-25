package com.zuct.db.zuct_db.service.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


import com.zuct.db.zuct_db.entity.Programs;

import com.zuct.db.zuct_db.repositories.ProgramsRepository;

@Service
@Transactional
public class ProgramsBaseService {

	
	@Autowired
	ProgramsRepository programsRepository;


    // CRUD METHODS
    
    // CRUD - CREATE
    
	public Programs insert(Programs programs) {
		return programsRepository.save(programs);
	}
	
	// CRUD - REMOVE
    
	public void delete(Long id) {
		programsRepository.delete(id);
	}

	// CRUD - GET ONE
    	
	public Programs getOne(Long id) {
		return programsRepository.findOne(id);
	}

    	
    // CRUD - GET LIST
    	
	public List<Programs> getAll() {
		List<Programs> list = new ArrayList<>();
		programsRepository.findAll().forEach(list::add);
		return list;
	}
	

}

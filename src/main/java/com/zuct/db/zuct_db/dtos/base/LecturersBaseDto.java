package com.zuct.db.zuct_db.dtos.base;

/**
 * 
 * 
  _____                      _              _ _ _     _   _     _        __ _ _      
 |  __ \                    | |            | (_) |   | | | |   (_)      / _(_) |     
 | |  | | ___    _ __   ___ | |_    ___  __| |_| |_  | |_| |__  _ ___  | |_ _| | ___ 
 | |  | |/ _ \  | '_ \ / _ \| __|  / _ \/ _` | | __| | __| '_ \| / __| |  _| | |/ _ \
 | |__| | (_) | | | | | (_) | |_  |  __/ (_| | | |_  | |_| | | | \__ \ | | | | |  __/
 |_____/ \___/  |_| |_|\___/ \__|  \___|\__,_|_|\__|  \__|_| |_|_|___/ |_| |_|_|\___|
 
                                                                                   
 * DO NOT EDIT THIS FILE!! 
 *
 *  FOR CUSTOMIZE LecturersBaseDto PLEASE EDIT ../LecturersDto.java
 * 
 *  -- THIS FILE WILL BE OVERWRITTEN ON THE NEXT SKAFFOLDER CODE GENERATION --
 * 
 */
 

/**
 * This is the dto of Lecturers object
 * 
 */

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import com.zuct.db.zuct_db.entity.Lecturers;
// Import relations
import com.zuct.db.zuct_db.dtos.infos.ProgramsInfo;


import com.zuct.db.zuct_db.dtos.infos.CoursesInfo;


import com.zuct.db.zuct_db.dtos.infos.StudentsInfo;



public class LecturersBaseDto {
	
	private Long _id;
	
	// Attributes
    private String l_first_name;
    private String l_gender;
    private String l_last_name;
    private String l_middle_name;
    private Double l_phone;
	
	// Relations _contain
	private Long _contain;
	
	// Relations courses
	private List<Long> _teaches_courses = new ArrayList<>();
	
	private List<StudentsInfo> studentss = new ArrayList<>();
	
	
	public Long get_id() {
		return _id;
	}

	public void set_id(Long id) {
		this._id = id;
	}
	
	public String getL_first_name() {
		return l_first_name;
	}

	public void setL_first_name(String l_first_name) {
		this.l_first_name = l_first_name;
	}
	public String getL_gender() {
		return l_gender;
	}

	public void setL_gender(String l_gender) {
		this.l_gender = l_gender;
	}
	public String getL_last_name() {
		return l_last_name;
	}

	public void setL_last_name(String l_last_name) {
		this.l_last_name = l_last_name;
	}
	public String getL_middle_name() {
		return l_middle_name;
	}

	public void setL_middle_name(String l_middle_name) {
		this.l_middle_name = l_middle_name;
	}
	public Double getL_phone() {
		return l_phone;
	}

	public void setL_phone(Double l_phone) {
		this.l_phone = l_phone;
	}
    
	public void set_contain(ProgramsInfo _contain) {
		this._contain = _contain.get_id();
	}

	public Long get_contain() {
		return _contain;
	}
	
	// Relations courses
	
	public List<Long> get_teaches_courses() {
		return this._teaches_courses;
	}

    public void set_teaches_courses(List<CoursesInfo> list) {
		this._teaches_courses = list.stream()
				.map(el -> el.get_id())
				.collect(Collectors.toList());
	}

	

    
	// Relations students

	public List<StudentsInfo> get_taughts() {
		return this.studentss;
	}

	public void set_taughts(List<StudentsInfo> list) {
		this.studentss = list;
	}
	
}
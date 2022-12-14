package com.zuct.db.zuct_db.entity.base;

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
 *  FOR CUSTOMIZE ProgramsBase PLEASE EDIT ../Programs.java
 * 
 *  -- THIS FILE WILL BE OVERWRITTEN ON THE NEXT SKAFFOLDER CODE GENERATION --
 * 
 */
 

/**
 * This is the model of Programs object
 * 
 */

import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import javax.persistence.Id;
import javax.persistence.Column;
import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.FetchType;
import java.util.stream.Collectors;

import com.zuct.db.zuct_db.entity.Programs;
// Import relations
import com.zuct.db.zuct_db.entity.Lecturers;

import com.zuct.db.zuct_db.entity.Courses;

import com.zuct.db.zuct_db.entity.Students;






@MappedSuperclass
public class ProgramsBase {
	
	@Id
	@GeneratedValue
	private Long _id;
	
	// Attributes
	@Column(nullable=false)
    private String pr_description;
	@Column(nullable=false)
    private String pr_name;
	
	// Relations lecturers
	@OneToMany(mappedBy="_contain")
	private List<Lecturers> _contain_lecturers = new ArrayList<>();
	
	// Relations courses
	@OneToMany(mappedBy="_contains")
	private List<Courses> _contains_courses = new ArrayList<>();
	
	// Relations students
	@OneToMany(mappedBy="_enrolls")
	private List<Students> _enrolls_students = new ArrayList<>();
	
	
	
	public Long get_id() {
		return _id;
	}

	public void set_id(Long id) {
		this._id = id;
	}
	
	public String getPr_description() {
		return pr_description;
	}

	public void setPr_description(String pr_description) {
		this.pr_description = pr_description;
	}
	public String getPr_name() {
		return pr_name;
	}

	public void setPr_name(String pr_name) {
		this.pr_name = pr_name;
	}
    
	// Relations lecturers
	
	public List<Lecturers> getLecturers() {
		return this._contain_lecturers;
	}

	public void addLecturers(Lecturers lecturers) {
		this._contain_lecturers.add(lecturers);
	}

	public void removeLecturers(Lecturers lecturers) {
		this._contain_lecturers.remove(lecturers);
	}
	
	// Relations courses
	
	public List<Courses> getCourses() {
		return this._contains_courses;
	}

	public void addCourses(Courses courses) {
		this._contains_courses.add(courses);
	}

	public void removeCourses(Courses courses) {
		this._contains_courses.remove(courses);
	}
	
	// Relations students
	
	public List<Students> getStudents() {
		return this._enrolls_students;
	}

	public void addStudents(Students students) {
		this._enrolls_students.add(students);
	}

	public void removeStudents(Students students) {
		this._enrolls_students.remove(students);
	}
	

    
}
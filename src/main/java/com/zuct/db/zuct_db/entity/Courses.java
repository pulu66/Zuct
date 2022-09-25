package com.zuct.db.zuct_db.entity;

import javax.persistence.Entity;
import com.zuct.db.zuct_db.entity.base.CoursesBase;

@Entity
public class Courses extends CoursesBase {

    public Courses() {}

    public Courses(String id) {
        this.set_id(Long.valueOf(id));
    }

	//OVERRIDE HERE YOUR CUSTOM MAPPER
	
	
}

package com.zuct.db.zuct_db.entity;

import javax.persistence.Entity;
import com.zuct.db.zuct_db.entity.base.LecturersBase;

@Entity
public class Lecturers extends LecturersBase {

    public Lecturers() {}

    public Lecturers(String id) {
        this.set_id(Long.valueOf(id));
    }

	//OVERRIDE HERE YOUR CUSTOM MAPPER
	
	
}

package com.zuct.db.zuct_db.entity;

import javax.persistence.Entity;
import com.zuct.db.zuct_db.entity.base.StudentsBase;

@Entity
public class Students extends StudentsBase {

    public Students() {}

    public Students(String id) {
        this.set_id(Long.valueOf(id));
    }

	//OVERRIDE HERE YOUR CUSTOM MAPPER
	
	
}

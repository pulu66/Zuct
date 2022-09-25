package com.zuct.db.zuct_db.entity;

import javax.persistence.Entity;
import com.zuct.db.zuct_db.entity.base.ProgramsBase;

@Entity
public class Programs extends ProgramsBase {

    public Programs() {}

    public Programs(String id) {
        this.set_id(Long.valueOf(id));
    }

	//OVERRIDE HERE YOUR CUSTOM MAPPER
	
	
}

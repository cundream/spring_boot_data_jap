package com.lc.spring.entity;

import java.io.Serializable;

public class Metrics implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private Object value;
	
	private Object category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	

	

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Metrics [name=" + name + ", value=" + value + ", category=" + category + "]";
    }
    

    
}

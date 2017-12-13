package com.lc.spring.entity;

import java.io.Serializable;

public class Metric implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private Object value;

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

	@Override
	public String toString() {
		return "Metric{" +
				"name='" + name + '\'' +
				", value=" + value +
				'}';
	}
}

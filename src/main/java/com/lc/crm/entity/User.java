package com.lc.crm.entity;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("user")
public class User {
	
	/**
	 * 用户账户
	 */
	@PrimaryKey
	private String username;
	
	/**
	 * 用户姓名
	 */
	@Column
	private String name;
	
	/**
	 * 用户状态
	 */
	private int state;
	
	/**
	 * 部门
	 */
	private String dept;
	
	/**
	 * 简介
	 */
	private String descri;
	
	public User(){}
	
	public User(String username){
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDescri() {
		return descri;
	}

	public void setDescri(String descri) {
		this.descri = descri;
	}

}

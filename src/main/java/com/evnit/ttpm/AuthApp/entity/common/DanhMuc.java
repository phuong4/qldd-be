package com.evnit.ttpm.AuthApp.entity.common;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class DanhMuc {
	@Id
	String id;
	String name;
	String value;
	Integer stt;
	
	
	
	
	public DanhMuc(String id, String name, String value, Integer stt) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.stt = stt;
	}

	public DanhMuc() {

	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getStt() {
		return stt;
	}
	public void setStt(Integer stt) {
		this.stt = stt;
	}
}

package com.evnit.ttpm.AuthApp.model.request.common;

public class DonViGiaoNhanRequest {
	private int id;
	private String name;
	private String description;
	private boolean xNK;
	
	public DonViGiaoNhanRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isxNK() {
		return xNK;
	}
	public void setxNK(boolean xNK) {
		this.xNK = xNK;
	}
}

package com.evnit.ttpm.AuthApp.entity.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDataReport {
	private String state;
	private String message;
	private Object data;
	private Object dataFile;
	private Object dataView;

	public enum STATE {
		OK, FAIL
	}

	public enum MESSAGE {
		SUCCESS, ERROR
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getDataFile() {
		return dataFile;
	}

	public void setDataFile(Object dataFile) {
		this.dataFile = dataFile;
	}

	public Object getDataView() {
		return dataView;
	}

	public void setDataView(Object dataView) {
		this.dataView = dataView;
	}
	
	
}

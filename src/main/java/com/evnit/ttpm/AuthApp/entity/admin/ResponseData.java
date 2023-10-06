package com.evnit.ttpm.AuthApp.entity.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
	@JsonProperty("STATE")
	private String state;
	@JsonProperty("MESSAGE")
	private String message;
	@JsonProperty("DATA")
	private Object data;

	
	public ResponseData() {
		super();
	}

	public ResponseData(String state, String message) {
		this.state = state;
		this.message = message;
	}

	public enum STATE {
		OK, FAIL, WARNING
	}

	public enum MESSAGE {
		SUCCESS, ERROR, WARNING
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
	
	
}

package com.evnit.ttpm.AuthApp.model.request.attrgroup;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "View Report Request", description = "Thông tin giao nhận điện năng")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttrView {

	String id;
	String name;
	String type;
	String datatype;
	String defaultvalue;
	Object value;
	Object options;
	Boolean allownull;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getDefaultvalue() {
		return defaultvalue;
	}
	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Object getOptions() {
		return options;
	}
	public void setOptions(Object options) {
		this.options = options;
	}
	public Boolean getAllownull() {
		return allownull;
	}
	public void setAllownull(Boolean allownull) {
		this.allownull = allownull;
	}

	
}

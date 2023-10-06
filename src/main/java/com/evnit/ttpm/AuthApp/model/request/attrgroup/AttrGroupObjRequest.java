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
public class AttrGroupObjRequest {

	String objID;
	String objTypeID;
	public String getObjID() {
		return objID;
	}
	public void setObjID(String objID) {
		this.objID = objID;
	}
	public String getObjTypeID() {
		return objTypeID;
	}
	public void setObjTypeID(String objTypeID) {
		this.objTypeID = objTypeID;
	}
	
	

}

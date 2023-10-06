package com.evnit.ttpm.AuthApp.model.request.vanhanh;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GSTTDiemDoCreateRequest {

	private String maGstt;
	private String maDiemdo;
	public String getMaGstt() {
		return maGstt;
	}
	public void setMaGstt(String maGstt) {
		this.maGstt = maGstt;
	}
	public String getMaDiemdo() {
		return maDiemdo;
	}
	public void setMaDiemdo(String maDiemdo) {
		this.maDiemdo = maDiemdo;
	}
	
	

}

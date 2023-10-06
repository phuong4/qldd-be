package com.evnit.ttpm.AuthApp.model.request.vanhanh;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VanHanhTraCuuDuLieuRequest {

	private String madiemdo;
	private Date tungay;
	private Date denngay;



}

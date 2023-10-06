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
public class NhatKyDongBoRequest {

	private Date ngay;
	private String orgid;



}

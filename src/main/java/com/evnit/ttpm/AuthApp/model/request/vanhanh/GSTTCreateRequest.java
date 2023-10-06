package com.evnit.ttpm.AuthApp.model.request.vanhanh;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GSTTCreateRequest {

	private String maGstt;
	private String temGstt;
	private String moTa;
	private boolean trangThai;
	private BigInteger stt;

}

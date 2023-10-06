package com.evnit.ttpm.AuthApp.model.request.xacnhan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class XacNhanGNDNThangCreateRequest {

	private String ma_gn;
	private BigInteger thang;
	private BigInteger nam;
	private BigInteger status;
	private String moTa;
}

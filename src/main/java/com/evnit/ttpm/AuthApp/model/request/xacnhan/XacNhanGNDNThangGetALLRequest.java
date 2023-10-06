package com.evnit.ttpm.AuthApp.model.request.xacnhan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class XacNhanGNDNThangGetALLRequest {

	private String ma_gn;
	private int thang;
	private int nam;
}

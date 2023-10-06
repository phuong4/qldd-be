package com.evnit.ttpm.AuthApp.model.request.xacnhan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class XacNhanGNDNNgayGetALLRequest {

	private String ma_gn;
	private Date ngay;
}

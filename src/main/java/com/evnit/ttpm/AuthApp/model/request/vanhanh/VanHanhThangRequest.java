package com.evnit.ttpm.AuthApp.model.request.vanhanh;

import io.micrometer.core.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VanHanhThangRequest {

	@Nullable
	private String maGstt;

	@Nullable
	private String orgid;

	private int thang;
	private int nam;



}

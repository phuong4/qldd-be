package com.evnit.ttpm.AuthApp.model.request.giaonhan;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@ApiModel(value = "GNDN Create Request", description = "Thông tin giao nhận điện năng")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiaoNhanPhuongThucUpdateRequest {

	private String maGn;
	private String maPthuc;
	private BigInteger stt;
	private String moTa;
	// private boolean trangThai;
	// private String userCrId;
	// private Date userCrDtime;
	// private String userMdfId;
	// private Date userMdfDtime;
	// Thể hiện chiều giao nhận khi đứng tại đơn vị mình
	private boolean daoChieu;
	public String getMaGn() {
		return maGn;
	}
	public void setMaGn(String maGn) {
		this.maGn = maGn;
	}
	public String getMaPthuc() {
		return maPthuc;
	}
	public void setMaPthuc(String maPthuc) {
		this.maPthuc = maPthuc;
	}
	public BigInteger getStt() {
		return stt;
	}
	public void setStt(BigInteger stt) {
		this.stt = stt;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public boolean isDaoChieu() {
		return daoChieu;
	}
	public void setDaoChieu(boolean daoChieu) {
		this.daoChieu = daoChieu;
	}
	
	
}

package com.evnit.ttpm.AuthApp.model.request.giaonhan;



public class GiaoNhanPhuongThucAttrKeyRequest {
	private String maGn;
	private String maPthuc;
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
	public GiaoNhanPhuongThucAttrKeyRequest(String maGn, String maPthuc) {
		super();
		this.maGn = maGn;
		this.maPthuc = maPthuc;
	}
	
	
	
	
}

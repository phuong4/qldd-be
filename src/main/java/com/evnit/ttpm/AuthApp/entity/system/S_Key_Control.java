package com.evnit.ttpm.AuthApp.entity.system;

import javax.persistence.*;

@Entity
@Table(name = "S_KEY_CONTROL", uniqueConstraints = {})
public class S_Key_Control {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "SKEY")
	private String skey;
	@Column(name = "HAVEDBID")
	private Boolean havedbid;
	@Column(name = "PREFIX")
	private String prefix;
	@Column(name = "SVALUE")
	private String svalue;
	@Column(name = "PRAM1")
	private String pram1;
	@Column(name = "SDESC")
	private String sdesc;

	public S_Key_Control() {
	}

	public S_Key_Control(String skey) {
		this.skey = skey;
	}

	public String getSkey() {
		return skey;
	}

	public void setSkey(String skey) {
		this.skey = skey;
	}

	public Boolean getHavedbid() {
		return havedbid;
	}

	public void setHavedbid(Boolean havedbid) {
		this.havedbid = havedbid;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSvalue() {
		return svalue;
	}

	public void setSvalue(String svalue) {
		this.svalue = svalue;
	}

	public String getPram1() {
		return pram1;
	}

	public void setPram1(String pram1) {
		this.pram1 = pram1;
	}

	public String getSdesc() {
		return sdesc;
	}

	public void setSdesc(String sdesc) {
		this.sdesc = sdesc;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (skey != null ? skey.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof S_Key_Control)) {
			return false;
		}
		S_Key_Control other = (S_Key_Control) object;
		return (this.skey != null || other.skey == null) && (this.skey == null || this.skey.equals(other.skey));
	}

	@Override
	public String toString() {
		return "Shared.Entity.S_Key_Control[skey=" + skey + "]";
	}

}

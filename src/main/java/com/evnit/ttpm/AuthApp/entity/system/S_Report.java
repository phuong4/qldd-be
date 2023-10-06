package com.evnit.ttpm.AuthApp.entity.system;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "S_REPORT")
public class S_Report {
	@Id
	@Basic(optional = false)
	@Column(name = "RPTID")
	private String rptid;
	@Column(name = "RPTDESC")
	private String rptdesc;
	@Column(name = "NOTE")
	private String note;
	@Column(name = "RPTORD")
	private Integer rptord;
	@Column(name = "ENABLE")
	private boolean enable;
	@Column(name = "RPTGROUPID")
	private String rptgroupid;
	@Column(name = "USER_CR_ID")
	private String userCrId;
	@Column(name = "USER_CR_DTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date userCrDtime;
	@Column(name = "USER_MDF_ID")
	private String userMdfId;
	@Column(name = "USER_MDF_DTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date userMdfDtime;

	public String getRptid() {
		return rptid;
	}

	public void setRptid(String rptid) {
		this.rptid = rptid;
	}

	public String getRptdesc() {
		return rptdesc;
	}

	public void setRptdesc(String rptdesc) {
		this.rptdesc = rptdesc;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getRptord() {
		return rptord;
	}

	public void setRptord(Integer rptord) {
		this.rptord = rptord;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getRptgroupid() {
		return rptgroupid;
	}

	public void setRptgroupid(String rptgroupid) {
		this.rptgroupid = rptgroupid;
	}

	public String getUserCrId() {
		return userCrId;
	}

	public void setUserCrId(String userCrId) {
		this.userCrId = userCrId;
	}

	public Date getUserCrDtime() {
		return userCrDtime;
	}

	public void setUserCrDtime(Date userCrDtime) {
		this.userCrDtime = userCrDtime;
	}

	public String getUserMdfId() {
		return userMdfId;
	}

	public void setUserMdfId(String userMdfId) {
		this.userMdfId = userMdfId;
	}

	public Date getUserMdfDtime() {
		return userMdfDtime;
	}

	public void setUserMdfDtime(Date userMdfDtime) {
		this.userMdfDtime = userMdfDtime;
	}
}

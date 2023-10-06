package com.evnit.ttpm.AuthApp.entity.system;

import javax.persistence.*;
import java.util.Date;



@Entity(name = "S_REPORT_FORM")
public class S_Report_Form {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "RPTFORMID")
	private String rptformid;
	@Column(name = "RPTFORMDESC")
	private String rptformdesc;
	@Column(name = "ENABLE")
	private boolean enable;
	@Column(name = "DEFAULTFORM")
	private boolean defaultform;
	@Column(name = "RPTFORMORD")
	private Integer rptformord;
	@Column(name = "FILENAME")
	private String filename;

	@Column(name = "NOTE")
	private String note;
	@Column(name = "RPTID")
	private String rptid;

	@Lob
	@Column(name = "FILEDATA")
	private byte[] filedata;

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
	public String getRptformid() {
		return rptformid;
	}
	public void setRptformid(String rptformid) {
		this.rptformid = rptformid;
	}
	public String getRptformdesc() {
		return rptformdesc;
	}
	public void setRptformdesc(String rptformdesc) {
		this.rptformdesc = rptformdesc;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public boolean isDefaultform() {
		return defaultform;
	}
	public void setDefaultform(boolean defaultform) {
		this.defaultform = defaultform;
	}
	public Integer getRptformord() {
		return rptformord;
	}
	public void setRptformord(Integer rptformord) {
		this.rptformord = rptformord;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getRptid() {
		return rptid;
	}
	public void setRptid(String rptid) {
		this.rptid = rptid;
	}
	public byte[] getFiledata() {
		return filedata;
	}
	public void setFiledata(byte[] filedata) {
		this.filedata = filedata;
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

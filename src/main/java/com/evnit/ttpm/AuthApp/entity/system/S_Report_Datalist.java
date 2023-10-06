package com.evnit.ttpm.AuthApp.entity.system;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "S_REPORT_DATALIST")
public class S_Report_Datalist {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "DATALISTID")
	private String datalistid;
	@Column(name = "DATALISTDESC")
	private String datalistdesc;
	@Column(name = "DATALISTORD")
	private Integer datalistord;
	@Column(name = "COMMANDTYPEID")
	private String commandtypeid;
	@Column(name = "QUERY")
	private String query;
	@Column(name = "ENABLE")
	private boolean enable;
	@Column(name = "RPTID")
	private String rptid;

	@Column(name = "LOADTYPE")
	private boolean loadtype;

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
	public String getDatalistid() {
		return datalistid;
	}
	public void setDatalistid(String datalistid) {
		this.datalistid = datalistid;
	}
	public String getDatalistdesc() {
		return datalistdesc;
	}
	public void setDatalistdesc(String datalistdesc) {
		this.datalistdesc = datalistdesc;
	}
	public Integer getDatalistord() {
		return datalistord;
	}
	public void setDatalistord(Integer datalistord) {
		this.datalistord = datalistord;
	}
	public String getCommandtypeid() {
		return commandtypeid;
	}
	public void setCommandtypeid(String commandtypeid) {
		this.commandtypeid = commandtypeid;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getRptid() {
		return rptid;
	}
	public void setRptid(String rptid) {
		this.rptid = rptid;
	}
	public boolean isLoadtype() {
		return loadtype;
	}
	public void setLoadtype(boolean loadtype) {
		this.loadtype = loadtype;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}

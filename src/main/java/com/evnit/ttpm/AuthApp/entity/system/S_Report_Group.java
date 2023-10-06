package com.evnit.ttpm.AuthApp.entity.system;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "S_REPORT_GROUP")
public class S_Report_Group {
	@Id
	@Basic(optional = false)
	@Column(name = "RPTGROUPID")
	private String rptgroupid;

	@Column(name = "RPTGROUPID_PARENT")
	private String rptgroupid_parent;

	@Column(name = "RPTGROUPDESC")
	private String rptgroupdesc;
	@Column(name = "RPTGROUPORD")
	private Integer rptgroupord;
	@Column(name = "NOTE")
	private String note;
	@Column(name = "ORGID")
	private String orgid;
	@Column(name = "ENABLE")
	private Boolean enable;
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

	@Column(name = "URL")
	private String url;
	@Column(name = "GLEVEL")
	private Integer glevel;

	@Transient
	List<S_Report_Group> listChild;

	public String getRptgroupid() {
		return rptgroupid;
	}

	public void setRptgroupid(String rptgroupid) {
		this.rptgroupid = rptgroupid;
	}

	public String getRptgroupid_parent() {
		return rptgroupid_parent;
	}

	public void setRptgroupid_parent(String rptgroupid_parent) {
		this.rptgroupid_parent = rptgroupid_parent;
	}

	public String getRptgroupdesc() {
		return rptgroupdesc;
	}

	public void setRptgroupdesc(String rptgroupdesc) {
		this.rptgroupdesc = rptgroupdesc;
	}

	public Integer getRptgroupord() {
		return rptgroupord;
	}

	public void setRptgroupord(Integer rptgroupord) {
		this.rptgroupord = rptgroupord;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getGlevel() {
		return glevel;
	}

	public void setGlevel(Integer glevel) {
		this.glevel = glevel;
	}

	public List<S_Report_Group> getListChild() {
		return listChild;
	}

	public void setListChild(List<S_Report_Group> listChild) {
		this.listChild = listChild;
	}
	
	
}

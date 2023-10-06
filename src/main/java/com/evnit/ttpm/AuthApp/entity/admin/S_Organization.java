package com.evnit.ttpm.AuthApp.entity.admin;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "S_ORGANIZATION")
public class S_Organization {
	@Id
	@Basic(optional = false)
	@Column(name = "ORGID")
	private String orgid;
	@Basic(optional = false)
	@Column(name = "ORGDESC")
	private String orgdesc;
	@Column(name = "ORGID_PARENT")
	private String orgidParent;
	@Column(name = "ORGORD")
	private String orgord;

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgdesc() {
		return orgdesc;
	}

	public void setOrgdesc(String orgdesc) {
		this.orgdesc = orgdesc;
	}

	public String getOrgidParent() {
		return orgidParent;
	}

	public void setOrgidParent(String orgidParent) {
		this.orgidParent = orgidParent;
	}

	public String getOrgord() {
		return orgord;
	}

	public void setOrgord(String orgord) {
		this.orgord = orgord;
	}

}

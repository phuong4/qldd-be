package com.evnit.ttpm.AuthApp.entity.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;


@Entity(name = "Q_FUNCTION")
public class Function {
	@Id
	@Column(name = "FUNCTIONID")
	private String functionid;
	@Column(name = "FUNCTIONNAME")
	private String functionname;
	@Column(name = "FUNCTIONPARENTID")
	private String functionparentid;
//    @Column(name = "FUNCTIONORD")
//    private int functionord;
	@Column(name = "IS_LAST")
	private Boolean islast;
	@Column(name = "ENABLE")
	private boolean enable;
	@Column(name = "HAVING_UPDATE")
	private Boolean havingupdate;
	@Column(name = "URL")
	private String url;
	@Column(name = "URL_MOBILE")
	private String urlMobile;
	@Column(name = "ICON")
	private String icon;
	@Column(name = "ISAPP")
	private Boolean isapp;
	@Column(name = "ISPUPLIC")
	private Boolean isPUPLIC;
	@Column(name = "ISLOGIN")
	private Boolean isLogin;
	@Column(name = "ISMENU")
	private Boolean isMenu;
	@Column(name = "ISMOBILE")
	private Boolean isMobile;

	// @JoinColumn(name = "FUNCTION_PARENT_ID", referencedColumnName = "FUNCTIONID",
	// insertable = false, updatable = false)
	// @ManyToOne(optional = false)
	// private Function sFuncidParent;
	@Transient
	List<Function> listChild;

	public String getFunctionid() {
		return functionid;
	}

	public void setFunctionid(String functionid) {
		this.functionid = functionid;
	}

	public String getFunctionname() {
		return functionname;
	}

	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}

	public String getFunctionparentid() {
		return functionparentid;
	}

	public void setFunctionparentid(String functionparentid) {
		this.functionparentid = functionparentid;
	}

	public Boolean getIslast() {
		return islast;
	}

	public void setIslast(Boolean islast) {
		this.islast = islast;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Boolean getHavingupdate() {
		return havingupdate;
	}

	public void setHavingupdate(Boolean havingupdate) {
		this.havingupdate = havingupdate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlMobile() {
		return urlMobile;
	}

	public void setUrlMobile(String urlMobile) {
		this.urlMobile = urlMobile;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getIsapp() {
		return isapp;
	}

	public void setIsapp(Boolean isapp) {
		this.isapp = isapp;
	}

	public Boolean getIsPUPLIC() {
		return isPUPLIC;
	}

	public void setIsPUPLIC(Boolean isPUPLIC) {
		this.isPUPLIC = isPUPLIC;
	}

	public Boolean getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}

	public Boolean getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Boolean isMenu) {
		this.isMenu = isMenu;
	}

	public Boolean getIsMobile() {
		return isMobile;
	}

	public void setIsMobile(Boolean isMobile) {
		this.isMobile = isMobile;
	}

	public List<Function> getListChild() {
		return listChild;
	}

	public void setListChild(List<Function> listChild) {
		this.listChild = listChild;
	}
	
	
	
}

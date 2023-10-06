package com.evnit.ttpm.AuthApp.entity.attr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "S_ATTRIBUTE", uniqueConstraints = {})
public class S_Attribute {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "ATTRID")
	private String attrid;
	@Basic(optional = false)
	@Column(name = "ATTRDESC")
	private String attrdesc;
	@Column(name = "ATTRORD")
	private Integer attrord;
	@Column(name = "COLNAME")
	private String colname;
	@Column(name = "COLLENGTH")
	private Integer collength;
	@Column(name = "COLPRECISION")
	private Integer colprecision;
	@Column(name = "COLSCALE")
	private Integer colscale;
	@Column(name = "COLLALLOWNULL")
	private Boolean collallownull;
	@Column(name = "COLDEFAULT")
	private String coldefault;
	@Column(name = "ENABLE")
	private Boolean enable;
	@Column(name = "ASSETVIEW")
	private Boolean assetview;

	@Column(name = "SIGN")
	private String sign;
	@Column(name = "ATTRID_PARENT")
	private String attridParent;
	@Column(name = "DATAQUERYLST")
	private String dataquerylst;
	@Column(name = "DATAQUERYONE")
	private String dataqueryone;
	@Column(name = "DATAFIELDVALUE")
	private Integer datafieldvalue;
	@Column(name = "DATAFIELDDESC")
	private Integer datafielddesc;
	@Column(name = "ALLOWHISTORY")
	private Boolean allowhistory;
	@Column(name = "DATAHEADERLST")
	private String dataheaderlst;

	@Column(name = "COLDATATYPEID")
	private String coldatatypeid;
	@Column(name = "ATTRGROUPID")
	private String attrgroupid;
	@Column(name = "UOMID")
	private String uomid;

	@Column(name = "ATTTYPEID")
	private String atttypeid;
	@Column(name = "ATTVALUES")
	private String attvalues;
	@Column(name = "ATTRNOTE")
	private String attrnote;

	@Column(name = "ASSETSIMILAR")
	private String assetsimilar;

	@Column(name = "COLHEIGHT")
	private Integer colheight;

	@Column(name = "CTRL_TYPE")
	private String ctrlType;

	public String getAttrid() {
		return attrid;
	}

	public void setAttrid(String attrid) {
		this.attrid = attrid;
	}

	public String getAttrdesc() {
		return attrdesc;
	}

	public void setAttrdesc(String attrdesc) {
		this.attrdesc = attrdesc;
	}

	public Integer getAttrord() {
		return attrord;
	}

	public void setAttrord(Integer attrord) {
		this.attrord = attrord;
	}

	public String getColname() {
		return colname;
	}

	public void setColname(String colname) {
		this.colname = colname;
	}

	public Integer getCollength() {
		return collength;
	}

	public void setCollength(Integer collength) {
		this.collength = collength;
	}

	public Integer getColprecision() {
		return colprecision;
	}

	public void setColprecision(Integer colprecision) {
		this.colprecision = colprecision;
	}

	public Integer getColscale() {
		return colscale;
	}

	public void setColscale(Integer colscale) {
		this.colscale = colscale;
	}

	public Boolean getCollallownull() {
		return collallownull;
	}

	public void setCollallownull(Boolean collallownull) {
		this.collallownull = collallownull;
	}

	public String getColdefault() {
		return coldefault;
	}

	public void setColdefault(String coldefault) {
		this.coldefault = coldefault;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Boolean getAssetview() {
		return assetview;
	}

	public void setAssetview(Boolean assetview) {
		this.assetview = assetview;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAttridParent() {
		return attridParent;
	}

	public void setAttridParent(String attridParent) {
		this.attridParent = attridParent;
	}

	public String getDataquerylst() {
		return dataquerylst;
	}

	public void setDataquerylst(String dataquerylst) {
		this.dataquerylst = dataquerylst;
	}

	public String getDataqueryone() {
		return dataqueryone;
	}

	public void setDataqueryone(String dataqueryone) {
		this.dataqueryone = dataqueryone;
	}

	public Integer getDatafieldvalue() {
		return datafieldvalue;
	}

	public void setDatafieldvalue(Integer datafieldvalue) {
		this.datafieldvalue = datafieldvalue;
	}

	public Integer getDatafielddesc() {
		return datafielddesc;
	}

	public void setDatafielddesc(Integer datafielddesc) {
		this.datafielddesc = datafielddesc;
	}

	public Boolean getAllowhistory() {
		return allowhistory;
	}

	public void setAllowhistory(Boolean allowhistory) {
		this.allowhistory = allowhistory;
	}

	public String getDataheaderlst() {
		return dataheaderlst;
	}

	public void setDataheaderlst(String dataheaderlst) {
		this.dataheaderlst = dataheaderlst;
	}

	public String getColdatatypeid() {
		return coldatatypeid;
	}

	public void setColdatatypeid(String coldatatypeid) {
		this.coldatatypeid = coldatatypeid;
	}

	public String getAttrgroupid() {
		return attrgroupid;
	}

	public void setAttrgroupid(String attrgroupid) {
		this.attrgroupid = attrgroupid;
	}

	public String getUomid() {
		return uomid;
	}

	public void setUomid(String uomid) {
		this.uomid = uomid;
	}

	public String getAtttypeid() {
		return atttypeid;
	}

	public void setAtttypeid(String atttypeid) {
		this.atttypeid = atttypeid;
	}

	public String getAttvalues() {
		return attvalues;
	}

	public void setAttvalues(String attvalues) {
		this.attvalues = attvalues;
	}

	public String getAttrnote() {
		return attrnote;
	}

	public void setAttrnote(String attrnote) {
		this.attrnote = attrnote;
	}

	public String getAssetsimilar() {
		return assetsimilar;
	}

	public void setAssetsimilar(String assetsimilar) {
		this.assetsimilar = assetsimilar;
	}

	public Integer getColheight() {
		return colheight;
	}

	public void setColheight(Integer colheight) {
		this.colheight = colheight;
	}

	public String getCtrlType() {
		return ctrlType;
	}

	public void setCtrlType(String ctrlType) {
		this.ctrlType = ctrlType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	
}

package com.evnit.ttpm.AuthApp.entity.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "View_Deal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class View_Deal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Size(min = 1)
	@JsonProperty("Id")
	@Column(name = "ID")
	private Integer Id;
	@Size(max = 250)
	@JsonProperty("Type")
	@Column(name = "TYPE")
	private Integer Type;
	@Size(max = 250)
	@JsonProperty("TbaId")
	@Column(name = "TBAID")
	private String TbaId;
	@JsonProperty("CheckNQL")
	@Column(name = "CHECKNQL")
	private boolean CheckNQL;
	@JsonProperty("CheckLD")
	@Column(name = "CHECKLD")
	private boolean CheckLD;

	@Size(max = 1000000)
	@JsonProperty("TbaStatus")
	@Column(name = "TBASTATUS")
	private Integer TbaStatus;
	@Size(max = 1000000)
	@JsonProperty("TypeNMD")
	@Column(name = "TYPENMD")
	private String TypeNMD;

	@JsonProperty("TypeNMDID")
	@Column(name = "TYPENMDID")
	private String TypeNMDid;

	@Size(max = 250)
	@JsonProperty("TinhTp")
	@Column(name = "TINHTP")
	private String TinhTp;

	@JsonProperty("TbaName")
	@Column(name = "TBANAME")
	private String TbaName;
	@Size(max = 250)
	@JsonProperty("TbaStatusName")
	@Column(name = "TBASTATUSNAME")
	private String TbaStatusName;
	@Size(max = 250)
	@JsonProperty("XnkName")
	@Column(name = "XNKNAME")
	private Integer XnkName;
	@Size(max = 250)
	@JsonProperty("NumDoc")
	@Column(name = "NUMDOC")
	private String NumDoc;
	@Size(max = 250)
	@JsonProperty("EndDateDoc")
	@Column(name = "ENDDATEDOC")
	private Date EndDateDoc;
	@Size(max = 250)
	@JsonProperty("TypeDeal")
	@Column(name = "TYPEDEAL")
	private String TypeDeal;
	@Size(max = 250)
	@JsonProperty("FromDate")
	@Column(name = "FROMDATE")
	private Date FromDate;
	@Size(max = 250)
	@JsonProperty("ENDDATE")
	@Column(name = "ENDDATE")
	private Date EndDate;
	@Size(max = 250)
	@JsonProperty("Factor")
	@Column(name = "FACTOR")
	private Float Factor;
	@Size(max = 250)
	@JsonProperty("ValidBase")
	@Column(name = "VALIDBASE")
	private String ValidBase;

	@Size(max = 250)
	@JsonProperty("FormSynPower")
	@Column(name = "FORMSYNPOWER")
	private String FormSynPower;
	@Size(max = 250)
	@JsonProperty("DealContent")
	@Column(name = "DEALCONTENT")
	private String DealContent;
	@Size(max = 250)
	@JsonProperty("FromDateSL")
	@Column(name = "FROMDATESL")
	private Date FromDateSL;
	@Size(max = 250)
	@JsonProperty("EndDateSL")
	@Column(name = "ENDDATESL")
	private Date EndDateSL;
	@JsonProperty("CheckTick")
	@Column(name = "CHECKTICK")
	private boolean CheckTick;
	@Size(max = 250)
	@JsonProperty("DEALID")
	@Column(name = "DEALID")
	private String DEALID;
	@Size(max = 250)
	@JsonProperty("TypeDealCode")
	@Column(name = "TYPEDEALCODE")
	private String TypeDealCode;
	@JsonProperty("FormSynPowerInt")
	@Column(name = "FORMSYNPOWERINT")
	private Integer FormSynPowerInt;
	@JsonProperty("Note")
	@Column(name = "NOTE")
	private String Note;
//	@JsonProperty("FileData")
//	@Column(name = "FILEDATA")
//	private String FileData;
	@JsonProperty("TypeName")
	@Column(name = "TYPENAME")
	private String TypeName;
	@JsonProperty("Unit")
	@Column(name = "UNIT")
	private String Unit;
}

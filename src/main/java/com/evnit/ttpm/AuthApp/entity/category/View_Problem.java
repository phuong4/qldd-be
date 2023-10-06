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

@Table(name = "View_Problem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class View_Problem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
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
	@JsonProperty("TbaName")
	@Column(name = "TBANAME")
	private String TbaName;
	@Size(max = 250)
	@JsonProperty("TbaStatusName")
	@Column(name = "TBASTATUSNAME")
	private String TbaStatusName;
	@Size(max = 250)
	@JsonProperty("FromDate")
	@Column(name = "FROMDATE")
	private Date FromDate;
	@Size(max = 250)
	@JsonProperty("ENDDATE")
	@Column(name = "ENDDATE")
	private Date EndDate;
	@Size(max = 250)
	@JsonProperty("TypeObjId")
	@Column(name = "TYPEOBJID")
	private String TypeObjId;
	@Size(max = 250)
	@JsonProperty("Cause")
	@Column(name = "CAUSE")
	private String Cause;
	@Size(max = 250)
	@JsonProperty("Content")
	@Column(name = "CONTENT")
	private String Content;
	@Size(max = 250)
	@JsonProperty("Detail")
	@Column(name = "DETAIL")
	private String Detail;
	@Size(max = 250)
	@JsonProperty("Status")
	@Column(name = "STATUS")
	private Integer Status;
	@Size(max = 250)
	@JsonProperty("StatusName")
	@Column(name = "STATUSNAME")
	private String StatusName;
	@Size(max = 250)
	@JsonProperty("Result")
	@Column(name = "RESULT")
	private String Result;
	@Size(max = 250)
	@JsonProperty("Consequence")
	@Column(name = "CONSEQUENCE")
	private String Consequence;
	@Size(max = 250)
	@JsonProperty("Remedies")
	@Column(name = "REMEDIES")
	private String Remedies;
	@Size(max = 250)
	@JsonProperty("TypeName")
	@Column(name = "TYPENAME")
	private String TypeName;
	@Size(max = 250)
	@JsonProperty("KeyId")
	@Column(name = "KEYID")
	private String KeyId;
	@JsonProperty("Count")
	@Column(name = "COUNT")
	private Integer Count;
	@JsonProperty("IdType")
	@Column(name = "IDTYPE")
	private String IdType;
//	@JsonProperty("FILEDATA")
//	@Column(name = "FILEDATA")
//	private String FILEDATA;


	@JsonProperty("DetailCT")
	@Column(name = "DETAILCT")
	private String DetailCT;
	@JsonProperty("DetailDD")
	@Column(name = "DETAILDD")
	private String DetailDD;
	@JsonProperty("DetailTU")
	@Column(name = "DETAILTU")
	private String DetailTU;
	@JsonProperty("DetailTI")
	@Column(name = "DETAILTI")
	private String DetailTI;

	@Size(max = 250)
	@JsonProperty("IdTypeCT")
	@Column(name = "IDTYPECT")
	private String IdTypeCT;

	@Size(max = 250)
	@JsonProperty("IdTypeDD")
	@Column(name = "IDTYPEDD")
	private String IdTypeDD;

	@Size(max = 250)
	@JsonProperty("IdTypeTU")
	@Column(name = "IDTYPETU")
	private String IdTypeTU;

	@Size(max = 250)
	@JsonProperty("IdTypeTI")
	@Column(name = "IDTYPETI")
	private String IdTypeTI;


	@Size(max = 250)
	@JsonProperty("TypeObjIdCT")
	@Column(name = "TYPEOBJIDCT")
	private String TypeObjIdCT ;

	@Size(max = 250)
	@JsonProperty("TypeObjIdDD")
	@Column(name = "TYPEOBJIDDD")
	private String TypeObjIdDD;

	@Size(max = 250)
	@JsonProperty("TypeObjIdTU")
	@Column(name = "TYPEOBJIDTU")
	private String TypeObjIdTU;

	@Size(max = 250)
	@JsonProperty("TypeObjIdTI")
	@Column(name = "TYPEOBJIDTI")
	private String TypeObjIdTI;

	@Size(max = 250)
	@JsonProperty("CategoryId")
	@Column(name = "CATEGORYID")
	private String CategoryId;
}

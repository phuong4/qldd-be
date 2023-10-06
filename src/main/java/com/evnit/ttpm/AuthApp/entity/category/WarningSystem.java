package com.evnit.ttpm.AuthApp.entity.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Table(name = "WARNINGSYSTEM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class WarningSystem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@JsonProperty("Id")
	@Column(name = "ID")
	private Long Id;
	@Size(max = 250)
	@JsonProperty("Type")
	@Column(name = "TYPE")
	private int Type;
	@Size(max = 250)
	@JsonProperty("IDDIEMDO")
	@Column(name = "IDDIEMDO")
	private String idDiemDo;
	@JsonProperty("TbaName")
	@Column(name = "TBANAME")
	private String TbaName;
	@JsonProperty("Nature")
	@Column(name = "NATURE")
	private String Nature;
	@JsonProperty("NameDD")
	@Column(name = "NAMEDD")
	private String NameDD;
	@JsonProperty("S")
	@Column(name = "S")
	private Double S;
	@JsonProperty("SPlus")
	@Column(name = "SPlus")
	private Double SPlus;
	@JsonProperty("SMinus")
	@Column(name = "SMinus")
	private Double SMinus;

	@JsonProperty("KD_Detail_CT")
	@Column(name = "KD_DETAIL_CT")
	private String kdDetailCT;
	@JsonProperty("KD_Detail_CU")
	@Column(name = "KD_DETAIL_TU")
	private String kdDetailTU;
	@JsonProperty("KD_Detail_TI")
	@Column(name = "KD_DETAIL_TI")
	private String kdDetailTI;

	@JsonProperty("KD_Date_CT")
	@Column(name = "KD_DATE_CT")
	private String kdDateCT;
	@JsonProperty("KD_Date_CU")
	@Column(name = "KD_DATE_TU")
	private String kdDateTU;
	@JsonProperty("KD_Date_TI")
	@Column(name = "KD_DATE_TI")
	private String kdDateTI;


	@JsonProperty("ID_BTU")
	@Column(name = "ID_BTU")
	private String idBtu;
	@JsonProperty("ID_BTI")
	@Column(name = "ID_BTI")
	private String idBti;

}

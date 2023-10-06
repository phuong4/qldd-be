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
import java.sql.Date;

@Table(name = "PLANACCREDITATION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class PlanAccreditation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@JsonProperty("Id")
	@Column(name = "ID")
	private Long id;
	@Size(max = 250)
	@JsonProperty("Type")
	@Column(name = "TYPE")
	private Integer type;
	@Size(max = 250)
	@JsonProperty("TbaId")
	@Column(name = "TBAID")
	private String tbaId;
	@JsonProperty("TbaName")
	@Column(name = "TBANAME")
	private String tbaName;
	@JsonProperty("DetailCT")
	@Column(name = "DETAILCT")
	private String detailCT;
	@JsonProperty("CountCT")
	@Column(name = "COUNTCT")
	private String countCT;
	@JsonProperty("DetailTU")
	@Column(name = "DETAILTU")
	private String detailTU;
	@JsonProperty("CountCT")
	@Column(name = "COUNTTU")
	private String countTU;
	@JsonProperty("DetailTI")
	@Column(name = "DETAILTI")
	private String detailTI;
	@JsonProperty("CountTI")
	@Column(name = "COUNTTI")
	private String countTI;
	@JsonProperty("YearTime")
	@Column(name = "YEARTIME")
	private String yearTime;
	@JsonProperty("MonthTime")
	@Column(name = "MONTHTIME")
	private Integer monthTime;
	@JsonProperty("MonthTimeShow")
	@Column(name = "MONTHTIMESHOW")
	private String monthTimeShow;

}

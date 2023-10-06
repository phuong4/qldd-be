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

@Table(name = "KDLDD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class KDLDD implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@JsonProperty("Id")
	@Column(name = "ID")
	private Long Id;
	@Size(max = 250)
	@JsonProperty("NameUnit")
	@Column(name = "NAMEUNIT")
	private String NameUnit;
	@Size(max = 250)
	@JsonProperty("NameDD")
	@Column(name = "NAMEDD")
	private String NameDD;
	@JsonProperty("CodeDD")
	@Column(name = "CODEDD")
	private String CodeDD;
	@JsonProperty("SeriCT")
	@Column(name = "SERICT")
	private String SeriCT;
	@JsonProperty("DateClosing")
	@Column(name = "DATECLOSING")
	private Date DateClosing;
	@JsonProperty("Expression1")
	@Column(name = "EXPRESSION1")
	private Double Expression1;
	@JsonProperty("Expression2")
	@Column(name = "EXPRESSION2")
	private Double Expression2;
	@JsonProperty("Expression3")
	@Column(name = "EXPRESSION3")
	private Double Expression3;
	@JsonProperty("InVain")
	@Column(name = "INVAIN")
	private Double InVain;
	@JsonProperty("ExpressionSum")
	@Column(name = "EXPRESSIONSUM")
	private Double ExpressionSum;
	@JsonProperty("ExpressionReceive1")
	@Column(name = "EXPRESSIONRECEIVE1")
	private Double ExpressionReceive1;


	@JsonProperty("ExpressionReceive2")
	@Column(name = "EXPRESSIONRECEIVE2")
	private Double ExpressionReceive2;
	@JsonProperty("ExpressionReceive3")
	@Column(name = "EXPRESSIONRECEIVE3")
	private Double ExpressionReceive3;
	@JsonProperty("InVainReceive")
	@Column(name = "INVAINRECEIVE")
	private Double InVainReceive;
	@JsonProperty("ExpressionSumReceive")
	@Column(name = "EXPRESSIONSUMRECEIVE")
	private Double ExpressionSumReceive;
	@JsonProperty("Multiplier")
	@Column(name = "MULTIPLIER")
	private Double Multiplier;
	@JsonProperty("LessOperation")
	@Column(name = "LESSOPERATION")
	private String LessOperation;
	@JsonProperty("ReadingStatus")
	@Column(name = "READINGSTATUS")
	private String ReadingStatus;
	@JsonProperty("UnitCCSL")
	@Column(name = "UNITCCSL")
	private String UnitCCSL;


	@JsonProperty("UnitType")
	@Column(name = "UNITTYPE")
	private Integer UnitType;
	@JsonProperty("UnitId")
	@Column(name = "UNITID")
	private Integer UnitId;
	@JsonProperty("IdDD")
	@Column(name = "IDDD")
	private Integer IdDD;
	@JsonProperty("SynDate")
	@Column(name = "SYNDATE")
	private String SynDate;
}

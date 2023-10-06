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
import java.util.Date;

@Table(name = "WARNINGDEVICE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class WarningDevice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@JsonProperty("Id")
	@Column(name = "ID")
	private String id;
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
	@JsonProperty("NameDD")
	@Column(name = "NAMEDD")
	private String nameDD;
	@JsonProperty("PHA")
	@Column(name = "PHA")
	private String pha;
	@JsonProperty("ValueErrorCT")
	@Column(name = "VALUEERRORCT")
	private Double valueErrorCT;
	@JsonProperty("ValueErrorCTYes")
	@Column(name = "VALUEERRORCTYES")
	private Double valueErrorCTYes;
	@JsonProperty("ErrorScoreTUTI")
	@Column(name = "ERRORSCORETUTI")
	private Double errorScoreTUTI;
	@JsonProperty("ErrorOriginTUTI")
	@Column(name = "ERRORORIGINTUTI")
	private Double errorOriginTUTI;
	@JsonProperty("ErrorScoreTUTIYes")
	@Column(name = "ERRORSCORETUTIYES")
	private Double errorScoreTUTIYes;
	@JsonProperty("ErrorOriginTUTIYes")
	@Column(name = "ERRORORIGINTUTIYES")
	private Double errorOriginTUTIYes;
	@JsonProperty("Month")
	@Column(name = "MONTH")
	private Integer month;
	@JsonProperty("MonthTimeShow")
	@Column(name = "MONTHTIMESHOW")
	private Integer monthTimeShow;
	@JsonProperty("KD_Date")
	@Column(name = "KD_DATE")
	private Date kdDate;

	@JsonProperty("CountCT")
	@Column(name = "COUNTCT")
	private Integer countCT;
	@JsonProperty("CountTU")
	@Column(name = "COUNTTU")
	private Integer countTU;
	@JsonProperty("CountTI")
	@Column(name = "COUNTTI")
	private Integer countTI;

	@JsonProperty("CongToAssetId")
	@Column(name = "CONGTOASSETID")
	private String congToAssetId;
	@JsonProperty("CongToAssetDESC")
	@Column(name = "CONGTOASSETDESC")
	private String congToAssetDESC;

	@JsonProperty("BTIAssetId")
	@Column(name = "BTIASSETID")
	private String btiAssetId;
	@JsonProperty("BTIAssetDesc")
	@Column(name = "BTIASSETDESC")
	private String btiAssetDesc;
	@JsonProperty("TIAssetId")
	@Column(name = "TIASSETID")
	private String tiAssetId;
	@JsonProperty("TIAssetDesc")
	@Column(name = "TIASSETDESC")
	private String tiAssetDesc;

	@JsonProperty("BTUAssetId")
	@Column(name = "BTUASSETID")
	private String btuAssetId;
	@JsonProperty("BTUAssetDesc")
	@Column(name = "BTUASSETDESC")
	private String btuAssetDesc;
	@JsonProperty("TUAssetId")
	@Column(name = "TUASSETID")
	private String tuAssetId;
	@JsonProperty("TUAssetDesc")
	@Column(name = "TUASSETDESC")
	private String tuAssetDesc;

	@JsonProperty("AssetId")
	@Column(name = "ASSETID")
	private String assetId;
	@JsonProperty("AssetDESC")
	@Column(name = "ASSETDESC")
	private String assetDesc;
	@JsonProperty("KD_Detail_Id")
	@Column(name = "KD_DETAIL_ID")
	private String kdDetailId;
}

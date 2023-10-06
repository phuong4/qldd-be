package com.evnit.ttpm.AuthApp.entity.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Table(name = "VIEW_ELECTRIC_FACTORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class ViewElectricFactory {
    @Id
    @Column(name = "ID")
    private String id;
    @JsonProperty("Type")
    @Column(name = "Type")
    private Integer type;

    @JsonProperty("Type_NM")
    @Column(name = "Type_NM")
    private String typeNM;

    @Size(max = 50)
    @JsonProperty("Ma_NM")
    @Column(name = "Ma_NM")
    private String maNM;

    @Size(max = 500)
    @JsonProperty("Ten_NM")
    @Column(name = "Ten_NM")
    private String tenNM;

    @JsonProperty("Dia_Chi")
    @Column(name = "Dia_Chi",columnDefinition = "NVARCHAR(MAX)")
    private String diaChi;

    @JsonProperty("City")
    @Column(name = "City")
    private Integer city;

    @JsonProperty("CityStr")
    @Column(name = "CITYSTR")
    private String cityStr;

    @JsonProperty("Regions")
    @Column(name = "Regions")
    private Integer regions;

    @JsonProperty("Ownership_Unit")
    @Column(name = "Ownership_Unit")
    private Integer ownershipUnit;

    @JsonProperty("Management_Unit")
    @Column(name = "Management_Unit")
    private Integer managementUnit;

    @JsonProperty("Cong_Suat_MW")
    @Column(name = "Cong_Suat_MW")
    private Integer congSuatMW;

    @JsonProperty("Cong_Suat_Mwp")
    @Column(name = "Cong_Suat_Mwp")
    private Integer congSuatMwp;

    @JsonProperty("Cong_Suat_Max_MW")
    @Column(name = "Cong_Suat_Max_MW")
    private Integer congSuatMaxMW;

    @JsonProperty("Dien_Ap_kV")
    @Column(name = "Dien_Ap_kV")
    private Integer dienApkV;

    @JsonProperty("San_Luong")
    @Column(name = "San_Luong")
    private Integer sanLuong;

    @JsonProperty("Date_COD")
    @Column(name = "Date_COD")
    private Date dateCOD;

    @JsonProperty("Date_IN")
    @Column(name = "Date_IN")
    private Date dateIN;

    @JsonProperty("Status")
    @Column(name = "Status")
    private Integer status;

    @JsonProperty("IPP")
    @Column(name = "IPP")
    private Boolean ipp;
    @JsonProperty("BOT")
    @Column(name = "BOT")
    private Boolean bot;
    @JsonProperty("DMT")
    @Column(name = "DMT")
    private Boolean dmt;
    @JsonProperty("BDD")
    @Column(name = "BDD")
    private Boolean bdd;
    @JsonProperty("XNK")
    @Column(name = "XNK")
    private Boolean xnk;
    @JsonProperty("TTD")
    @Column(name = "TTD")
    private Boolean ttd;
    @Column(name = "IS_DELETE")
    private Boolean is_Delete;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "City",insertable = false,updatable = false)
    private SCategoryTinhTP sCategoryTinhTP;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ownership_Unit",insertable = false,updatable = false)
    private SCategoryDonViSH sCategoryDonViSHByOwnership;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Management_Unit",insertable = false,updatable = false)
    private SCategoryDonViSH sCategoryDonViSHByManage;
}

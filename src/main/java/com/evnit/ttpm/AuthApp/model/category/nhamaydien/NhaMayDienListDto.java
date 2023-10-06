package com.evnit.ttpm.AuthApp.model.category.nhamaydien;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhaMayDienListDto {
	private String id;
	private Integer type;
	private String typeNM;
    private String maNM;
    private String tenNM;
    private Integer city;
    private String cityName;
    private Integer regions;
    private String regionsName;
    private String diaChi;
    private Integer ownershipUnit;
    private String ownershipUnitStr;
    private Integer managementUnit;
    private String manageUnitStr;
    private Integer status;
    private String statusStr;
    private Integer congSuatMW;
    private Integer congSuatMwp;
    private Integer congSuatMaxMW;
    private Integer dienApkV;
    private Integer sanLuong;
    private Date dateCOD;
    private String dateCODStr;
    private Date dateIN;
	private String dateINStr;
    private Boolean ipp;
    private Boolean bot;
    private Boolean dmt;
    private Boolean bdd;
    private Boolean xnk;
    private Boolean ttd;
    private Boolean is_Delete;
}

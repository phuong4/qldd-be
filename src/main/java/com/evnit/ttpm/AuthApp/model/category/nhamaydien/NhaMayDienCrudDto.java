package com.evnit.ttpm.AuthApp.model.category.nhamaydien;

import java.util.Date;
import java.util.List;

import com.evnit.ttpm.AuthApp.model.category.EngineUnit.EngineUnitCreateDto;
import com.evnit.ttpm.AuthApp.model.category.LicenseOperate.LicenseOperateCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class NhaMayDienCrudDto {
	private String id;
	private Integer type;
	private String typeNM;
    private String maNM;
    private String tenNM;
    private Integer city;
	private Integer regions;
    private String diaChi;
    private Integer ownershipUnit;
    private Integer managementUnit;
    private Integer status;
    private Integer congSuatMW;
    private Integer congSuatMwp;
    private Integer congSuatMaxMW;
    private Integer dienApkV;
    private Integer sanLuong;
    private Date dateCOD;
    private Date dateIN;
    private Boolean ipp;
    private Boolean bot;
    private Boolean dmt;
    private Boolean bdd;
    private Boolean xnk;
    private Boolean ttd;
    private Boolean is_Delete;
    private String userId;
    private String ownershipUnitStr;
    private String manageUnitStr;
    private String statusStr;
    private String typeNMStr;
    private String regionStr;
    private String dateInStr;
    private String dateCODStr;

    private List<LicenseOperateCreateDto> listCreateGP;
    private List<EngineUnitCreateDto> listCreateTM;
    private List<LicenseOperateCreateDto> listCreateGPOld;
    private List<EngineUnitCreateDto> listCreateTMOld;

}

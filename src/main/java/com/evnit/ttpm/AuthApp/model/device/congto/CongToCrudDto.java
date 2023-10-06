package com.evnit.ttpm.AuthApp.model.device.congto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CongToCrudDto {
    private String assetId;
    private String assetIdParent;
    private String assetRoot;
    private String assetIdChange;
    private String typeCreate;
    private Boolean checkReplace;
    private String assetDesc;
    private Integer assetORD;
    private String categoryId;
    private String typeId;
    private String useStatusLastId;
    private Date useStatusLastDTime;
    private String serialNum;
    private String pManufacturerId;
    private String nationalFact;
    private String pInstallDate;
    private Date inspectionStamps;
    private String userCRId;
    private Date userCRDTime;
    private String userMDFId;
    private Date userMDFDTime;
    private String orgId;
    private String uLevelId;
    private String assetNote;
    private Date dateOperation;
    private String assetIdLink;
    private Date dateAccreditation;
    private Double cycleAccreditation;
    private ZAGCongTo congTo;
    private Boolean isSave;
    private BigInteger transId;

    public String getpManufacturerId() {
        return pManufacturerId;
    }

    public void setpManufacturerId(String pManufacturerId) {
        this.pManufacturerId = pManufacturerId;
    }

    public String getpInstallDate() {
        return pInstallDate;
    }

    public void setpInstallDate(String pInstallDate) {
        this.pInstallDate = pInstallDate;
    }
}

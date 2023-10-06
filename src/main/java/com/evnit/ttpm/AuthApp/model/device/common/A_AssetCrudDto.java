package com.evnit.ttpm.AuthApp.model.device.common;

import lombok.Data;

import java.util.Date;
@Data
public class A_AssetCrudDto {
    private String assetId;
    private String assetIdParent;
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
    private String userCRId;
    private Date userCRDTime;
    private String userMDFId;
    private Date userMDFDTime;
    private String oRGId;
    private String uLevelId;
    private String assetNote;
    private Date dateOperation;
    private String assetIdLink;
    private Date dateAccreditation;
    private Double cycleAccreditation;
}

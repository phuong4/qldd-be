package com.evnit.ttpm.AuthApp.model.device.ti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TICrudDto {
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
    private String attrDataId;
    private String objTypeId;
    private String objId;
    private String pha;
    private String kieuTI;
    private String tinhChat;
}

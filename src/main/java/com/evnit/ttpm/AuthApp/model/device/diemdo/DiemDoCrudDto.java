package com.evnit.ttpm.AuthApp.model.device.diemdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiemDoCrudDto {
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
    private String ATTRDATAID;
    private String OBJTYPEID;
    private String OBJID;
    private Date NTHU_TINH;
    private Date NTHU_TAI;
    private Date NTHU_TTSL;
    private String TINH_CHAT1;
    private String TINH_CHAT2;
    private String DVI_GNHAN1;
    private String DVI_GNHAN2;
    private String DTAC_XNK;
    private String XNK;
}

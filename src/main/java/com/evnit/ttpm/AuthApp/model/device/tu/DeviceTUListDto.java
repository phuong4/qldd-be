package com.evnit.ttpm.AuthApp.model.device.tu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceTUListDto {
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
    private String orgId;
    private String uLevelId;
    private String assetNote;
    private Date dateOperation;
    private String assetIdLink;
    private Date dateAccreditation;
    private Date inspectionStamps;
    private Double cycleAccreditation;
    private String cycleAccreditationStr;
    private String attrDataId;
    private String objTypeId;
    private String objId;
    private String loaiCto;
    private String kieuCto;
    private String i;
    private String u;
    private String ccxWh;
    private String ccxVarh;
    private String tsbTU;
    private String tsbTI;
    private String hsn;
    private String lapTrinh;
    private String chiNMD;
    private String tinhChat;
    private String assetIdParentStr;
    private Integer parentStatus;
    private String parentStatusStr;
    private String typeStr;
    private String orgIdStr;
}

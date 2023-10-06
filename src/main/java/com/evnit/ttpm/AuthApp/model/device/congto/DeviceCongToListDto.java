package com.evnit.ttpm.AuthApp.model.device.congto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DeviceCongToListDto {
    private String assetId;
    private String assetIdParent;
    private String assetDesc;
    private Integer assetORD;
    private String categoryId;
    private String typeId;
    private String useStatusLastId;
    private String useStatusLastName;
    private Date useStatusLastDTime;
    private String serialNum;
    private String pManufacturerId;
    private String nationalFact;
    @JsonProperty("pInstallDate")
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
    private Date dateAccreditationNext;
    private Date inspectionStamps;
    private Double cycleAccreditation;
    private String cycleAccreditationStr;
    private String diemDoName;
    private String diemDoStatus;
    private String parentStatusStr;
    private String trangThaiNMD;
    private String powerId;
    private String powerType;
    private String powerName;
    private String attrDataId;
    private String assetRoot;
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
    private String typeStr;
    private String orgIdStr;
    private String xnk;
    private Integer countTB;
}

package com.evnit.ttpm.AuthApp.model.device.diemdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDiemDoList {
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
    private String assetIdParentStr;
    private Integer parentStatus;
    private String parentStatusStr;
    private String typeStr;
    private String orgIdStr;
    private String attrDataId;
    private String objTypeId;
    private String objId;
    private Date nThuTinh;
    private Date nThuTai;
    private Date nThuTTSL;
    private String tinhChat1;
    private String tinhChat2;
    private String dViGNhan1;
    private String dViGNhan2;
    private String dTacXNK;
    private String xnk;
}

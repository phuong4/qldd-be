package com.evnit.ttpm.AuthApp.model.device.common;

import com.evnit.ttpm.AuthApp.model.device.diemdo.ZAGDiemDo;
import com.evnit.ttpm.AuthApp.model.device.congto.ZAGCongTo;
import com.evnit.ttpm.AuthApp.model.device.ti.ZAGTI;
import com.evnit.ttpm.AuthApp.model.device.tu.ZAGTU;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceCrudDto {
    private String assetId;
    private BigInteger transId;
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
    private Date pInstallDate;
    private Date inspectionStamps;
    private String userCRId;
    private Date userCRDTime;
    private String userMDFId;
    private Date userMDFDTime;
    private String orgId;
    private String uLevelId;
    private Boolean isSave;
    private String assetNote;
    private Date dateOperation;
    private String assetIdLink;
    private Date dateAccreditation;
    private Double cycleAccreditation;
    private String attrDataId;
    private String objTypeId;
    private String objId;
    private ZAGCongTo congTo;
    private List<ZAGTU> tu;
    private List<ZAGTI> ti;
    private ZAGDiemDo diemDo;
    private String assetRoot;

    private String chuky;
    private String laptrinh;
    private String chinmd;
    private String orgidstr;
    private String tempstr;
    private String loaictostr;
    private String kieuctostr;
    private String istr;
    private String ustr;
    private String ccxvarhstr;
    private String ccxwhstr;
    private String tsbtistr;
    private String tsbtustr;
    private String hsnstr;
}

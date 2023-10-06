package com.evnit.ttpm.AuthApp.model.device.ti;

import com.evnit.ttpm.AuthApp.entity.device.ti.ViewTI;
import com.evnit.ttpm.AuthApp.entity.device.ti.ViewTIPre;
import com.evnit.ttpm.AuthApp.entity.device.tu.ViewTUPre;
import com.evnit.ttpm.AuthApp.model.device.tu.TUCreateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetTICreateDto {
    private String assetId;
    private String assetIdChange;
    private String assetIdChangeChild;
    private String assetIdParent;
    private String assetDesc;
    private Integer assetORD;
    private String categoryId;
    private String typeId;
    private String idDiemDo;
    private String useStatusLastId;
    private Date useStatusLastDTime;
    private Date pInstallDate;
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
    private Boolean isSave;
    private BigInteger transId;
    private Boolean group;
    private String kieuTI;
    List<TICreateDto> tiList;
    List<ViewTI> tiList1;
    private Boolean checkReplace;
    private List<String> listCuonDelete;


    private String useStatusLastIdStr;
    private String serialNum;
    private String pManufacturerId;
    private String pManufacturerIdStr;
    private String nationalFact;
    private String cycleAccreditationStr;
    private String attrDataId;
    private String objTypeId;
    private String objId;
    private String cuon;
    private String pha;
    private String congTo;
    private Date pDuyetMau;
    private String kieuTU;
    private String areaId;
    private String areaName;
    private String powerSystemName;
    private String nameTUCuon;
    private String powerSystemType;
    private Integer ownershipUnit;
    private String ownershipUnitStr;
    private Boolean xnk;
    private Integer statusTU;
    private String statusTUStr;
    private String tinhChat;
    private Date dateAccreditationNext;
    private String diemDoName;
    private String machNThu;
    private String cuonDNoi;
    private String tsb;
    private String ccx;
    private String dungLuong;
    private String powerSystemStatus;
    private String powerSystemStatusStr;
    private String powerSystemId;
    private Integer countTB;
    private Integer assetOrd;
}


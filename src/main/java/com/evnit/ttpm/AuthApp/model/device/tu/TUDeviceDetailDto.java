package com.evnit.ttpm.AuthApp.model.device.tu;

import com.evnit.ttpm.AuthApp.model.device.cuon.CuonAssetDetailDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TUDeviceDetailDto {
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
    private String pha;
    private Date pDuyetMau;
    private String kieuTU;
    private String ccx;
    private String dungLuong;
    private String cuonDNoi;
    private String machNThu;
    private String tinhChat;
    private String tsb;
    private List<CuonAssetDetailDto> listCuonAsset;
}

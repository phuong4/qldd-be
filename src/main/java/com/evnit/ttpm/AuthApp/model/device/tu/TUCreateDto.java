package com.evnit.ttpm.AuthApp.model.device.tu;

import com.evnit.ttpm.AuthApp.model.device.cuon.CuonCreateDto;
import lombok.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TUCreateDto {
    private String assetId;
    private String assetIdParent;
    private String assetRoot;
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
    private String assetNote;
    private Date dateOperation;
    private String assetIdLink;
    private Date dateAccreditation;
    private Double cycleAccreditation;
    private Boolean isSave;
    private Boolean isDelete;
    private BigInteger transId;
    private ZAGTUCreateDto zagtu;
    private Integer setType;
    private List<CuonCreateDto> listCuon;


    private String useStatusLastIdStr;
    private String pManufacturerIdStr;
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

    public Integer getAssetORD() {
        return assetORD;
    }

    public void setAssetORD(Integer assetORD) {
        this.assetORD = assetORD;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetIdParent() {
        return assetIdParent;
    }

    public void setAssetIdParent(String assetIdParent) {
        this.assetIdParent = assetIdParent;
    }

    public String getAssetDesc() {
        return assetDesc;
    }

    public void setAssetDesc(String assetDesc) {
        this.assetDesc = assetDesc;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getUseStatusLastId() {
        return useStatusLastId;
    }

    public void setUseStatusLastId(String useStatusLastId) {
        this.useStatusLastId = useStatusLastId;
    }

    public Date getUseStatusLastDTime() {
        return useStatusLastDTime;
    }

    public void setUseStatusLastDTime(Date useStatusLastDTime) {
        this.useStatusLastDTime = useStatusLastDTime;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getpManufacturerId() {
        return pManufacturerId;
    }

    public void setpManufacturerId(String pManufacturerId) {
        this.pManufacturerId = pManufacturerId;
    }

    public String getNationalFact() {
        return nationalFact;
    }

    public void setNationalFact(String nationalFact) {
        this.nationalFact = nationalFact;
    }

    public Date getpInstallDate() {
        return pInstallDate;
    }

    public void setpInstallDate(Date pInstallDate) {
        this.pInstallDate = pInstallDate;
    }

    public Date getInspectionStamps() {
        return inspectionStamps;
    }

    public void setInspectionStamps(Date inspectionStamps) {
        this.inspectionStamps = inspectionStamps;
    }

    public String getUserCRId() {
        return userCRId;
    }

    public void setUserCRId(String userCRId) {
        this.userCRId = userCRId;
    }

    public Date getUserCRDTime() {
        return userCRDTime;
    }

    public void setUserCRDTime(Date userCRDTime) {
        this.userCRDTime = userCRDTime;
    }

    public String getUserMDFId() {
        return userMDFId;
    }

    public void setUserMDFId(String userMDFId) {
        this.userMDFId = userMDFId;
    }

    public Date getUserMDFDTime() {
        return userMDFDTime;
    }

    public void setUserMDFDTime(Date userMDFDTime) {
        this.userMDFDTime = userMDFDTime;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getuLevelId() {
        return uLevelId;
    }

    public void setuLevelId(String uLevelId) {
        this.uLevelId = uLevelId;
    }

    public String getAssetNote() {
        return assetNote;
    }

    public void setAssetNote(String assetNote) {
        this.assetNote = assetNote;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getAssetIdLink() {
        return assetIdLink;
    }

    public void setAssetIdLink(String assetIdLink) {
        this.assetIdLink = assetIdLink;
    }

    public Date getDateAccreditation() {
        return dateAccreditation;
    }

    public void setDateAccreditation(Date dateAccreditation) {
        this.dateAccreditation = dateAccreditation;
    }

    public Double getCycleAccreditation() {
        return cycleAccreditation;
    }

    public void setCycleAccreditation(Double cycleAccreditation) {
        this.cycleAccreditation = cycleAccreditation;
    }

    public ZAGTUCreateDto getZagtu() {
        return zagtu;
    }

    public void setZagtu(ZAGTUCreateDto zagtu) {
        this.zagtu = zagtu;
    }

    public List<CuonCreateDto> getListCuon() {
        return listCuon;
    }

    public void setListCuon(List<CuonCreateDto> listCuon) {
        this.listCuon = listCuon;
    }
}

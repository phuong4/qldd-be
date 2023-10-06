package com.evnit.ttpm.AuthApp.entity.device;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.hssf.record.formula.functions.Na;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;

@Table(name = "VIEW_DIEMDO")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewDiemDo {
    @Id
    @Column(name = "ASSETID")
    @JsonProperty("id")
    private String assetId;
    @JsonProperty("tbaid")
    @Column(name = "ASSETID_PARENT")
    private String assetIdParent;
    @Column(name = "ASSETDESC")
    @JsonProperty("name")
    private String assetDesc;
    @Column(name = "ASSETORD")
    private Integer assetORD;
    @Column(name = "CATEGORYID")
    private String categoryId;
    @Column(name = "TYPEID")
    private String typeId;
    @Column(name = "ASSETNOTE")
    @JsonProperty("note")
    private String assetNote;
    @Column(name = "USESTATUS_LAST_ID")
    @JsonProperty("status")
    private String useStatusLastId;
    @Column(name = "USESTATUS_LAST_DTIME")
    private Date useStatusLastDTime;
    @Column(name = "SERIALNUM")
    private String serialNum;
    @Column(name = "P_MANUFACTURERID")
    private String pManufacturerId;
    @Column(name = "NATIONALFACT")
    private String nationalFace;
    @Column(name = "P_INSTALLDATE")
    private Date pInstallDate;
    @Column(name = "USER_CR_ID")
    private String userCRDId;
    @Column(name = "USER_CR_DTIME")
    private Date userCRDTime;
    @Column(name = "USER_MDF_ID")
    private String userMDFId;
    @Column(name = "USER_MDF_DTIME")
    private Date userMDFDTime;
    @Column(name = "ORGID")
    @JsonProperty("unit")
    private String orgId;
    @Column(name = "ULEVELID")
    private String uLevelId;
    @Column(name = "DATEOPERATION")
    private Date dateOperation;
    @Column(name = "ASSETID_LINK")
    @JsonProperty("code")
    private String assetIdLink;
    @Column(name = "DATEACCREDITATION")
    private Date dateAccreditation;
    @Column(name = "CYCLEACCREDITATIOn")
    private Double cycleAccreditation;
    @Column(name = "INSPECTION_STAMPS")
    private String inspectionStamps;
    @Column(name = "ASSETLINK")
    private String assetLink;
    @Column(name = "ISDELETE")
    private Boolean isDelete;
    @Column(name = "ISSAVE")
    private Boolean isSave;
    @Column(name = "TRANSID")
    private BigInteger transId;
    @Column(name = "PARENT_STR")
    private String parentStr;
    @Column(name = "PARENT_STATUS")
    private String parentStatus;
    @Column(name = "PARENT_TYPE")
    @JsonProperty("type")
    private String parentType;
    @Column(name = "ATTRDATAID")
    private String attrDataId;
    @Column(name = "OBJTYPEID")
    private String objTypeId;
    @Column(name = "OBJID")
    private String objId;
    @Column(name = "NTHU_TINH")
    private Date nThuTinh;
    @Column(name = "NTHU_TAI")
    private Date nThuTai;
    @JsonProperty("dateincome")
    @Column(name = "NTHU_TTSL")
    private Date nThuTTSL;
    @JsonProperty("ptgn1TC")
    @Column(name = "TINH_CHAT1")
    private String tinhChat1;
    @JsonProperty("ptgn2TC")
    @Column(name = "TINH_CHAT2")
    private String tinhChat2;
    @Column(name = "DVI_GNHAN1")
    @JsonProperty("ptgn1DVGN")
    private String dViGNhan1;
    @JsonProperty("ptgn2DVGN")
    @Column(name = "DVI_GNHAN2")
    private String dViGNhan2;
    @Column(name = "DTAC_XNK")
    private String dTacXNK;
    @Column(name = "XNK")
    private String xnk;

    public Date getnThuTinh() {
        return nThuTinh;
    }

    public void setnThuTinh(Date nThuTinh) {
        this.nThuTinh = nThuTinh;
    }

    public Date getnThuTai() {
        return nThuTai;
    }

    public void setnThuTai(java.sql.Date nThuTai) {
        this.nThuTai = nThuTai;
    }

    public Date getnThuTTSL() {
        return nThuTTSL;
    }

    public void setnThuTTSL(java.sql.Date nThuTTSL) {
        this.nThuTTSL = nThuTTSL;
    }

    public String getTinhChat1() {
        return tinhChat1;
    }

    public void setTinhChat1(String tinhChat1) {
        this.tinhChat1 = tinhChat1;
    }

    public String getdViGNhan1() {
        return dViGNhan1;
    }

    public void setdViGNhan1(String dViGNhan1) {
        this.dViGNhan1 = dViGNhan1;
    }

    public String getTinhChat2() {
        return tinhChat2;
    }

    public void setTinhChat2(String tinhChat2) {
        this.tinhChat2 = tinhChat2;
    }

    public String getdViGNhan2() {
        return dViGNhan2;
    }

    public void setdViGNhan2(String dViGNhan2) {
        this.dViGNhan2 = dViGNhan2;
    }

    public String getdTacXNK() {
        return dTacXNK;
    }

    public void setdTacXNK(String dTacXNK) {
        this.dTacXNK = dTacXNK;
    }

    public String getXnk() {
        return xnk;
    }

    public void setXnk(String xnk) {
        this.xnk = xnk;
    }
}

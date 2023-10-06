package com.evnit.ttpm.AuthApp.entity.device.ti;

import com.evnit.ttpm.AuthApp.entity.category.SCategoryDonViSH;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Table(name = "VIEWTI")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ViewTIDetail {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "ASSETID")
    private String assetId;
    @Column(name = "ASSETID_PARENT")
    private String assetIdParent;
    @Column(name = "POWERSYSTEMID")
    private String powerSystemId;
    @Column(name = "POWERSYSTEMTYPE")
    private String powerSystemType;
    @Column(name = "IDCUON")
    private String idCuon;
    @Column(name = "POWERSYSTEMNAME")
    private String powerSystemName;
    @Column(name = "POWERSYSTEMSTATUS")
    private String powerSystemStatus;
    @Column(name = "NAMETU_CUON")
    private String nameTUCuon;
    @Column(name = "PHA")
    private String pha;
    @Column(name = "XNK")
    private Boolean xnk;
    @Column(name = "OWNERSHIP_UNIT")
    private Integer ownershipUnit;
    @Column(name = "STATUS_TU")
    private Integer statusTU;
    @Column(name = "TINH_CHAT")
    private String tinhChat;
    @Column(name = "IDDIEMDO")
    private String idDiemDo;
    @Column(name = "DIEMDONAME")
    private String diemDoName;
    @Column(name = "MACH_NTHU")
    private String machNThu;
    @Column(name = "PDUYET_MAU")
    private Date pDuyetMau;
    /*@Column(name = "KIEUTU_NAME")
    private Date kieuTU_Name;*/
    @Column(name = "KIEU_TI")
    private String kieuTI;
    @Column(name="ASSETDESC")
    private String assetDesc;
    @Column(name = "ASSETORD")
    private Integer assetOrd;
    /*@Column(name = "CATEGORYID")
    private String categoryId;*/
    /*@Column(name = "TYPEID")
    private String typeId;*/
    @Column(name = "USESTATUS_LAST_ID")
    private String useStatusLastId;
    /*@Column(name = "USESTATUS_LAST_DTIME")
    private Date useStatusLastDTime;*/
    @Column(name = "SERIALNUM")
    private String serialNum;
    @Column(name = "P_MANUFACTURERID")
    private String pManufacturerId;
    @Column(name = "P_MANUFACTURERNAME")
    private String pManufacturerName;
    @Column(name = "NATIONALFACT")
    private String nationalFact;
    @Column(name = "NATIONALFACTNAME")
    private String nationalFactName;
    @Column(name = "P_INSTALLDATE")
    private Date pInstallDate;
    /*@Column(name = "USER_CR_ID")
    private String userCRId;
    @Column(name = "USER_CR_DTIME")
    private Date userCRDTime;
    @Column(name = "USER_MDF_ID")
    private String userMdfId;
    @Column(name = "USER_MDF_DTIME")
    private Date userMdfDTime;*/
    @Column(name = "ORGID")
    private String orgId;
    /*@Column(name = "ULEVELID")
    private String uLevelId;
    @Column(name = "ASSETNOTE")
    private String assetNote;
    @Column(name = "DATEOPERATION")
    private Date dateOperation;
    @Column(name = "ASSETID_LINK")
    private String assetIdLink;*/
    @Column(name = "DATEACCREDITATION")
    private Date DateAccreditation;
    @Column(name = "DATEACCREDITATION_NEXT")
    private Date dateAccreditationNext;
    @Column(name = "INSPECTION_STAMPS")
    private Date inspectionStamps;
    @Column(name = "CYCLEACCREDITATION")
    private Double cycleAccreditation;
    @Column(name = "DVSH")
    private String dvsh;
    /*@Column(name = "OBJTYPEID")
    private String objTypeId;
    @Column(name = "OBJID")
    private String objId;*/
    @Column(name = "ISDELETE")
    private Boolean ISDELETE;
    @Column(name = "CUON_DNOI")
    private String cuonDNoi;
    @Column(name = "TSB")
    private String tsb;
    @Column(name = "CCX")
    private String ccx;
    @Column(name = "DUNG_LUONG")
    private String dungLuong;
    @Column(name = "STATUS_TU_1")
    private Integer statusTU1;
    @Column(name = "TYPENUMBER")
    private Integer typeNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNERSHIP_UNIT",insertable = false,updatable = false)
    private SCategoryDonViSH sCategoryDonViSHByOwnership;
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "P_MANUFACTURERID",insertable = false,updatable = false)
    private SListAll sListAllById;*/
}

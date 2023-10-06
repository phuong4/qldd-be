package com.evnit.ttpm.AuthApp.entity.device.cuon;

import com.evnit.ttpm.AuthApp.entity.device.diemdo.ViewDiemDoTest;
import com.evnit.ttpm.AuthApp.entity.device.ti.ViewTI;
import com.evnit.ttpm.AuthApp.entity.device.ti.ViewTIDevice;
import com.evnit.ttpm.AuthApp.entity.device.ti.ViewTIPre;
import com.evnit.ttpm.AuthApp.entity.device.tu.ViewTU;
import com.evnit.ttpm.AuthApp.entity.device.tu.ViewTUDevice;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "VIEWCUON_TI")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewCuonTI {
    @Id
    @Column(name = "ASSETID")
    private String assetId;
    @Column(name = "ASSETID_PARENT")
    private String assetIdParent;
    @Column(name = "ASSETDESC")
    private String assetDesc;
    @Column(name = "ASSETORD")
    private Integer assetORD;
    @Column(name = "CATEGORYID")
    private String categoryId;
    @Column(name = "TYPEID")
    private String typeId;
    @Column(name = "USESTATUS_LAST_ID")
    private String useStatusLastId;
    @Column(name = "USESTATUS_LAST_DTIME")
    private Date useStatusLastDTime;
    @Column(name = "SERIALNUM")
    private String serialNum;
    @Column(name = "P_MANUFACTURERID")
    private String pManufactureId;
    @Column(name = "NATIONALFACT")
    private String nationalFact;
    @Column(name = "P_INSTALLDATE")
    private Date pInstallDate;
    @Column(name = "USER_CR_ID")
    private String userCrId;
    @Column(name = "USER_CR_DTIME")
    private Date userCRDTime;
    @Column(name = "USER_MDF_ID")
    private String userMdfId;
    @Column(name = "USER_MDF_DTIME")
    private Date userMdfDTime;
    @Column(name = "ORGID")
    private String orgId;
    @Column(name = "ULEVELID")
    private String uLevelId;
    @Column(name = "ASSETNOTE")
    private String assetNote;
    @Column(name = "DATEOPERATION")
    private Date dateOperation;
    @Column(name = "ASSETID_LINK")
    private String assetIdLink;
    @Column(name = "DATEACCREDITATION")
    private Date dateAccreditation;
    @Column(name = "INSPECTION_STAMPS")
    private Date inspectionStamps;
    @Column(name = "CYCLEACCREDITATION")
    private Double cycleAccreditation;
    @Column(name = "ATTRDATAID")
    private String attrDataId;
    @Column(name = "OBJTYPEID")
    private String objTypeId;
    @Column(name = "OBJID")
    private String objId;
    @Column(name = "CCX")
    private String ccx;
    @Column(name = "DUNG_LUONG")
    private String dungLuong;
    @Column(name = "CUON_DNOI")
    private String cuonDNoi;
    @Column(name = "MACH_NTHU")
    private String machNThu;
    @Column(name = "TINH_CHAT")
    private String tinhChat;
    @Column(name = "TSB")
    private String tsb;
    @Column(name = "DIEMDONAME")
    private String diemDoName;
    @Column(name = "NATURE")
    private String nature;
    @Column(name = "STATUSMAX")
    private String statusMax;
    @Column(name = "TSBSTR")
    private String tsbStr;
    @Column(name = "CUONDAUNOISTR")
    private String cuonDauNoiStr;
    @Column(name = "CCXSTR")
    private String ccxStr;
    @Column(name = "DLSTR")
    private String dlStr;
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ASSETID_PARENT", insertable = false, updatable = false)
    private ViewTI viewTI;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ASSETID_PARENT", insertable = false, updatable = false)
    private ViewTIDevice viewTIDevice;

//    @ToString.Exclude
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "ASSETID_PARENT", insertable = false, updatable = false)
//    private ViewTIPre viewTIPre;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "A_ASSET_LINK",
            joinColumns = @JoinColumn(name = "ASSETID"),
            inverseJoinColumns = @JoinColumn(name = "ASSETID_LINK"))
    private List<ViewDiemDoTest> diemDoAssets = new ArrayList<>();

}

package com.evnit.ttpm.AuthApp.entity.device.tu;

import com.evnit.ttpm.AuthApp.entity.device.cuon.ViewCuon;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "VIEWTUPRE")
public class ViewTUPre {
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
    private String pManufacturerId;
    @Column(name = "NATIONALFACT")
    private String nationalFact;
    @Column(name = "P_INSTALLDATE")
    private Date pInstallDate;
    @Column(name = "USER_CR_ID")
    private String userCRId;
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
    private Date DateAccreditation;
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
    @Column(name = "PHA")
    private String pha;
    @Column(name = "ASSETROOT")
    private String assetRoot;
    @Column(name = "PDUYET_MAU")
    private Date pDuyetMau;
    @Column(name = "KIEU_TU")
    private String kieuTU;
    @Column(name = "ISDELETE")
    private Boolean ISDELETE;
    @Column(name = "SETTYPE")
    private Integer setType;
    @Column(name = "TEMPSTR")
    private String tempStr;
    @Column(name = "PDUYETMAUSTR")
    private String pDuyetMauStr;
    @Column(name = "NHASX")
    private String nhaSX;
    @Column(name = "NUOCSX")
    private String nuocSX;
    @Column(name = "DVSH")
    private String dvsh;
    @Column(name = "KIEUTUSTR")
    private String kieuTUStr;
    @OneToMany(mappedBy = "viewTUPre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ViewCuon> listCuon;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ASSETID_PARENT", insertable = false, updatable = false)
    @ToString.Exclude
    private ViewSetTU setTU;

    public String getpDuyetMauStr() {
        return pDuyetMauStr;
    }
    public void setpDuyetMauStr(String pDuyetMauStr) {
        this.pDuyetMauStr = pDuyetMauStr;
    }
}

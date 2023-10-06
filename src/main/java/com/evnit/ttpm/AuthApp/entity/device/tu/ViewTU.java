package com.evnit.ttpm.AuthApp.entity.device.tu;

import com.evnit.ttpm.AuthApp.entity.category.A_ASSET;
import com.evnit.ttpm.AuthApp.entity.device.cuon.ViewCuon;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name = "View_TU")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewTU {
    @Id
    @Column(name = "ASSETID")
    private String assetId;
    @Column(name = "ASSETID_PARENT")
    private String assetIdParent;
    @Column(name = "ASSETDESC")
    private String assetDesc;
    @Column(name = "POWERID")
    private String powerId;
    @Column(name = "POWERTYPEID")
    private String powerTypeId;
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
    @JsonProperty("pManufacturerId")
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
    @Column(name = "TOTALSTATUS")
    private Integer totalStatus;
    @Column(name = "TOTAL_CANCEL")
    private Integer totalCancel;
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
    @Column(name = "PDUYET_MAU")
    @JsonProperty("pDuyetMau")
    private Date pDuyetMau;
    @Column(name = "KIEU_TU")
    private String kieuTU;
    @Column(name = "ISDELETE")
    private Boolean ISDELETE;
    @Column(name = "SETTYPE")
    private Integer setType;
    @OneToMany(mappedBy = "viewTU", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ViewCuon> listCuon;
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ASSETID_PARENT", insertable = false, updatable = false)
    private ViewSetTU setTU;

    // Method to add an object to the objectList
    public void addCuonToList(ViewCuon cuon) {
        listCuon.add(cuon);
    }
}

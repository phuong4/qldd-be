package com.evnit.ttpm.AuthApp.entity.device.diemdo;

import com.evnit.ttpm.AuthApp.entity.device.cuon.ViewCuon;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "VIEWDIEMDO_TEST")
@Entity
public class ViewDiemDoTest {
    @Id
    @Column(name = "ASSETID")
    private String assetId;
    @Column(name = "ASSETID_PARENT")
    private String assetIdParent;
    @Column(name = "ASSETDESC")
    private String assetDesc;
    @Column(name = "ASSETORD")
    private Integer assetOrd;
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
    @JsonIgnore
    @ManyToMany(mappedBy = "diemDoAssets")
    private List<ViewCuon> cuonList = new ArrayList<>();
}

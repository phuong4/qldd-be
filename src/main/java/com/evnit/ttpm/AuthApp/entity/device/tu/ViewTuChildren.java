package com.evnit.ttpm.AuthApp.entity.device.tu;

import com.evnit.ttpm.AuthApp.entity.category.A_ASSET;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "VIEWTUCHILDREN")
@Table
public class ViewTuChildren {
    @Id
    @Column(name = "CHILDTU_ID")
    private String childTUId;
    @Column(name = "CHILDTU_PINSTALLDATE")
    private String childTUPInstallDate;
    @Column(name = "CHILDTU_DATEACCREDITATION")
    private String childTUDateAccreditation;
    @Column(name = "CHILDTUCYCLE")
    private String childTUCycle;
    @Column(name = "CHILDTU_INSPECTIONSTAMPS")
    private String childTUInspectionStamps;
    @Column(name = "CHILDTU_PDU")
    private String childTUPDu;
    @Column(name = "CHILDTU_SERIALNUM")
    private String childTUSerialNum;
    @Column(name = "CHILDTU_KIEUTU")
    private String childTUKieuTU;
    @Column(name = "CHILDTU_PHA")
    private String childTUPha;
    @Column(name = "PARENTTU_ID")
    private String parentTUId;
    @Column(name = "PARENTTU_NAME")
    private String parentTUName;
    @Column(name = "PARENTTU_NOTE")
    private String parentTUNote;
    @Column(name = "PARENTTU_TYPEID")
    private String parentTUTypeId;
    @Column(name = "PARENTTU_IDPARENT")
    private String parentTUIDParent;
    @Column(name = "RN")
    private Long rn;
    @Column(name = "STATUS_COUNT")
    private Long statusCount;
    @Column(name = "ASSETCUONNAME")
    private String assetCuonName;
    @Column(name = "ASSETCUONID")
    private String assetCuonId;
    @Column(name = "MANUFACTURENAME")
    private String manufactureName;
    @Column(name = "SETTYPE")
    private String setType;
    @Column(name = "ISDELETE")
    private Boolean isDelete;
}

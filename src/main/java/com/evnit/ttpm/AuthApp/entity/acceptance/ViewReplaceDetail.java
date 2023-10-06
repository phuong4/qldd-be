package com.evnit.ttpm.AuthApp.entity.acceptance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "VIEW_REPLACEDETAIL")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewReplaceDetail {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "ACCEPTID")
    private String acceptId;
    @Column(name = "ACCEPCHANGEID")
    private String accepChangeId;
    @Column(name = "STATUS_ACCEPT")
    private Boolean statusAccept;
    @Column(name = "ACCREDID")
    private String accredId;
    @Column(name = "CAUSE_CHANGE")
    private String causeChange;
    @Column(name = "POWERID")
    private String powerId;
    @Column(name = "ASSETID")
    private String assetId;
    @Column(name = "ASSETID_CHANGE")
    private String assetIdChange;
    @Column(name = "CATEGORYID")
    private String categoryId;
    @Column(name = "NEW_SERIALNUM")
    private String newSerialNum;
    @Column(name = "NEW_ASSETDESC")
    private String newAssetDesc;
    @Column(name = "NEW_ASSETID_CHILD")
    private String newAssetIdChild;
    @Column(name = "NEW_SERIALNUM_CHILD")
    private String newSerialNumChild;
    @Column(name = "NEW_ASSETID_PARENT")
    private String newAssetIdParent;
    @Column(name = "OLD_ASSETDESC")
    private String oldAssetDesc;
    @Column(name = "OLD_SERIALNUM")
    private String oldSerialNum;
    @Column(name = "OLD_ASSETID_CHILD")
    private String oldAssetIdChild;
    @Column(name = "OLD_SERIALNUM_CHILD")
    private String oldSerialNumChild;
    @Column(name = "NEW_PHA")
    private String newPha;
    @Column(name = "OLD_PHA")
    private String oldPha;
    @Column(name = "IDCUON")
    private String idCuon;
    @Column(name = "IDDIEMDO")
    private String idDiemDo;
}

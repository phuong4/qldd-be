package com.evnit.ttpm.AuthApp.entity.device.ti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "VIEWSETTI")
@Entity
public class ViewSetTI {
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
    @Column(name = "ASSETNOTE")
    private String assetNote;
    @Column(name = "ISDELETE")
    private Boolean ISDELETE;
    @Column(name = "POWERSYSTEM_NAME")
    private String powerSystemName;
    @Column(name = "POWERSYSTEM_STATUS")
    private Integer powerSystemStatus;
    @Column(name = "POWERTYPEID")
    private String powerTypeId;
    @Column(name = "SETTYPE")
    private Integer setType;
    @Column(name = "STATUS_BTI")
    private Integer statusBTI;
    @Column(name = "TOTAL_CANCEL")
    private Integer totalCancel;
    @Column(name = "TOTAL_STATUS")
    private Integer totalStatus;
    @OneToMany(mappedBy = "setTI", fetch = FetchType.LAZY)
    private List<ViewTI> tiList;
//    @OneToMany(mappedBy = "viewSetTI", fetch = FetchType.LAZY)
//    private List<ViewTIPre> tiList1;
}

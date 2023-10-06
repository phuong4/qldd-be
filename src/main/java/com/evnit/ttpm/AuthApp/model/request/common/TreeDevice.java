package com.evnit.ttpm.AuthApp.model.request.common;

import com.evnit.ttpm.AuthApp.entity.device.tu.ViewTU;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeDevice {
    private String ASSETID;
    private String ASSETID_PARENT;
    private String ASSETDESC;
    private Integer ASSETORD;
    private String CATEGORYID;
    private String TYPEID;
    private String USESTATUS_LAST_ID;
    private Date USESTATUS_LAST_DTIME;
    private String SERIALNUM;
    private String P_MANUFACTURERID;
    private String NATIONALFACT;
    private Date P_INSTALLDATE;
    private String USER_CR_ID;
    private Date USER_CR_DTIME;
    private String USER_MDF_ID;
    private Date USER_MDF_DTIME;
    private String ORGID;
    private String ULEVELID;
    private String ASSETNOTE;
    private Date DATEOPERATION;
    private String ASSETID_LINK;
    private Date DATEACCREDITATION;
    private Date INSPECTION_STAMPS;
    private Double CYCLEACCREDITATION;
    private String attrDataId;
    private String objTypeId;
    private String objId;
    private String cuon;
    private String pha;
    private String congTo;
    private Date pDuyetMau;
    private String kieuTU;
    private Boolean ISDELETE;
    private List<ViewTU> childen;
}

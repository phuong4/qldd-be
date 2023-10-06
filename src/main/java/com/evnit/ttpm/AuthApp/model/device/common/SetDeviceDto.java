package com.evnit.ttpm.AuthApp.model.device.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetDeviceDto {
    private static final long serialVersionUID = 1L;
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
    private Double CYCLEACCREDITATION;
    private String ASSETLINK;
    private Boolean ISDELETE;
    private Boolean ISSAVE;
    private Date INSPECTION_STAMPS;
    private BigInteger transId;
    private String ASSETROOT;
    private String cuonIdsReplace;
    private Integer setType;
}

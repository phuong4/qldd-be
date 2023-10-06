package com.evnit.ttpm.AuthApp.model.category.QuanLyDD;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssetCreateDto {
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
    private Float CYCLEACCREDITATION;
    private String ASSETLINK;
    private boolean ISDELETE;

  
	
    
}

package com.evnit.ttpm.AuthApp.entity.device.congto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "VIEW_CONGTO")
@Data
public class ViewCongTo {
    @Column(name = "ASSETID")
    @Id
    private String ASSETID;
    @Size(max = 250)
    @JsonProperty("ASSETID_PARENT")
    @Column(name = "ASSETID_PARENT")
    private String ASSETID_PARENT;
    //    @Column(name = "ASSETROOT")
//    private String ASSETROOT;
    @Size(max = 250)
    @JsonProperty("ASSETDESC")
    @Column(name = "ASSETDESC")
    private String ASSETDESC;
    @Size(min = 1)
    @JsonProperty("ASSETORD")
    @Column(name = "ASSETORD")
    private Integer ASSETORD;
    @Size(max = 1000000)
    @JsonProperty("CATEGORYID")
    @Column(name = "CATEGORYID")
    private String CATEGORYID;
    @Size(max = 1000000)
    @JsonProperty("TYPEID")
    @Column(name = "TYPEID")
    private String TYPEID;
    @Size(max = 250)
    @JsonProperty("USESTATUS_LAST_ID")
    @Column(name = "USESTATUS_LAST_ID")
    private String USESTATUS_LAST_ID;
    @JsonProperty("USESTATUS_LAST_DTIME")
    @Column(name = "USESTATUS_LAST_DTIME")
    private Date USESTATUS_LAST_DTIME;
    @Size(max = 250)
    @JsonProperty("SERIALNUM")
    @Column(name = "SERIALNUM")
    private String SERIALNUM;
    @Size(max = 250)
    @JsonProperty("P_MANUFACTURERID")
    @Column(name = "P_MANUFACTURERID")
    private String P_MANUFACTURERID;
    @Size(max = 250)
    @JsonProperty("NATIONALFACT")
    @Column(name = "NATIONALFACT")
    private String NATIONALFACT;
    @Size(max = 250)
    @JsonProperty("P_INSTALLDATE")
    @Column(name = "P_INSTALLDATE")
    private Date P_INSTALLDATE;
    @Size(max = 250)
    @JsonProperty("USER_CR_ID")
    @Column(name = "USER_CR_ID")
    private String USER_CR_ID;
    @Size(max = 250)
    @JsonProperty("USER_CR_DTIME")
    @Column(name = "USER_CR_DTIME")
    private Date USER_CR_DTIME;
    @Size(max = 250)
    @JsonProperty("USER_MDF_ID")
    @Column(name = "USER_MDF_ID")
    private String USER_MDF_ID;
    @Column(name = "USER_MDF_DTIME")
    private Date USER_MDF_DTIME;
    @Size(max = 250)
    @JsonProperty("ORGID")
    @Column(name = "ORGID")
    private String ORGID;
    @Size(max = 250)
    @JsonProperty("ULEVELID")
    @Column(name = "ULEVELID")
    private String ULEVELID;
    @Size(max = 250)
    @JsonProperty("ASSETNOTE")
    @Column(name = "ASSETNOTE")
    private String ASSETNOTE;
    @Size(max = 250)
    @JsonProperty("DATEOPERATION")
    @Column(name = "DATEOPERATION")
    private Date DATEOPERATION;
    @Size(max = 250)
    @JsonProperty("ASSETID_LINK")
    @Column(name = "ASSETID_LINK")
    private String ASSETID_LINK;
    @Size(max = 250)
    @JsonProperty("DATEACCREDITATION")
    @Column(name = "DATEACCREDITATION")
    private Date DATEACCREDITATION;
    @JsonProperty("INSPECTION_STAMPS")
    @Column(name = "INSPECTION_STAMPS")
    private Date INSPECTION_STAMPS;
    @Size(max = 250)
    @JsonProperty("CYCLEACCREDITATION")
    @Column(name = "CYCLEACCREDITATION")
    private Double CYCLEACCREDITATION;
    private String DIEMDONAME;

    @Column(name = "DIEMDO_STATUS")
    private String DIEMDOSTATUS;
    @Column(name = "POWER_ID")
    private String POWERID;
    @Column(name = "POWERID_ORDER")
    private String POWERIDORDER;
    @Column(name = "POWER_NAME")
    private String POWERNAME;
    @Column(name = "POWER_TYPE")
    private String powerType;
    private String ORGIDSTR;
    private String TYPEIDSTR;
    @Column(name = "ATTRDATAID")
    private String attrDataId;
    @Column(name = "OBJTYPEID")
    private String objTypeId;
    @Column(name = "OBJID")
    private String objId;
    @Column(name = "LOAI_CTO")
    private String loaiCto;
    @Column(name = "KIEU_CTO")
    private String kieuCto;
    @Column(name = "I")
    private String i;
    @Column(name = "U")
    private String u;
    @Column(name = "CCX_Wh")
    private String ccxWh;
    @Column(name = "CCX_Varh")
    private String ccxVarh;
    @Column(name = "TSB_TU")
    private String tsbTU;
    @Column(name = "TSB_TI")
    private String tsbTI;
    @Column(name = "HSN")
    private String hsn;
    @Column(name = "LTRINH")
    private String lTrinh;
    @Column(name = "CHI_NMD")
    private String chiNMD;
    @Column(name = "TINH_CHAT")
    private String tinhChat;
    @Column(name = "ISDELETE")
    private Boolean ISDELETE;

    @Column(name = "TRANGTHAINMD")
    private String TRANGTHAINMD;

    @Column(name = "XNK")
    private String XNK;
    @Column(name = "ASSETROOT")
    private String ASSETROOT;

    @JsonProperty("DATEACCREDITATIONNEXT")
    @Column(name = "DATEACCREDITATIONNEXT")
    private Date DATEACCREDITATIONNEXT;
}

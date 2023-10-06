package com.evnit.ttpm.AuthApp.entity.device.cuon;

import com.evnit.ttpm.AuthApp.entity.category.A_ASSET;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;

@Table(name = "CUON_ASSET_TEMP")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuonAssetTemp {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "ASSETID")
    private String ASSETID;
    @Size(max = 250)
    @JsonProperty("ASSETID_PARENT")
    @Column(name = "ASSETID_PARENT")
    private String ASSETID_PARENT;
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
    @Size(max = 250)
    @JsonProperty("CYCLEACCREDITATION")
    @Column(name = "CYCLEACCREDITATION")
    private Double CYCLEACCREDITATION;
    @Size(max = 250)
    @JsonProperty("ASSETLINK")
    @Column(name = "ASSETLINK")
    private String ASSETLINK;
    @JsonProperty("ISDELETE")
    @Column(name = "ISDELETE")
    private Boolean ISDELETE;
    @JsonProperty("INSPECTION_STAMPS")
    @Column(name = "INSPECTION_STAMPS")
    private Date INSPECTION_STAMPS;

    public CuonAssetTemp mapperCuon(A_ASSET asset) {
        CuonAssetTemp cuonAssetTemp = new CuonAssetTemp();
        cuonAssetTemp.setASSETID(asset.getASSETID());
        cuonAssetTemp.setASSETDESC(asset.getASSETDESC());
        cuonAssetTemp.setASSETID_PARENT(asset.getASSETID_PARENT());
        cuonAssetTemp.setASSETLINK(asset.getASSETLINK());
        cuonAssetTemp.setASSETORD(asset.getASSETORD());
        cuonAssetTemp.setASSETID_LINK(asset.getASSETID_LINK());
        cuonAssetTemp.setASSETNOTE(asset.getASSETNOTE());
        cuonAssetTemp.setCATEGORYID(asset.getCATEGORYID());
        cuonAssetTemp.setCYCLEACCREDITATION(asset.getCYCLEACCREDITATION());
        cuonAssetTemp.setDATEACCREDITATION(asset.getDATEACCREDITATION());
        cuonAssetTemp.setINSPECTION_STAMPS(asset.getINSPECTION_STAMPS());
        cuonAssetTemp.setDATEOPERATION(asset.getDATEOPERATION());
        cuonAssetTemp.setISDELETE(asset.getISDELETE());
        cuonAssetTemp.setNATIONALFACT(asset.getNATIONALFACT());
        cuonAssetTemp.setP_MANUFACTURERID(asset.getP_MANUFACTURERID());
        cuonAssetTemp.setORGID(asset.getORGID());
        cuonAssetTemp.setSERIALNUM(asset.getSERIALNUM());
        cuonAssetTemp.setTYPEID(asset.getTYPEID());
        cuonAssetTemp.setUSESTATUS_LAST_ID(asset.getUSESTATUS_LAST_ID());
        return cuonAssetTemp;
    }
}

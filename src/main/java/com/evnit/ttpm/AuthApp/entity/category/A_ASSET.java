package com.evnit.ttpm.AuthApp.entity.category;

import com.evnit.ttpm.AuthApp.entity.device.ti.ViewTIDevice;
import com.evnit.ttpm.AuthApp.entity.device.tu.ViewTUDevice;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Table(name = "A_ASSET")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class A_ASSET implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
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
    @Column(name = "ISSAVE")
    private Boolean ISSAVE;
    @JsonProperty("INSPECTION_STAMPS")
    @Column(name = "INSPECTION_STAMPS")
    private Date INSPECTION_STAMPS;
    @JsonIgnore
    @Column(name = "TRANSID")
    private BigInteger transId;
    @JsonProperty("ASSETROOT")
    @Column(name = "ASSETROOT")
    private String ASSETROOT;
    @Column(name = "SETTYPE")
    private Integer setType;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "ASSETID_PARENT", insertable = false, updatable = false)
//    private ViewTIDevice viewTiDevice;

    public enum CategoryId {
        CONGTO, TU, TI, DIEMDO, CUON, BTU, BTI
    }
}


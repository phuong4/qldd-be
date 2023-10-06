package com.evnit.ttpm.AuthApp.entity.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Table(name = "P_PROBLEM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class SCategorySuCoBatThuong implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1)
    @JsonProperty("ID")
    @Column(name = "PROBLEMID")
    private String PROBLEMID;
    @Size(max = 250)
    @JsonProperty("PROBLEMID_ORG")
    @Column(name = "PROBLEMID_ORG")
    private String PROBLEMID_ORG;
    @Size(max = 250)
    @JsonProperty("PSTATUSID")
    @Column(name = "PSTATUSID")
    private String PSTATUSID;
    @Size(max = 250)
    @JsonProperty("STARTDTIME")
    @Column(name = "STARTDTIME")
    private Date STARTDTIME;
    @Size(max = 250)
    @JsonProperty("ENDDTIME")
    @Column(name = "ENDDTIME")
    private Date ENDDTIME;
    
    @Size(max = 1000000)
    @JsonProperty("TYPEOBJID")
    @Column(name = "TYPEOBJID")
    private String TYPEOBJID;
    @Size(max = 1000000)
    @JsonProperty("PCONTENT")
    @Column(name = "PCONTENT")
    private String PCONTENT;
    @Size(max = 250)
    @JsonProperty("PCONSEQUENCE")
    @Column(name = "PCONSEQUENCE")
    private String PCONSEQUENCE;
    
    @JsonProperty("PCAUSE")
    @Column(name = "PCAUSE")
    private String PCAUSE;
    @Size(max = 1000000)
    @JsonProperty("PREMEDIES")
    @Column(name = "PREMEDIES")
    private String PREMEDIES;
    @Size(max = 1000000)
    @JsonProperty("PRESULT_PROCESS")
    @Column(name = "PRESULT_PROCESS")
    private String PRESULT_PROCESS;
    @Size(max = 1000000)
    @JsonProperty("MRERESULT_PROCESS")
    @Column(name = "MRERESULT_PROCESS")
    private Date MRERESULT_PROCESS;
    @Size(max = 250)
    @Size(max = 250)
    @JsonProperty("USER_CR_ID")
    @Column(name = "USER_CR_ID")
    private String USER_CR_ID;
    @Size(max = 250)
    @JsonProperty("USER_CR_DTIME")
    @Column(name = "USER_CR_DTIME")
    private String USER_CR_DTIME;
    @Size(max = 250)
    @JsonProperty("USER_MDF_ID")
    @Column(name = "USER_MDF_ID")
    private String USER_MDF_ID;
    @Size(max = 250)
    @JsonProperty("USER_MDF_DTIME")
    @Column(name = "USER_MDF_DTIME")
    private Date USER_MDF_DTIME;
    @Size(max = 250)
    @JsonProperty("NQL_XNHAN")
    @Column(name = "NQL_XNHAN")
    private boolean NQL_XNHAN;
    @Size(max = 250)
    @JsonProperty("LD_XNHAN")
    @Column(name = "LD_XNHAN")
    private boolean LD_XNHAN;
    @Size(min = 1)
    @JsonProperty("ORGID")
    @Column(name = "ORGID")
    private String ORGID;
    @JsonProperty("ASSETID")
    @Column(name = "ASSETID")
    private String ASSETID;
    @JsonProperty("KQ_REPLACE")
    @Column(name = "KQ_REPLACE")
    private boolean KQ_REPLACE;
    @JsonProperty("PCALCULATEID")
    @Column(name = "PCALCULATEID")
    private String PCALCULATEID;
    @JsonProperty("ISDELETE")
    @Column(name = "ISDELETE")
    private boolean ISDELETE;
    @JsonProperty("TYPE")
    @Column(name = "TYPE")
    private Integer TYPE;
//    @JsonProperty("FILEDATA")
//    @Column(name = "FILEDATA")
//    private String FILEDATA;
}

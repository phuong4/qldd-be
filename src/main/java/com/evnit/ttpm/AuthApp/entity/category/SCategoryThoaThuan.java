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

@Table(name = "M_DEAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class SCategoryThoaThuan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1)
    @JsonProperty("ID")
    @Column(name = "DEALID")
    private String dealId;
    @Size(max = 250)
    @JsonProperty("DEALDESC")
    @Column(name = "DEALDESC")
    private String DEALDESC;
    @Size(max = 250)
    @JsonProperty("DEALCONTENT")
    @Column(name = "DEALCONTENT")
    private String DEALCONTENT;
    @Size(max = 250)
    @JsonProperty("ASSETID")
    @Column(name = "ASSETID")
    private String ASSETID;
    @Size(max = 250)
    @JsonProperty("NUMDOC")
    @Column(name = "NUMDOC")
    private String NUMDOC;
    
    @Size(max = 1000000)
    @JsonProperty("DATEDOC")
    @Column(name = "DATEDOC")
    private Date DATEDOC;
    @Size(max = 1000000)
    @JsonProperty("STARTDATE_EFFECT")
    @Column(name = "STARTDATE_EFFECT")
    private Date STARTDATE_EFFECT;
    @Size(max = 250)
    @JsonProperty("ENDDATE_EFFECT")
    @Column(name = "ENDDATE_EFFECT")
    private Date ENDDATE_EFFECT;
    
    @JsonProperty("TYPE_DEAL")
    @Column(name = "TYPE_DEAL")
    private String TYPE_DEAL;
    @Size(max = 250)
    @JsonProperty("FACTOR")
    @Column(name = "FACTOR")
    private Double FACTOR;
    @Size(max = 250)
    @JsonProperty("FORM_SYN_POWER")
    @Column(name = "FORM_SYN_POWER")
    private String FORM_SYN_POWER;
    @Size(max = 250)
    @JsonProperty("STARTDATE_DOC")
    @Column(name = "STARTDATE_DOC")
    private Date STARTDATE_DOC;
    @Size(max = 250)
    @JsonProperty("ENDDATE_DOC")
    @Column(name = "ENDDATE_DOC")
    private Date ENDDATE_DOC;
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
    @JsonProperty("USER__MDF_DTIME")
    @Column(name = "USER__MDF_DTIME")
    private Date USER__MDF_DTIME;
    @Size(max = 250)
    @JsonProperty("VALID_BASE")
    @Column(name = "VALID_BASE")
    private String VALID_BASE;
    @Size(max = 250)
    @JsonProperty("NQL_XNHAN")
    @Column(name = "NQL_XNHAN")
    private boolean NQL_XNHAN;
    @Size(max = 250)
    @JsonProperty("LD_XNHAN")
    @Column(name = "LD_XNHAN")
    private boolean LD_XNHAN;
    @Size(min = 1)
    @JsonProperty("TinhTP")
    @Column(name = "TinhTP")
    private int TinhTP;
    @JsonProperty("CheckTick")
    @Column(name = "CHECKTICK")
    private boolean CheckTick;
//    @JsonProperty("FileData")
//    @Column(name = "FILEDATA")
//    private String FileData;
    @JsonProperty("Note")
    @Column(name = "NOTE")
    private String Note;
    @JsonProperty("ISDELETE")
    @Column(name = "ISDELETE")
    private boolean ISDELETE;
    @JsonProperty("TYPE")
    @Column(name = "TYPE")
    private Integer TYPE;
}

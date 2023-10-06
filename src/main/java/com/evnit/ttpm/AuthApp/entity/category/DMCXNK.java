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
import java.sql.Date;

@Table(name = "VIEW_QLDD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class DMCXNK implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1)
    @JsonProperty("ID")
    @Column(name = "ID")
    private Integer ID;
    @Size(max = 250)
    @JsonProperty("TYPE")
    @Column(name = "TYPE")
    private Integer TYPE;
    @Size(max = 250)
    @JsonProperty("TBAID")
    @Column(name = "TBAID")
    private String TBAID;
    @Size(max = 250)
    @JsonProperty("UNIT")
    @Column(name = "UNIT")
    private String UNIT;
    @Size(max = 250)
    @JsonProperty("CODE")
    @Column(name = "CODE")
    private String CODE;
    @JsonProperty("KeyId")
    @Column(name = "KEYID")
    private String KeyId;

    @Size(max = 1000000)
    @JsonProperty("NAME")
    @Column(name = "NAME")
    private String NAME;
    @Size(max = 1000000)
    @JsonProperty("STATUS")
    @Column(name = "STATUS")
    private Integer STATUS;
    @Size(max = 250)
    @JsonProperty("PTGN1TC")
    @Column(name = "PTGN1TC")
    private String PTGN1TC;
    
    @JsonProperty("PTGN2TC")
    @Column(name = "PTGN2TC")
    private String PTGN2TC;
    @Size(max = 250)
    @JsonProperty("PTGN1DVGN")
    @Column(name = "PTGN1DVGN")
    private String PTGN1DVGN;
    @Size(max = 250)
    @JsonProperty("PTGN2DVGN")
    @Column(name = "PTGN2DVGN")
    private String PTGN2DVGN;
    @Size(max = 250)
    @JsonProperty("datestatic")
    @Column(name = "datestatic")
    private Date datestatic;
    @Size(max = 250)
    @JsonProperty("DATELOAD")
    @Column(name = "DATELOAD")
    private Date DATELOAD;
    @Size(max = 250)
    @JsonProperty("DATEINCOME")
    @Column(name = "DATEINCOME")
    private Date DATEINCOME;
    @Size(max = 250)
    @JsonProperty("XNK")
    @Column(name = "XNK")
    private String XNK;
    @Size(max = 250)
    @JsonProperty("dtxnk")
    @Column(name = "dtxnk")
    private String dtxnk;
    @Size(max = 250)
    @JsonProperty("PARTNERXNK")
    @Column(name = "PARTNERXNK")
    private Integer PARTNERXNK;
    @Size(max = 250)
    @JsonProperty("NOTE")
    @Column(name = "NOTE")
    private String NOTE;
   
    @Size(max = 250)
    @JsonProperty("TBANAME")
    @Column(name = "TBANAME")
    private String TBANAME;
    @Size(max = 250)
    @JsonProperty("UNITNAME")
    @Column(name = "UNITNAME")
    private String UNITNAME;
    @Size(max = 250)
    @JsonProperty("PTGN1TCNAME")
    @Column(name = "PTGN1TCNAME")
    private String PTGN1TCNAME;
    @Size(max = 250)
    @JsonProperty("PTGN2TCNAME")
    @Column(name = "PTGN2TCNAME")
    private String PTGN2TCNAME;
    @Size(max = 250)
    @JsonProperty("PTGN1DVGNNAME")
    @Column(name = "PTGN1DVGNNAME")
    private String PTGN1DVGNNAME;
    @Size(max = 250)
    @JsonProperty("PTGN2DVGNNAME")
    @Column(name = "PTGN2DVGNNAME")
    private String PTGN2DVGNNAME;
    @Size(max = 250)
    @JsonProperty("XNKNAME")
    @Column(name = "XNKNAME")
    private String XNKNAME;
    @JsonProperty("CheckXNK")
    @Column(name = "CHECKXNK")
    private boolean CheckXNK;
    @Size(max = 250)
    @JsonProperty("TBASTATUS")
    @Column(name = "TBASTATUS")
    private Integer TBASTATUS;
    @Size(min = 1)
    @JsonProperty("TBASTATUSID")
    @Column(name = "TBASTATUSID")
    private Integer TBASTATUSID;
    @JsonProperty("STATUSNAME")
    @Column(name = "STATUSNAME")
    private String STATUSNAME;
    @JsonProperty("TBASTATUSNAME")
    @Column(name = "TBASTATUSNAME")
    private String TBASTATUSNAME;

    @JsonProperty("DATEINCOMESTR")
    @Column(name = "DATEINCOMESTR")
    private String DATEINCOMESTR;
    @JsonProperty("TYPENAME")
    @Column(name = "TYPENAME")
    private String TYPENAME;
    @JsonProperty("DTXNKNAME")
    @Column(name = "DTXNKNAME")
    private String DTXNKNAME;



    @JsonProperty("CCXCT")
    @Column(name = "CCXCT")
    private String CCXCT;

    @JsonProperty("VLTDTU")
    @Column(name = "VLTDTU")
    private String VLTDTU;

    @JsonProperty("TSBTU")
    @Column(name = "TSBTU")
    private String TSBTU;

    @JsonProperty("CCXTU")
    @Column(name = "CCXTU")
    private String CCXTU;

    @JsonProperty("DLTU")
    @Column(name = "DLTU")
    private String DLTU;

    @JsonProperty("MNTTU")
    @Column(name = "MNTTU")
    private String MNTTU;

    @JsonProperty("VLTDTI")
    @Column(name = "VLTDTI")
    private String VLTDTI;

    @JsonProperty("TSBTI")
    @Column(name = "TSBTI")
    private String TSBTI;

    @JsonProperty("CCXTI")
    @Column(name = "CCXTI")
    private String CCXTI;

    @JsonProperty("DLTI")
    @Column(name = "DLTI")
    private String DLTI;

    @JsonProperty("MNTTI")
    @Column(name = "MNTTI")
    private String MNTTI;
}

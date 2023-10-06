package com.evnit.ttpm.AuthApp.entity.category;

import com.evnit.ttpm.AuthApp.entity.common.SListAll;
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

@Table(name = "Measuring")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class SCategoryQuanLyDD implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1)
    @JsonProperty("ID")
    @Column(name = "ID")
    private int ID;
    @Size(max = 250)
    @JsonProperty("TYPE")
    @Column(name = "TYPE")
    private int TYPE;
    @Size(max = 250)
    @JsonProperty("TBAID")
    @Column(name = "TBAID")
    private int TBAID;
    @Size(max = 250)
    @JsonProperty("UNITID")
    @Column(name = "UNITID")
    private int UNITID;
    @Size(max = 250)
    @JsonProperty("CODE")
    @Column(name = "CODE")
    private String CODE;
    
    @Size(max = 1000000)
    @JsonProperty("NAME")
    @Column(name = "NAME")
    private String NAME;
    @Size(max = 1000000)
    @JsonProperty("STATUS")
    @Column(name = "STATUS")
    private int STATUS;
    @Size(max = 250)
    @JsonProperty("PTGN1TC")
    @Column(name = "PTGN1TC")
    private int PTGN1TC;
    
    @JsonProperty("PTGN2TC")
    @Column(name = "PTGN2TC")
    private int PTGN2TC;
    @Size(max = 250)
    @JsonProperty("PTGN1DVGN")
    @Column(name = "PTGN1DVGN")
    private int PTGN1DVGN;
    @Size(max = 250)
    @JsonProperty("PTGN2DVGN")
    @Column(name = "PTGN2DVGN")
    private int PTGN2DVGN;
    @Size(max = 250)
    @JsonProperty("DATESTATIC")
    @Column(name = "DATESTATIC")
    private Date DATESTATIC;
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
    private int XNK;
    @Size(max = 250)
    @JsonProperty("PARTNERXNK")
    @Column(name = "PARTNERXNK")
    private int PARTNERXNK;
    @Size(max = 250)
    @JsonProperty("NOTE")
    @Column(name = "NOTE")
    private String NOTE;
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "TBAID",insertable = false,updatable = false)
//    private SCategoryTbaRgl sCategoryTbaRgl ;
//    
//    
//   @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "PTGN1DVGN",insertable = false,updatable = false)
//    private SDeliveryUnit sDeliveryUnit ;
//    
//    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "PTGN2DVGN",insertable = false,updatable = false)
//    private SDeliveryUnit sDeliveryUnit2 ;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "XNK",insertable = false,updatable = false)
//    private DMCXNK slit ;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "PTGN1TC",insertable = false,updatable = false)
//    private SListAll slit1 ;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "PTGN2TC",insertable = false,updatable = false)
//    private SListAll slit2 ;
}

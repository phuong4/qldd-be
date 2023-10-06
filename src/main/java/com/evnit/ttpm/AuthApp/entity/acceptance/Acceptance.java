package com.evnit.ttpm.AuthApp.entity.acceptance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "M_ACCEPTANCE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Acceptance {
    @Id
    @Column(name = "ACCEPTID")
    private String acceptId;
    @Column(name = "ACCEPTDESC")
    private String acceptDesc;
    @Column(name = "ASSETID")
    private String assetId;
    @Column(name = "ACTIONSTARTDATE")
    private Date actionStartDate;
    @Column(name = "ACTIONENDDATE")
    private Date actionEndDate;
    @Column(name = "TYPE_ACCEPT")
    private String typeAccept;
    @Column(name = "STATUS_ACCEPT")
    private String statusAccept;
    @Column(name = "RESULT_ACCEPT")
    private String resultAccept;
    @Column(name = "ACCEPT_FORM")
    private String acceptForm;
    @Column(name = "USER_CR_ID")
    private String userCrId;
    @Column(name = "USER_CR_DTIME")
    private Date userCrDTime;
    @Column(name = "USER_MDF_ID")
    private String userMdfId;
    @Column(name = "USER__MDF_DTIME")
    private Date userMdfDTime;
    @Column(name = "NQL_XNHAN")
    private Boolean nqlXNhan;
    @Column(name = "LD_XNHAN")
    private Boolean ldXNhan;
    public enum TYPE_ACCEPT{
        KTLD,NTTLD,NTMTLD,TTTBDD,NTK,HDD
    }
}

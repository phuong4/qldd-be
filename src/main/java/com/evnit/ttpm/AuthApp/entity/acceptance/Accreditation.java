package com.evnit.ttpm.AuthApp.entity.acceptance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "M_ACCREDITATION")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accreditation {
    @Id
    @Column(name = "ACCREDID")
    private String accredId;
    @Column(name = "ACCREDDESC")
    private String accredDesc;
    @Column(name = "ASSETID")
    private String assetId;
    @Column(name = "ACCREDITATION_STARTDATE")
    private String accreditationStartDate;
    @Column(name = "ACCREDITATION_ENDDATE")
    private String accreditationEndDate;
    @Column(name = "ACCREDITATION_TYPE")
    private String accreditationType;
    @Column(name = "ACCREDITATION_RESULT")
    private String accreditationResult;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "USER_CR_ID")
    private String userCrId;
    @Column(name = "USER_CR_DTIME")
    private String userCrDTime;
    @Column(name = "USER_MDF_ID")
    private String userMdfId;
    @Column(name = "USER_MDF_DTIME")
    private String userMdfDTime;
    @Column(name = "NQL_XNHAN")
    private String nqlXNhan;
    @Column(name = "LD_XNHAN")
    private String ldXNhan;
}

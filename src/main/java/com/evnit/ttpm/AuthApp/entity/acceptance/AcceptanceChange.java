package com.evnit.ttpm.AuthApp.entity.acceptance;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity(name = "M_ACCEPTANCE_CHANGE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AcceptanceChange {
    @Id
    @Column(name = "ACCEPCHANGEID")
    private String accepChangeId;
    @Column(name = "ACCEPTID")
    private String acceptId;
    @Column(name = "ASSETID_CHANGE")
    private String assetIdChange;
    @Column(name = "ASSETID")
    private String assetId;
    @Column(name = "CAUSE_CHANGE")
    private String causeChange;
    @Column(name = "ACCREDID")
    private String accredId;
    @Column(name = "STATUS_ACCEPT")
    private Boolean statusAccept;
    @Column(name = "IDDIEMDO")
    private String idDiemDo;
    @Column(name = "IDDIEMDO_CHANGE")
    private String idDiemDoChange;
    @Column(name = "IDCUON")
    private String idCuon;
}

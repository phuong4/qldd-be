package com.evnit.ttpm.AuthApp.entity.acceptance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "M_ACCEPTANCE_DETAILS")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcceptanceDetail {
    @Id
    @Column(name = "ACCEPTDETAILID")
    private String acceptDetailId;
    @Column(name = "ACCEPTID")
    private String acceptId;
    @Column(name = "ASSETID")
    private String assetId;
    @Column(name = "ID_CT")
    private String idCongTo;
    @Column(name = "IDS_TU")
    private String idDeviceTU;
    @Column(name = "IDS_TI")
    private String idDeviceTI;
    @Column(name = "STATUS_ACCEPT")
    private Boolean statusAccept;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "HTTTSL_ACCEPT")
    private Boolean hTTTSLAccept;
}

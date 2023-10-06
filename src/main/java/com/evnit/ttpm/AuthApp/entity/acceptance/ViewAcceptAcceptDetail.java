package com.evnit.ttpm.AuthApp.entity.acceptance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "VIEW_ACCEPT_ACCEPT_DETAIL")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewAcceptAcceptDetail {
    @Id
    @Column(name = "ACCEPTDETAILID")
    private String acceptDetailId;
    @Column(name = "ACCEPTID")
    private String acceptId;
    @Column(name = "ACCEPT_FORM")
    private String acceptForm;
    @Column(name = "ACCEPTDESC")
    private String acceptDesc;
    @Column(name = "ACTIONENDDATE")
    private Date actionEndDate;
    @Column(name = "ACTIONSTARTDATE")
    private Date actionStartDate;
    @Column(name = "ASSETID")
    private String assetId;
    @Column(name = "LD_XNHAN")
    private String ldXNhan;
    @Column(name = "NQL_XNHAN")
    private String nqlXNhan;
    @Column(name = "RESULT_ACCEPT")
    private String resultAccept;
    @Column(name = "TYPE_ACCEPT")
    private String typeAccept;
    @Column(name = "ID_DEVICE")
    private String idDevice;
    @Column(name = "HTTTSL_ACCEPT")
    private String hTTTSLAccept;
    @Column(name = "STATUS_ACCEPT")
    private String statusAccept;
}

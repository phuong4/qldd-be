package com.evnit.ttpm.AuthApp.entity.acceptance;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Table(name = "VIEW_NGHIEM_THU_FINAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class ViewAcceptanceFinal {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "LD_XNHAN")
    private Boolean lDXNhan;
    @Column(name = "NQL_XNHAN")
    private Boolean nQLXNHAN;
    @Column(name = "POWERSYSTEMTYPE")
    private String powerSystemType;
    @Column(name = "POWERSYSTEMNAME")
    private String powerSystemName;
    @Column(name = "ACCEPT_FORM")
    private String acceptForm;
    @Column(name = "ACCEPT_FORMSTR", insertable = false, updatable = false)
    private String acceptFormStr;
    @Column(name = "ACTIONSTARTDATE")
    private Date actionStartDate;
    @Column(name = "ACTIONENDDATE")
    private Date actionEndDate;
    @Column(name = "TYPE_ACCEPT")
    private String typeAccept;
    @Column(name = "DEVICETYPE")
    private String deviceType;
    @Column(name = "ASSETDESCDETAIL")
    private String assetDescDetail;
    @Column(name = "AMOUNT")
    private Integer amount;
    @Column(name = "ACCEPTANCED")
    private Integer acceptanced;
    @Column(name = "UNTESTED")
    private Integer untested;
    @Column(name = "RESULT_ACCEPT")
    private String resultAccept;
    @Column(name = "THANGNT")
    private Date thangNT;
    @Column(name = "ASSETID")
    private String assetId;
    @Column(name = "ACCEPTID")
    private String acceptId;
    @Column(name = "SOLUOT")
    private Integer soLuot;
    @Column(name = "POWERSYSTEMSTATUS")
    private Integer powerSystemStatus;
    @Column(name = "POWERSYSTEMTYPED")
    private String powerSystemTyped;
    @Column(name = "CATEGORYID")
    private String categoryId;

}

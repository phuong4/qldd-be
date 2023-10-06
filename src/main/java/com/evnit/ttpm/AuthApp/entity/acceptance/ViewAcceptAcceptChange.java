package com.evnit.ttpm.AuthApp.entity.acceptance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "VIEW_ACCEPT_ACCEPT_CHANGE")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewAcceptAcceptChange {
    @Id
    @Column(name = "ACCEPCHANGEID")
    private String acceptChangeId;
    @Column(name = "ACCEPTID")
    private String acceptId;
    @Column(name = "ACTIONENDDATE")
    private Date actionEndDate;
    @Column(name = "ACTIONSTARTDATE")
    private Date actionStartDate;
    @Column(name = "ASSETID")
    private String assetId;
    @Column(name = "ID_DEVICE")
    private String idDevice;
    @Column(name = "TYPE_ACCEPT")
    private String typeAccept;
}

package com.evnit.ttpm.AuthApp.entity.acceptance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "VIEW_ACCEPTANCE")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewAcceptance {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "ID_DEVICE")
    private String idDevice;
    @Column(name = "ACTIONSTARTDATE")
    private Date actionStartDate;
    @Column(name = "ACTIONENDDATE")
    private Date actionEndDate;
    @Column(name = "TYPE_ACCEPT")
    private String typeAccept;
}

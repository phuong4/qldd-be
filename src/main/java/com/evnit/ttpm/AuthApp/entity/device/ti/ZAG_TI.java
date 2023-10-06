package com.evnit.ttpm.AuthApp.entity.device.ti;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ZAG_TI")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZAG_TI {
    @Id
    @Column(name = "ATTRDATAID")
    private String attrDataId;
    @Column(name = "OBJTYPEID")
    private String objTypeId;
    @Column(name = "OBJID")
    private String objId;
    @Column(name = "PHA")
    private String pha;
    @Column(name = "KIEU_TI")
    private String kieuTI;
    @Column(name = "PDUYET_MAU")
    private Date pDuyetMau;
}

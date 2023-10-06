package com.evnit.ttpm.AuthApp.entity.device.cuon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ZAG_CUON")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZAG_CUON {
    @Id
    @Column(name = "ATTRDATAID")
    private String attrDataId;
    @Column(name = "OBJTYPEID")
    private String objTypeId;
    @Column(name = "OBJID")
    private String objId;
    @Column(name = "CCX")
    private String ccx;
    @Column(name = "DUNG_LUONG")
    private String dungLuong;
    @Column(name = "CUON_DNOI")
    private String cuonDNoi;
    @Column(name = "MACH_NTHU")
    private String machNThu;
    @Column(name = "TINH_CHAT")
    private String tinhChat;
    @Column(name = "TSB")
    private String tsb;

}

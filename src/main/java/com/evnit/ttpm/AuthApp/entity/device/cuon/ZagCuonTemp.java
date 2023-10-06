package com.evnit.ttpm.AuthApp.entity.device.cuon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ZAG_CUON_TEMP")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZagCuonTemp {
    @Id
    @Column(name = "ID")
    private String id;
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

    public ZagCuonTemp mapperZag(ZAG_CUON zag) {
        ZagCuonTemp zagCuonTemp = new ZagCuonTemp();
        zagCuonTemp.setCuonDNoi(zag.getCuonDNoi());
        zagCuonTemp.setCcx(zag.getCcx());
        zagCuonTemp.setAttrDataId(zag.getAttrDataId());
        zagCuonTemp.setMachNThu(zag.getMachNThu());
        zagCuonTemp.setDungLuong(zag.getDungLuong());
        zagCuonTemp.setObjId(zag.getObjId());
        zagCuonTemp.setObjTypeId(zag.getObjTypeId());
        zagCuonTemp.setTinhChat(zag.getTinhChat());
        zagCuonTemp.setTsb(zag.getTsb());
        return zagCuonTemp;
    }
}

package com.evnit.ttpm.AuthApp.model.device.diemdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZAGDiemDo {
    private String attrDataId;
    private String objTypeId;
    private String objId;
    private Date nThuTinh;
    private Date nThuTai;
    private Date nThuTTSL;
    private String tinhChat1;
    private String dViGNhan1;
    private String tinhChat2;
    private String dViGNhan2;
    private String dTacXNK;
    private String xnk;

    public Date getnThuTinh() {
        return nThuTinh;
    }

    public void setnThuTinh(Date nThuTinh) {
        this.nThuTinh = nThuTinh;
    }

    public Date getnThuTai() {
        return nThuTai;
    }

    public void setnThuTai(Date nThuTai) {
        this.nThuTai = nThuTai;
    }

    public Date getnThuTTSL() {
        return nThuTTSL;
    }

    public void setnThuTTSL(Date nThuTTSL) {
        this.nThuTTSL = nThuTTSL;
    }

    public String getTinhChat1() {
        return tinhChat1;
    }

    public void setTinhChat1(String tinhChat1) {
        this.tinhChat1 = tinhChat1;
    }

    public String getdViGNhan1() {
        return dViGNhan1;
    }

    public void setdViGNhan1(String dViGNhan1) {
        this.dViGNhan1 = dViGNhan1;
    }

    public String getTinhChat2() {
        return tinhChat2;
    }

    public void setTinhChat2(String tinhChat2) {
        this.tinhChat2 = tinhChat2;
    }

    public String getdViGNhan2() {
        return dViGNhan2;
    }

    public void setdViGNhan2(String dViGNhan2) {
        this.dViGNhan2 = dViGNhan2;
    }

    public String getdTacXNK() {
        return dTacXNK;
    }

    public void setdTacXNK(String dTacXNK) {
        this.dTacXNK = dTacXNK;
    }

    public String getXnk() {
        return xnk;
    }

    public void setXnk(String xnk) {
        this.xnk = xnk;
    }
}

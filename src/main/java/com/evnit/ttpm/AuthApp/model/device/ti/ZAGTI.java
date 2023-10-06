package com.evnit.ttpm.AuthApp.model.device.ti;

import com.evnit.ttpm.AuthApp.model.device.cuon.CuonDetailDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZAGTI {
    private String attrDataId;
    private String objTypeId;
    private String objId;
    private String pha;
    private String kieuTI;
    private List<CuonDetailDto> cuon;
    private Double cycleAccreditation;
    private Date inspectionStamps;
    private String nationalFact;
    private String pManufacturerId;
    private String serialNum;
    private Date pDuyetMau;

    public String getpManufacturerId() {
        return pManufacturerId;
    }

    public void setpManufacturerId(String pManufacturerId) {
        this.pManufacturerId = pManufacturerId;
    }

    public Date getpDuyetMau() {
        return pDuyetMau;
    }

    public void setpDuyetMau(Date pDuyetMau) {
        this.pDuyetMau = pDuyetMau;
    }
}

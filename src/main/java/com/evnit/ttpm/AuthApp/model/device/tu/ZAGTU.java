package com.evnit.ttpm.AuthApp.model.device.tu;

import com.evnit.ttpm.AuthApp.model.device.cuon.CuonDetailDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZAGTU {
    private String pha;
    private String attrDataId;
    private String objTypeId;
    private String objId;
    private Date pDuyetMau;
    private String kieuTU;
    private List<CuonDetailDto> cuon;
    private Double cycleAccreditation;
    private Date inspectionStamps;
    private String nationalFact;
    private String pManufacturerId;
    private String serialNum;

    public String getPha() {
        return pha;
    }

    public void setPha(String pha) {
        this.pha = pha;
    }

    public Date getpDuyetMau() {
        return pDuyetMau;
    }

    public void setpDuyetMau(Date pDuyetMau) {
        this.pDuyetMau = pDuyetMau;
    }

    public String getpManufacturerId() {
        return pManufacturerId;
    }

    public void setpManufacturerId(String pManufacturerId) {
        this.pManufacturerId = pManufacturerId;
    }
}

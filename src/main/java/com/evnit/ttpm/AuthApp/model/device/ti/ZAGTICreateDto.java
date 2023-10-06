package com.evnit.ttpm.AuthApp.model.device.ti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZAGTICreateDto {
    private String pha;
    private String attrDataId;
    private String objTypeId;
    private String objId;
    private Date pDuyetMau;
    private String kieuTI;

    public void setpDuyetMau(Date pDuyetMau) {
        this.pDuyetMau = pDuyetMau;
    }
}

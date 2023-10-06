package com.evnit.ttpm.AuthApp.model.device.tu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZAGTUCreateDto {
    private String pha;
    private String attrDataId;
    private String objTypeId;
    private String objId;
    private Date pDuyetMau;
    private String kieuTU;

    public void setpDuyetMau(Date pDuyetMau) {
        this.pDuyetMau = pDuyetMau;
    }
}

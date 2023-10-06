package com.evnit.ttpm.AuthApp.model.device.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewHTDDDto {
    private String idDiemDo;
    private String idCongTo;
    private String assetDescDiemDo;
    private String serialNumCongTo;
    private String tuText;
    private String tiText;
    private String statusDiemDo;
    private String statusDiemDoStr;
    private String idPower;
    private String assetDescPower;
    private String idsBTI;
    private String idsBTU;
    private Boolean isSave;
}

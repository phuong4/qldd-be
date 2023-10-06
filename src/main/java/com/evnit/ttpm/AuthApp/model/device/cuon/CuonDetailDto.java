package com.evnit.ttpm.AuthApp.model.device.cuon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuonDetailDto {
    private String attrDataId;
    private String objTypeId;
    private String objId;
    private String ccx;
    private String dungLuong;
    private String cuonDNoi;
    private String machNThu;
    private String tinhChat;
    private String tsb;
    private String assetDesc;
    private String useStatusLastId;
    private Integer assetOrd;
}

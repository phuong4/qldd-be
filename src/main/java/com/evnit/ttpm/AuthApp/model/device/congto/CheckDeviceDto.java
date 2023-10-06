package com.evnit.ttpm.AuthApp.model.device.congto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckDeviceDto {
    private String assetId;
    private String category;
    private String serialNum;
    private String pmanufacturerId;
    private String assetIdParent;
    private String orgId;
    private String assetDesc;
}

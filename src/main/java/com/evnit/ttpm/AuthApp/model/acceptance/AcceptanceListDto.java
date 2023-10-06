package com.evnit.ttpm.AuthApp.model.acceptance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcceptanceListDto {
    private String id;
    private Integer soLuot;
    private String powerSystemType;
    private String powerSystemName;
    private String powerSystemStatus;
    private String PowerSystemStatusStr;
    private String powerSystemTyped;
    private Integer deviceStatus;
    private String acceptForm;
    private String acceptFormStr;
    private String actionStartDate;
    private String actionEndDate;
    private String typeAccept;
    private String typeAcceptStr;
    private String deviceType;
    private String deviceStatusStr;
    private String assetDescDetail;
    private String amount;
    private String acceptanced;
    private String untested;
    private String acceptDesc;
    private String resultAccept;
    private Date thangNT;
    private String assetId;
    private String acceptId;
    private Boolean lDXNhan;
    private Boolean nQLXNHAN;

}

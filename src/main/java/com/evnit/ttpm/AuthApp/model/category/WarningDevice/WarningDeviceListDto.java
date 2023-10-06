package com.evnit.ttpm.AuthApp.model.category.WarningDevice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarningDeviceListDto {
    private String Id;
    private Integer Type;
    private String TbaId;
    private String TbaName	;
    private String NameDD	;
    private String pha;
    private Double ValueErrorCT;
    private Double ValueErrorCTYes;

    private Double ErrorScoreTUTI;
    private Double ErrorScoreTUTI1;
    private Double ErrorOriginTUTI;
    private Double ErrorOriginTUTI1;
    private Double ErrorScoreTUTIYes;
    private Double ErrorScoreTUTIYes1;
    private Double ErrorOriginTUTIYes;
    private Double ErrorOriginTUTIYes1;
    private String Month;
    private String TypeStr;
    private String MonthTimeShow;
    private Date kdDate;

    private Integer countCT;
    private Integer countTU;
    private Integer countTI;

    private String congToAssetId;
    private String congToAssetDESC;

    private String btiAssetId;
    private String btiAssetDesc;
    private String tiAssetId;
    private String tiAssetDesc;

    private String btuAssetId;
    private String btuAssetDesc;
    private String tuAssetId;
    private String tuAssetDesc;

    private String assetId;
    private String assetDesc;
    private String kdDetailId;
}

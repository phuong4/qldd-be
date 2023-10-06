package com.evnit.ttpm.AuthApp.model.category.tbargl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TbaRglListDto {
    private String id;
    private String code;
    private String name;
    private String voltageLevel;
    private Integer city;
    private Integer type;
    private String typeStr;
    private String cityName;
    private Integer ownershipUnit;
    private String ownershipUnitStr;
    private Integer manageUnit;
    private String manageUnitStr;
    private Integer status;
    private String statusStr;
    private Boolean xnk;
    private Integer ownerType;
    private Integer manageType;
    private String description;

}

package com.evnit.ttpm.AuthApp.model.category.tbargl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TbaRglCrudDto {
    private String id;
    private String code;
    private String name;
    private String voltageLevel;
    private String cityName;
    private Integer city;
    private String typeStr;
    private Integer type;
    private String ownershipUnitStr;
    private Integer ownershipUnit;
    private String manageUnitStr;
    private Integer manageUnit;
    private String statusStr;
    private Integer status;
    private Boolean xnk;
    private String userId;
    private String description;

}

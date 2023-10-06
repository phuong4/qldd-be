package com.evnit.ttpm.AuthApp.model.category.WarningSystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarningSystemListDto {
    private Number Id;
    private Number Type;
    private String idDiemDo;
    private String TbaName	;
    private String Nature	;
    private String NameDD	;
    private Double S;
    private Double SPlus;
    private Double SMinus;
    private String kdDetailCT;
    private String kdDetailTU;
    private String kdDetailTI;
    private String kdDateCT;
    private String kdDateTU;
    private String kdDateTI;
    private String idBtu;
    private String idBti;
}

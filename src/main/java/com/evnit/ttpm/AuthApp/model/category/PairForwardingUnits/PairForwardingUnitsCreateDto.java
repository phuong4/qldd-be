package com.evnit.ttpm.AuthApp.model.category.PairForwardingUnits;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PairForwardingUnitsCreateDto {
    private Integer id;
    private Integer unit1;
    private String description;
    private Integer unit2;
    private boolean is_Delete;
	private String userId;
    private String guid;
    private String unit1Str;
    private String unit2Str;

    

}

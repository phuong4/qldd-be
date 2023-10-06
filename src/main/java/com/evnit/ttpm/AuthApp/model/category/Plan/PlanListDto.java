package com.evnit.ttpm.AuthApp.model.category.Plan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanListDto {
    private String Id;
    private Integer Type;
    private String TbaId;
    private String TbaName	;
    private String YearTime	;
    private Integer MonthTime	;
    private String DetailCT	;
    private Integer CountCT;
    private String DetailTU	;
    private Integer CountTU;
    private String DetailTI	;
    private Integer CountTI;
    private Integer CountMonth;
    private String MonthTimeShow;

}

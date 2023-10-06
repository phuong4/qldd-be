package com.evnit.ttpm.AuthApp.model.Paramater;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParamaterListDto {
    private Integer Id;
    private Float Ua;
    private Float Ub;
    private Float Uc;
    private Float Ia;
    private Float Ib;
    private Float Ic;
    private Float Phia;
    private Float Phib	;
    private Float Phic;
    private String TbaId;
    private String DiemDoId;
    private String EventId;
    private Date DateAnalysis;





}

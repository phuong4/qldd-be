package com.evnit.ttpm.AuthApp.model.Paramater;

import com.evnit.ttpm.AuthApp.model.category.SuCo.FileData;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParamaterCreateDto {
    private String id;
    private Double ua;
    private Double ub;
    private Double uc;
    private Double ia;
    private Double ib;
    private Double ic;
    private Double phia;
    private Double phib	;
    private Double phic;
    private String tbaId;
    private String diemDoId;
    private String eventId;
    private String dateEvent;
    private String dateEventStr;




}

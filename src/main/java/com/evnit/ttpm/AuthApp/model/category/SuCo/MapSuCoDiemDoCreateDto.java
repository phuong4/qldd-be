package com.evnit.ttpm.AuthApp.model.category.SuCo;

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
public class MapSuCoDiemDoCreateDto {
    private String PROBLEMID;
    private String ASSETID;
    private Integer TYPE;
    private boolean ISDELETE;
    private Integer COUNT;
}

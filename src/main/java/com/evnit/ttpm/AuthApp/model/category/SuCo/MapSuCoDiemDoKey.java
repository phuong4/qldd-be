package com.evnit.ttpm.AuthApp.model.category.SuCo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MapSuCoDiemDoKey implements Serializable {
    private String PROBLEMID;
    private String ASSETID;

}

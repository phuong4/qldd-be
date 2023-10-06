package com.evnit.ttpm.AuthApp.model.category.DeliveryUnit;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DeliveryUnitListDto {
    private Integer id;
    private String name;
    private String description;
    private boolean xnk;
    private boolean is_Delete;
    private String guid;
}

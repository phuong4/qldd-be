package com.evnit.ttpm.AuthApp.model.category.DeliveryUnit;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DeliveryUnitCreateDto {
    private Integer id;
    private String name;
    private String description;
    private String userId;
    private boolean xnk;
    private boolean is_Delete;
    private String guid;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean getIs_Delete() {
        return is_Delete;
    }
    public void setIs_Delete(boolean is_Delete) {
        this.is_Delete = is_Delete;
    }
}

package com.evnit.ttpm.AuthApp.model.request.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SelectItem {
    private String id;
    private String value;
    private String text;
    private String deviceType;
    private Integer status;

}

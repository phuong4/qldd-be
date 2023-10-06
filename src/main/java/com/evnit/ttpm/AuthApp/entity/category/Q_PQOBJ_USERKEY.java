package com.evnit.ttpm.AuthApp.entity.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Q_PQOBJ_USERKEY implements Serializable {
    private String ASSETID;
    private String USERID;
}
